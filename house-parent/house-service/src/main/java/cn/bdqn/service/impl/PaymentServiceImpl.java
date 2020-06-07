package cn.bdqn.service.impl;

import cn.bdqn.domain.*;
import cn.bdqn.mapper.*;
import cn.bdqn.service.PaymentService;
import cn.bdqn.utils.DateUtil;
import cn.bdqn.utils.HttpUtils;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentMapper paymentMapper;
    @Autowired
    private MoneyMapper moneyMapper;

    @Autowired
    private TenantMapper tenantMapper;

    @Autowired
    private HouseMapper houseMapper;

    @Autowired
    private RecordMapper recordMapper;
    @Override
    public List<Payment> queryByState() {
        return paymentMapper.selectByState();
    }


    public void TimeOfPaymen() {
        System.out.println("执行了");
        List<Payment> payments = this.queryByState();
        Calendar calendar = Calendar.getInstance();//操作date的工具类
        payments.forEach(item->{
            if (DateUtil.date2String3(new Date()).equals(DateUtil.date2String3(item.getNextTime()))) {
                System.out.println("到我了");
//                获得订单对象
                Record record = recordMapper.selectByPrimaryKey(item.getRecordId());
                int payerUserId = item.getPayerUser().getId();//付款人id
                int payeeUserId = item.getPayeeUser().getId();//收款人ID
                BigDecimal money = item.getHouse().getPrice();//所需支付的租金
                Money payerMoney = moneyMapper.selectByUerId(payerUserId);//付款人资金对象
                Money payeeMoney = moneyMapper.selectByUerId(payeeUserId);//收款人资金对象
//                判读租客账户余额是否充足
                if (payerMoney.getMoney().compareTo(money) == 0 || payerMoney.getMoney().compareTo(money) == 1) {
                    payerMoney.setMoney(payerMoney.getMoney().subtract(money));//付款
                    payeeMoney.setMoney(payeeMoney.getMoney().add(money));//收款
                    moneyMapper.updateByUserId(payeeMoney);//更新付款到数据库
                    moneyMapper.updateByUserId(payerMoney);//更细收款
//            钱到位之后开始 修改房租日期
                    calendar.setTime(item.getNextTime());//设置要操作的时间
                    calendar.add(Calendar.MONTH, 1);//给要操作的时间加1个月,
                    item.setStartTime(item.getNextTime());//设置上次的付款时间,
                    item.setNextTime(calendar.getTime());//设置下次交租时间
                    item.setDeliveryNumber(item.getDeliveryNumber() + 1);//已交次数加1
                    item.setResidueMoney(item.getResidueMoney().subtract(money));//剩余的金额
                    if ((item.getNumber() - item.getDeliveryNumber()) == 0) {
                        item.setState(0);
                        item.setNextTime(null);
                        record.setDealState(0);
                        //更新订单对象
                        recordMapper.updateByPrimaryKeySelective(record);
                    }
                    //并更新付款对象
                    paymentMapper.updateByPrimaryKeySelective(item);
                } else {
//                    短信提醒缴租
                    this.verification(item.getPayerUser().getPhone(), item.getHouse().getPrice());
                    calendar.setTime(item.getNextTime());//设置要操作的时间
                    calendar.add(Calendar.DATE, 1);//给要操作的时间加1天,
                    item.setNextTime(calendar.getTime());//设置下次缴租时间
//                    更新订单还款对象
                    paymentMapper.updateByPrimaryKeySelective(item);
                }
            }
        });
//        根据租客信息更新房屋信息
        List<Tenant> tenants = new ArrayList<>();
        tenants = tenantMapper.selectAll();
        tenants.forEach(items->{
            if (DateUtil.date2String3(new Date()).equals(DateUtil.date2String3(items.getTerminationTime()))) {
                items.setState(1);
                //更新租客状态
                tenantMapper.updateByPrimaryKeySelective(items);
                House house = houseMapper.selectByPrimaryKey(items.getHouseId());
                //更新房屋状态
                house.setState(0);
                //更新可租房屋数量
                house.setResidueRoom(house.getResidueRoom()+1);
                //更新房屋信息
                houseMapper.updateByPrimaryKey(house);
            }
        });
    }


    /**
     * 根据订单id查询
     * @param recordId
     * @return
     */
    @Override
    public Payment queryByRecordId(Integer recordId) {
        return paymentMapper.selectByRecordId(recordId);
    }

    //    短信验证
    private void verification(String phone,BigDecimal price){

        String host = "https://feginesms.market.alicloudapi.com";
        String path = "/codeNotice";
        String method = "GET";
        String appcode = "a55b99e70e2b4860a0fe8056265719b8";//阿里云appCode
        String message = "（自动缴租失败）您本期房租应缴费:"+price+"元,请手动缴租或保证账户余额充足,谢谢";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        //测试可用默认短信模板,测试模板为专用模板不可修改,如需自定义短信内容或改动任意字符,请联系旺旺或QQ726980650进行申请
        querys.put("param", price.toString());
        //然后这里是手机号
        querys.put("phone", phone);
        //签名编号【联系旺旺客服申请，测试请用1】
        querys.put("sign", "1");
        //模板编号【联系旺旺客服申请，测试请用1~21】
        querys.put("skin", "1");
        //JDK 1.8示例代码请在这里下载：  http://code.fegine.com/Tools.zip

        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 或者直接下载：
             * http://code.fegine.com/HttpUtils.zip
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             * 相关jar包（非pom）直接下载：
             * http://code.fegine.com/aliyun-jar.zip
             */
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            System.out.println(response.toString());//如不输出json, 请打开这行代码，打印调试头部状态码。
//            状态码: 200 正常；400 URL无效；401 appCode错误； 403 次数用完； 500 API网管错误
//            获取response的body
            String ss = EntityUtils.toString(response.getEntity());
            System.out.println(ss);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
