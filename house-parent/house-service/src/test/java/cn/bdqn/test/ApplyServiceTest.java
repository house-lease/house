package cn.bdqn.test;

import cn.bdqn.domain.Apply;
import cn.bdqn.domain.Payment;
import cn.bdqn.domain.User;
import cn.bdqn.service.ApplyService;
import cn.bdqn.service.PaymentService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApplyServiceTest {

    @Test
    public void testSave() throws Exception{
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("beans-mapper.xml","beans-service.xml");
        ApplyService applyService = (ApplyService) applicationContext.getBean("applyServiceImpl");
        User user = new User();
        user.setId(1);
        Apply apply = new Apply();
        apply.setUser(user);
        apply.setUserName("测试001");
        apply.setUserPhone("13246852");
        apply.setCash(1);
        apply.setState(1);
        apply.setHouseImageUrl("saokfnhusnhfe");
        applyService.addInfo(apply);
    }

}
