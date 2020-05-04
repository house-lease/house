package cn.bdqn.test;

import cn.bdqn.domain.Particular;
import cn.bdqn.domain.User;
import cn.bdqn.service.ParticularService;

import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ParticularTest {
    @Test
    public void testSave() throws Exception{

        ApplicationContext ac =  new ClassPathXmlApplicationContext("beans-service.xml","beans-mapper.xml");

        ParticularService particularService = (ParticularService) ac.getBean("particularService");
        Particular particulars=new Particular();
        User user =new User();
        user.setId(1);
        user.setUserName("老王");
        particulars.setId(1);
        particulars.setUser(user);
        particulars.setRefillTime(new Date());
        BigDecimal bigDecimal =new BigDecimal("12.22");
        particulars.setMoney(bigDecimal);
        particulars.setState(0);
        particularService.save(particulars);

        System.out.println(particulars);
    }

     @Test
    public void testDelete() throws Exception{

        ApplicationContext ac =  new ClassPathXmlApplicationContext("bean.xml");

        ParticularService particularService = (ParticularService) ac.getBean("particularService");
        particularService.deleteById(1);


    }
}
