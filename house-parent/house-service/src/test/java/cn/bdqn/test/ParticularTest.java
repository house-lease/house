package cn.bdqn.test;

import cn.bdqn.domain.Particular;
import cn.bdqn.domain.User;
import cn.bdqn.service.ParticularService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.Date;

public class ParticularTest {

    @Test
    public void testSave() throws Exception{

        ApplicationContext ac =  new ClassPathXmlApplicationContext("beans-service.xml");

        ParticularService particularService = (ParticularService) ac.getBean("particularService");
//        particularService.delete(1);
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
}
