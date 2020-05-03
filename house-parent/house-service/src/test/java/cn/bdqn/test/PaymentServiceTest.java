package cn.bdqn.test;

import cn.bdqn.domain.Payment;
import cn.bdqn.service.PaymentService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class PaymentServiceTest {

    @Test
    public void testQueryByState() throws Exception{
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("beans-mapper.xml","beans-service.xml");
        PaymentService paymentService = (PaymentService) applicationContext.getBean("paymentServiceImpl");
        List<Payment> payments = paymentService.queryByState();
        payments.forEach(System.out::println);
    }
}
