package seck.seck;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring.xml", "classpath:spring/springmvc-config.xml" })
public class SeckillServiceTest {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StringRedisTemplate redisTemplate;

	@Test
	public void test() throws InterruptedException {

		System.out.println("hello");
		redisTemplate.delete("myStr");
		redisTemplate.opsForValue().set("myStr", "100", 2, TimeUnit.SECONDS);
		System.out.println(redisTemplate.opsForValue().get("myStr"));
		Thread.sleep(3000);
		System.out.println(redisTemplate.opsForValue().get("myStr"));
		System.out.println("---------------");
	}

}
