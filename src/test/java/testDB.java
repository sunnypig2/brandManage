package java;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.ContextConfiguration;
/**
 * Created by lenovo on 2018/7/31.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath：spring-mybatis.xml","classpath:spring-mvc.xml"})
public class testDB {
}
