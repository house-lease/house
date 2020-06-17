package cn.bdqn.service.impl;

import cn.bdqn.domain.*;
import cn.bdqn.mapper.*;
import cn.bdqn.service.RecordService;
import cn.bdqn.utils.DateUtil;
import cn.bdqn.utils.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordMapper recordMapper;

    @Autowired
    private PaymentMapper paymentMapper;

    @Autowired
    private MoneyMapper moneyMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HouseMapper houseMapper;

    @Autowired
    private TenantMapper tenantMapper;

    @Override
    public Integer save(Integer judge,Integer payerUserId, Integer payeeUserId, Integer houseId, BigDecimal sumMoney,BigDecimal residueMoney,
                     Integer startValue) {

//        付款人对象
        User payerUser = userMapper.selectByPrimaryKey(payerUserId);
        //收款人对象
        User payeeUser = userMapper.selectByPrimaryKey(payeeUserId);
        //房屋对象
        House house = houseMapper.selectByPrimaryKey(houseId);
//        获得付款人的账户余额
        Money payerUserMoney = moneyMapper.selectByUerId(payerUserId);
//         获得收款人的账户余额
        Money payeeUserMoney = moneyMapper.selectByUerId(payeeUserId);
        // 订单对象
        Record record = new Record();
        //            还款对象
        Payment payment = new Payment();
//        操作时间的对象
        Calendar calendar = Calendar.getInstance();
        if (house.getResidueRoom()>0){
            if (judge==0){
                if (payerUserMoney.getMoney().compareTo(house.getPrice().add(new BigDecimal(1000)))==0||(payerUserMoney.getMoney().compareTo(house.getPrice().add(new BigDecimal(1000)))==1)){
//          扣除首付金额
                    payerUserMoney.setMoney(payerUserMoney.getMoney().subtract(house.getPrice().add(new BigDecimal(1000))));
                    moneyMapper.updateByUserId(payerUserMoney);
                    //增加首付金额
                    payeeUserMoney.setMoney(payeeUserMoney.getMoney().add(house.getPrice().add(new BigDecimal(1000))));
                    moneyMapper.updateByUserId(payeeUserMoney);
//           订单对象
                    record.setPayeeUser(payeeUser);
                    record.setPayeeName(payeeUser.getUserName());
                    record.setPayerUser(payerUser);
                    record.setPayerName(payerUser.getUserName());
                    record.setDealMoney(sumMoney);
                    record.setHouse(house);
                    record.setHouseName(house.getHouseName());
                    record.setDealTime(new Date());
                    record.setState(0);
                    record.setDealState(1);
                    record.setRecord(DateUtil.date2String1(new Date()));
                    recordMapper.insert(record);

//            还款对象

                    payment.setPayeeUser(payeeUser);
                    payment.setPayeeName(payeeUser.getUserName());
                    payment.setPayerUser(payerUser);
                    payment.setPayerName(payerUser.getUserName());
                    payment.setHouse(house);
                    payment.setHouseName(house.getHouseName());
                    payment.setStartTime(new Date());
                    calendar.setTime(payment.getStartTime());//设置要操作的时间
                    calendar.add(Calendar.MONTH,1);//计算下次还款的时间
                    payment.setNextTime(calendar.getTime());
                    payment.setDeliveryNumber(1);
                    payment.setSumMoney(sumMoney);
                    payment.setCashState(0);
                    payment.setNumber(startValue);
                    payment.setRecordId(record.getId());
                    payment.setState(1);
                    payment.setResidueMoney(residueMoney);

                    if ((payment.getNumber()-payment.getDeliveryNumber())==0){
                        payment.setNextTime(null);
                        payment.setState(0);
                        record.setDealState(0);
                        recordMapper.updateByPrimaryKeySelective(record);
                    }
                    paymentMapper.insert(payment);



//                    租客信息
                    Tenant tenant = new Tenant();
                    tenant.setHouseId(houseId);
                    tenant.setUser(payerUser);
                    tenant.setState(0);
                    calendar.setTime(new Date());//设置要操作的时间
                    calendar.add(Calendar.MONTH,startValue);//计算搬离的时间
                    tenant.setTerminationTime(calendar.getTime());
//                    添加租客信息
                    tenantMapper.insert(tenant);

                    //短信提醒房东
                    this.verification(payeeUser.getPhone(),payerUser.getNickname(),record.getRecord());
//                   更新房屋可租房间数
                    house.setResidueRoom(house.getResidueRoom()-1);
                    if (house.getResidueRoom()<=0){
                        house.setState(1);
                    }
//                更新房屋状态
                    houseMapper.updateByPrimaryKey(house);
//                    0代表交易成功
                    return 0;
                }else {
//                    1代表余额不足
                    return 1;
                }
            }else {
                //订单对象封装
                record.setPayeeUser(payeeUser);
                record.setPayeeName(payeeUser.getUserName());
                record.setPayerUser(payerUser);
                record.setPayerName(payerUser.getUserName());
                record.setDealMoney(sumMoney);
                record.setHouse(house);
                record.setHouseName(house.getHouseName());
                record.setDealTime(new Date());
                record.setState(0);
                record.setDealState(3);
                record.setRecord(DateUtil.date2String1(new Date()));
                recordMapper.insert(record);
                //还款对象封装
                payment.setPayeeUser(payeeUser);
                payment.setPayeeName(payeeUser.getUserName());
                payment.setPayerUser(payerUser);
                payment.setPayerName(payerUser.getUserName());
                payment.setHouse(house);
                payment.setHouseName(house.getHouseName());
                payment.setCashState(2);
                payment.setNumber(startValue);
                payment.setRecordId(record.getId());
                payment.setState(2);
                paymentMapper.insert(payment);
//                2取消交易存在在订单中
                return 2;
            }

        }else {
//            3没有可租房间
            return 3;
        }

    }

    /**
     * 修改订单
     * @param paymentId
     * @param sumMoney
     * @param residueMoney
     * @param startValue
     * @return
     */
    @Override
    public Integer updateRecord(Integer paymentId, BigDecimal sumMoney, BigDecimal residueMoney, Integer startValue) {

        Payment payment = paymentMapper.selectByPrimaryKey(paymentId);
        Record record = recordMapper.selectByPrimaryKey(payment.getRecordId());

        //        获得付款人的账户余额
        Money payerUserMoney = moneyMapper.selectByUerId(payment.getPayerUser().getId());
//         获得收款人的账户余额
        Money payeeUserMoney = moneyMapper.selectByUerId(payment.getPayeeUser().getId());

        //        操作时间的对象
        Calendar calendar = Calendar.getInstance();
        if (payment.getHouse().getResidueRoom()>0){
            if (payerUserMoney.getMoney().compareTo(payment.getHouse().getPrice().add(new BigDecimal(1000)))==0||(payerUserMoney.getMoney().compareTo(payment.getHouse().getPrice().add(new BigDecimal(1000)))==1)){
//          扣除首付金额
                payerUserMoney.setMoney(payerUserMoney.getMoney().subtract(payment.getHouse().getPrice().add(new BigDecimal(1000))));
                moneyMapper.updateByUserId(payerUserMoney);
                //增加首付金额
                payeeUserMoney.setMoney(payeeUserMoney.getMoney().add(payment.getHouse().getPrice().add(new BigDecimal(1000))));
                moneyMapper.updateByUserId(payeeUserMoney);
//                更新订单状态
                record.setDealState(1);
                record.setDealMoney(sumMoney);
                //更新还款表
                payment.setResidueMoney(residueMoney);
                payment.setSumMoney(sumMoney);
                payment.setCashState(0);
                payment.setNumber(startValue);
                payment.setDeliveryNumber(1);
                payment.setStartTime(new Date());
                calendar.setTime(payment.getStartTime());//设置要操作的时间
                calendar.add(Calendar.MONTH,1);//计算下次还款的时间
                payment.setNextTime(calendar.getTime());
                payment.setState(1);
                if ((payment.getNumber()-payment.getDeliveryNumber())==0){
                    payment.setNextTime(null);
                    payment.setState(0);
                    record.setDealState(0);
                }

//                更新订单和付款
                recordMapper.updateByPrimaryKeySelective(record);
                paymentMapper.updateByPrimaryKeySelective(payment);
                //短信提醒房东
                this.verification(payment.getPayeeUser().getPhone(),payment.getPayerUser().getNickname(),record.getRecord());
                //                   更新房屋可租房间数
                payment.getHouse().setResidueRoom(payment.getHouse().getResidueRoom()-1);
                if (payment.getHouse().getResidueRoom()<=0){
                    payment.getHouse().setState(1);
                }
//                更新房屋状态
                houseMapper.updateByPrimaryKey(payment.getHouse());
                //交易成功
                return 0;
            }else {
//                    1代表余额不足
                return 1;
            }


        }else {
//            3没有可租房间
            return 2;
        }

    }

    /**
     *缴费
     * @param id
     * @return
     */
    @Override
    public Payment pay(Integer id) {

        Payment payment = paymentMapper.selectByPrimaryKey(id);
        Record record = recordMapper.selectByPrimaryKey(payment.getRecordId());

        //需要支付的金额
        BigDecimal money = payment.getHouse().getPrice();
        //        获得付款人的账户余额
        Money payerUserMoney = moneyMapper.selectByUerId(payment.getPayerUser().getId());
//         获得收款人的账户余额
        Money payeeUserMoney = moneyMapper.selectByUerId(payment.getPayeeUser().getId());

        //        操作时间的对象
        Calendar calendar = Calendar.getInstance();
        //判断账户余额是否充足
        if (payerUserMoney.getMoney().compareTo(money)==0||(payerUserMoney.getMoney().compareTo(money)==1)){
            //          扣除首付金额
            payerUserMoney.setMoney(payerUserMoney.getMoney().subtract(money));
            moneyMapper.updateByUserId(payerUserMoney);
            //增加首付金额
            payeeUserMoney.setMoney(payeeUserMoney.getMoney().add(money));
            moneyMapper.updateByUserId(payeeUserMoney);
            //计算剩余金额
            payment.setResidueMoney(payment.getResidueMoney().subtract(money));
            //计算已交次数
            payment.setDeliveryNumber(payment.getDeliveryNumber()+1);
            //计算时间
            payment.setStartTime(payment.getNextTime());
            //计算下次交钱时间
            calendar.setTime(payment.getNextTime());//设置要操作的时间
            calendar.add(Calendar.MONTH,1);//计算下次还款的时间
            payment.setNextTime(calendar.getTime());
            //如果次数已经还完
            if ((payment.getNumber() - payment.getDeliveryNumber()) == 0) {
                //设置还款状态
                payment.setState(0);
                //下次时间清零
                payment.setNextTime(null);
                //设置订单状态
                record.setDealState(0);
                //更新订单对象
                recordMapper.updateByPrimaryKeySelective(record);
            }
            //并更新付款对象
            paymentMapper.updateByPrimaryKeySelective(payment);
            //0代表还款成功
            return payment;
        }else {
//            2代表用户余额不足
            return null;
        }
    }

    /**
     * 房东退还押金
     * @param id
     * @return
     */
    @Override
    public Payment returnMoney(Integer id) {
        Payment payment = paymentMapper.selectByPrimaryKey(id);

        //需要支付的金额押金
        BigDecimal money = payment.getHouse().getPrice();
        //        获得付款人的账户余额
        Money payerUserMoney = moneyMapper.selectByUerId(payment.getPayerUser().getId());
//         获得收款人的账户余额
        Money payeeUserMoney = moneyMapper.selectByUerId(payment.getPayeeUser().getId());

        //        操作时间的对象
        Calendar calendar = Calendar.getInstance();
        //判断账户余额是否充足
        if (payeeUserMoney.getMoney().compareTo(money)==0||(payeeUserMoney.getMoney().compareTo(money)==1)){
            //          扣除押金金额
            payeeUserMoney.setMoney(payeeUserMoney.getMoney().subtract(money));
            moneyMapper.updateByUserId(payeeUserMoney);
            //增加押金金额
            payerUserMoney.setMoney(payerUserMoney.getMoney().add(money));
            moneyMapper.updateByUserId(payerUserMoney);
            //修改押金状态
            payment.setCashState(1);
            //更新订单状态
            paymentMapper.updateByPrimaryKeySelective(payment);
            return payment;

        }
        return null;
    }

    //    根据付款用户id查询
    @Override
    public List<Record> queryByPayerUserId(Integer payerUserId,Integer dealState) {
        return recordMapper.selectByPayerUserId(payerUserId,dealState);
    }

    /**
     * 根据收款人id查询
     * @param payeeUserId
     * @return
     */
    @Override
    public List<Record> queryByPayeeUserId(Integer payeeUserId,Integer dealState) {
        return recordMapper.selectByPayeeUserId(payeeUserId,dealState);
    }

    //    根据订单id修改订单支付状态
    @Override
    public List<Record> updateDealState(Integer userId,Integer id, Integer dealState) {
//        修改订单状态
        recordMapper.updateDealState(id,dealState);
//        查询最新订单列表
        return recordMapper.selectByPayerUserId(userId,dealState);
    }


    //    短信验证
    private void verification(String phone,String name,String record){

        String host = "https://feginesms.market.alicloudapi.com";
        String path = "/codeNotice";
        String method = "GET";
        String appcode = "a55b99e70e2b4860a0fe8056265719b8";//阿里云appCode
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        //测试可用默认短信模板,测试模板为专用模板不可修改,如需自定义短信内容或改动任意字符,请联系旺旺或QQ726980650进行申请
        String me = name+"|"+record;
        querys.put("param",me);
        //然后这里是手机号
        querys.put("phone", phone);
        //签名编号【联系旺旺客服申请，测试请用1】
        querys.put("sign", "500504");
        //模板编号【联系旺旺客服申请，测试请用1~21】
        querys.put("skin", "900727");
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
