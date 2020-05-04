package cn.bdqn.test;

import cn.bdqn.domain.Payment;
import cn.bdqn.service.PaymentService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class PaymentServiceTest {

    @Test
    public void testQueryByState() throws Exception{
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("beans-mapper.xml","beans-service.xml");
        PaymentService paymentService = (PaymentService) applicationContext.getBean("paymentServiceImpl");
        List<Payment> payments = paymentService.queryByState();
        System.out.println(payments);
    }
    @Test
    public void testTimeOfPaymen() throws Exception{
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("beans-mapper.xml","beans-service.xml");
        PaymentService paymentService = (PaymentService) applicationContext.getBean("paymentServiceImpl");
        paymentService.TimeOfPaymen();
        System.out.print("方法执行了:");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        System.out.println(simpleDateFormat.format(new Date()));
    }
    @Test
    public void testCalendar() throws Exception{
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, 1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
        String date = simpleDateFormat.format(calendar.getTime());
//        Date date1 = simpleDateFormat.parse(date);
        System.out.println(date);
    }
    /*测试定时任务*/
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("beans-service.xml", "beans-mapper.xml");
    }
}
