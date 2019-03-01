package test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ≤‚ ‘π§æﬂ¿‡
 * @author Exception
 *
 */
public class TestBase {
	public ApplicationContext getContext() {
		String [] conf = {
				"conf/spring-mvc.xml",
				"conf/spring-mybatis.xml"
		};
		ApplicationContext ctx = new ClassPathXmlApplicationContext(conf);
		return ctx;
	}
}
