package seck.seck;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yyf.school.login.dao.LoginDao;
import com.yyf.school.notice.dao.NoticeDao;
import com.yyf.school.notice.vo.NoticeVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring.xml", "classpath:spring/springmvc-config.xml" })
public class SeckillServiceTest {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private NoticeDao NoticeDao;

	@Autowired
	private LoginDao loginDao;

	@Test
	public void test() throws InterruptedException {
		// AllRoleVO allRoleVO = new AllRoleVO();
		// allRoleVO.setId("hnbuyztmur1523410944750");
		//
		// allRoleVO.setSchoolCode("sdsd");
		// allRoleVO.setCollegeCode("sdf");
		// allRoleVO.setGradeCode("4dfdf44");
		// allRoleVO.setCaseSchool(3);

		List<String> list = new ArrayList<String>();
		list.add("2");
		// loginDao.doApprove(1, list);
		// NoticeVO noticeVO = new NoticeVO();
		// noticeVO.setId("11");
		// noticeVO.setContents("111");
		// noticeVO.setTitle("222");
		// noticeVO.setWriteDate(new Date());
		// NoticeDao.update(noticeVO);
		// Integer sds = NoticeDao.findHotById("df");
		// if (sds == null) {
		// System.out.println("dsd");
		// } else {
		// System.out.println("1212");
		// }
		// System.out.println(list1.size());
		NoticeDao.updateHot("sd1df");

	}
}
