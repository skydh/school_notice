package com.yyf.school.notice.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.yyf.school.login.dao.LoginDao;
import com.yyf.school.login.vo.AllRoleVO;
import com.yyf.school.notice.dao.NoticeDao;
import com.yyf.school.notice.service.NoticeService;
import com.yyf.school.notice.vo.AggVO;
import com.yyf.school.notice.vo.NoticeVO;
import com.yyf.school.shiro.context.TokenApplication;
import com.yyf.school.utils.constant.Constants;
import com.yyf.school.utils.exception.SchoolException;
import com.yyf.school.utils.idcreate.IdCreateUtils;
import com.yyf.school.utils.serializableY.SerializableY;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private TokenApplication tokenApplication;

	@Autowired
	private LoginDao loginDao;
	@Autowired
	private NoticeDao noticeDao;

	@Autowired
	private StringRedisTemplate redisTemplate;

	@Override
	public void save(NoticeVO noticeVO) throws SchoolException {

		String id = tokenApplication.getUserId();
		String username = loginDao.findUserNameById(id);
		String roleID = loginDao.showId(id);
		noticeVO.setWriterId(roleID);
		noticeVO.setWriter(username);
		noticeVO.setId(IdCreateUtils.getId());
		noticeVO.setWriteDate(new Date());
		noticeDao.insert(noticeVO);

	}

	@Override
	public void update(NoticeVO noticeVO) throws SchoolException {
		noticeVO.setWriteDate(new Date());
		noticeDao.update(noticeVO);
	}

	@Override
	public void delete(List<String> list) throws SchoolException {
		noticeDao.delete(list);
	}

	@Override
	public List<NoticeVO> showList() throws SchoolException {
		String id = tokenApplication.getUserId();
		String roleID = loginDao.showId(id);
		return noticeDao.showList(roleID);
	}

	/**
	 * 更具规则，只能查看本级以及上级的信息,判断按钮可见性的操作
	 */
	@Override
	public AggVO showListAll() throws SchoolException {
		AggVO aggVO = new AggVO();
		String id = tokenApplication.getUserId();
		AllRoleVO allRoleVO = loginDao.showCode(id);
		List<String> list = new ArrayList<String>();

		if (allRoleVO != null) {
			if (allRoleVO.getId() != null) {
				list.add(allRoleVO.getId());
			}
			if (allRoleVO.getSchoolCode() != null) {
				list.add(allRoleVO.getSchoolCode());
			}
			if (allRoleVO.getGradeCode() != null) {
				list.add(allRoleVO.getGradeCode());
			}
			if (allRoleVO.getClassCode() != null) {
				list.add(allRoleVO.getClassCode());
			}
			if (allRoleVO.getCollegeCode() != null) {
				list.add(allRoleVO.getCollegeCode());
			}
		}
		List<NoticeVO> voList = null;
		if (list.size() > 0) {
			voList = noticeDao.showListAll(list);
		}
		aggVO.setList(voList);
		String tId = loginDao.isPower(id);
		if (tId != null) {
			aggVO.setApproveMenu(true);
		} else {
			aggVO.setApproveMenu(false);
		}
		return aggVO;
	}

	/**
	 * 核心方法，恩，对这个加缓存处理，因为这个数据量太大，打开一个文章的数据量太大，所以这边我们。 具体逻辑如下：
	 * 1，每次查询时，先从redis里面查,
	 * 查出来了就返回，查不出来就从数据库中取出来，然后返回，然后从hot表中查询是否存在这个数据的id，存在判断是否%3==0，
	 * 则加入到缓存里面，设置过期时间为1天， 否则加一，不存在则插入，
	 * 
	 */
	@Override
	public NoticeVO queryDetail(String id) throws SchoolException {
		// TODO Auto-generated method stub
		String voString = redisTemplate.opsForValue().get(id);
		if (voString != null) {
			return (NoticeVO) SerializableY.objectDeserialization(voString);
		} else {

			NoticeVO noticeVO = noticeDao.findById(id);
			Integer times = noticeDao.findHotById(id);
			if (times == null) {
				noticeDao.insertHot(id);
			} else if (times % (Constants.yyf) == 0) {
				String stringVO = SerializableY.objectSerialiable(noticeVO);
				redisTemplate.opsForValue().set(id, stringVO, Constants.offerDate, TimeUnit.SECONDS);
			} else {
				noticeDao.updateHot(id);
			}
			return noticeVO;

		}

	}

}
