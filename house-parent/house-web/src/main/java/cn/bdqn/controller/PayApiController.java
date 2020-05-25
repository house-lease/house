package cn.bdqn.controller;

import alipay.config.AlipayConfig;
import cn.bdqn.domain.House;
import cn.bdqn.domain.Record;
import cn.bdqn.domain.User;
import cn.bdqn.service.RecordService;
import cn.bdqn.service.impl.RecordServiceImpl;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RequestMapping("/payApiController")
@Controller
public class PayApiController {

    @Autowired
    RecordService service;

    @RequestMapping("/Pay")
    public String pay(HttpServletRequest request, HttpServletResponse response)throws Exception{

        System.out.println(1);

        //制造商户订单号 字符串形式 ：年份+月份+日期+小时+分钟+秒数+毫秒
        Date vNow = new Date();
        String sNow = "";
        sNow = ""+(vNow.getYear()+1900)+(vNow.getMonth()+1)+vNow.getDate()+vNow.getHours()+vNow.getMinutes()+vNow.getSeconds()+(vNow.getTime() / 1000);

//        document.getElementById("WIDout_trade_no").value =  sNow;
//        document.getElementById("WIDsubject").value = "手机网站支付测试商品";
//        document.getElementById("WIDtotal_amount").value = "0.01";
//        document.getElementById("WIDbody").value = "购买测试商品0.01元";



            // 商户订单号，商户网站订单系统中唯一订单号，必填
            String out_trade_no = sNow;
                    //new String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
            // 订单名称，必填
            String subject ="五月份租金";
                    //new String(request.getParameter("WIDsubject").getBytes("ISO-8859-1"),"UTF-8");
            // 付款金额，必填
            String total_amount="1000";
                    //new String(request.getParameter("WIDtotal_amount").getBytes("ISO-8859-1"),"UTF-8");
            // 商品描述，可空
            String body = "xx市xx区xx小区x号楼x号房x月份租金";
                    //new String(request.getParameter("WIDbody").getBytes("ISO-8859-1"),"UTF-8");
            // 超时时间 可空
            String timeout_express="2m";
            // 销售产品码 必填
            String product_code="QUICK_WAP_WAY";
            /**********************/
            // SDK 公共请求类，包含公共请求参数，以及封装了签名与验签，开发者无需关注签名与验签
            //调用RSA签名方式
            AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY,AlipayConfig.SIGNTYPE);
            AlipayTradeWapPayRequest alipay_request=new AlipayTradeWapPayRequest();

            // 封装请求支付信息
            AlipayTradeWapPayModel model=new AlipayTradeWapPayModel();
            model.setOutTradeNo(out_trade_no);
            model.setSubject(subject);
            model.setTotalAmount(total_amount);
            model.setBody(body);
            model.setTimeoutExpress(timeout_express);
            model.setProductCode(product_code);
            alipay_request.setBizModel(model);
            // 设置异步通知地址
            alipay_request.setNotifyUrl(AlipayConfig.notify_url);
            // 设置同步地址
            alipay_request.setReturnUrl(AlipayConfig.return_url);

            // form表单生产
            String form = "";
            form = client.pageExecute(alipay_request).getBody();
            try {
                // 调用SDK生成表单
                form = client.pageExecute(alipay_request).getBody();
                //设置编码格式
                response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
                response.getWriter().write(form);//直接将完整的表单html输出到页面
                response.getWriter().flush();
                response.getWriter().close();
            } catch (AlipayApiException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return "";
    }

    @RequestMapping(value = "/notify_url",method = RequestMethod.POST)
    public String notify_url(HttpServletRequest request, HttpServletResponse response )throws Exception{

        //charset=UTF-8
        // &out_trade_no=20205251933121590406392
        // &method=alipay.trade.wap.pay.return
        // &total_amount=0.01
        // &sign=VYCwE1wnx532SOcEi%2BM9ahbl4%2F4YtBtW423ew%2BnZvcSAGYiSbs%2BETyJS0n1wyVYv3pVxInqYlCGE6hcQrrvEuymol4c7O%2BrfkRcmDN%2FYcNCAKM5xDGr9mhazcv4titXFwK2BBVdO%2B6E2lyyJsaOdcgMQ%2BfvjjSD%2F2dxOaqZjOIS9MJbi3cAMLwRH5%2FZ5m%2F%2FZlgZTqBZMpl2FNtnuF8TbLTU4qJl8NOJaZ5ZvMSxr8CtZnPGiqO4z%2Boxv46BNLpItSP1WR9Z5UJNsLW6OCh15bgJkIT5MHF50Mdp31uiMlIYEwm8lYOq602MzVToXgFkzWhj2CTwJIAWQbxjF5%2Bh%2FBQ%3D%3D
        // &trade_no=2020052522001405290500959047
        // &auth_app_id=2016102200741003
        // &version=1.0
        // &app_id=2016102200741003
        // &sign_type=RSA2
        // &seller_id=2088102180688961
        // &timestamp=2020-05-25+19%3A34%3A33

        //获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
            params.put(name, valueStr);
        }
        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
        //商户订单号

        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
        //支付宝交易号

        String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

        //交易状态
        String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
        //计算得出通知验证结果
        //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
        boolean verify_result = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, "RSA2");

        Record record = new Record();
        record.setRecord(out_trade_no);
        User user = new User();
        user.setUserName("张三");
        record.setPayeeUser(user);
        record.setPayerName(user.getUserName());
        User user1 = new User();
        user1.setUserName("李四");
        record.setPayeeUser(user1);
        record.setPayeeName(user1.getUserName());
        House house = new House();
        house.setHouseName("温馨的家");
        record.setHouse(house);
        record.setHouseName(house.getHouseName());
        record.setDealTime(new Date());
        record.setDealMoney(BigDecimal.valueOf(1000));
        record.setDealState(1);
        record.setState(0);

        service.save(record);

        if(verify_result){//验证成功
            //////////////////////////////////////////////////////////////////////////////////////////
            //请在这里加上商户的业务逻辑程序代码

            //——请根据您的业务逻辑来编写程序（以下代码仅作参考）——

            if(trade_status.equals("TRADE_FINISHED")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //如果签约的是可退款协议，退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
                //如果没有签约可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。
            } else if (trade_status.equals("TRADE_SUCCESS")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //如果签约的是可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。
            }

            //——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
            //out.clear();
            //out.println("success");	//请不要修改或删除
            System.out.println("success");

            //////////////////////////////////////////////////////////////////////////////////////////
        }else{//验证失败
            //out.println("fail");
            System.out.println("fail");
        }

        return "";
    }

    @RequestMapping(value = "/return_url",method = RequestMethod.GET)
    public String return_url
            (String charset,String out_trade_no,String method, String total_amount,String sign,String trade_no,String auth_app_id,String version,String app_id,String sign_type,String seller_id,String timestamp)
            throws  Exception{

        //charset=UTF-8
        // &out_trade_no=20205251933121590406392
        // &method=alipay.trade.wap.pay.return
        // &total_amount=0.01
        // &sign=VYCwE1wnx532SOcEi%2BM9ahbl4%2F4YtBtW423ew%2BnZvcSAGYiSbs%2BETyJS0n1wyVYv3pVxInqYlCGE6hcQrrvEuymol4c7O%2BrfkRcmDN%2FYcNCAKM5xDGr9mhazcv4titXFwK2BBVdO%2B6E2lyyJsaOdcgMQ%2BfvjjSD%2F2dxOaqZjOIS9MJbi3cAMLwRH5%2FZ5m%2F%2FZlgZTqBZMpl2FNtnuF8TbLTU4qJl8NOJaZ5ZvMSxr8CtZnPGiqO4z%2Boxv46BNLpItSP1WR9Z5UJNsLW6OCh15bgJkIT5MHF50Mdp31uiMlIYEwm8lYOq602MzVToXgFkzWhj2CTwJIAWQbxjF5%2Bh%2FBQ%3D%3D
        // &trade_no=2020052522001405290500959047
        // &auth_app_id=2016102200741003
        // &version=1.0
        // &app_id=2016102200741003
        // &sign_type=RSA2
        // &seller_id=2088102180688961
        // &timestamp=2020-05-25+19%3A34%3A33

        //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        params.put("charset",new String(charset.getBytes("ISO-8859-1"), "utf-8"));
        params.put("out_trade_no",new String(out_trade_no.getBytes("ISO-8859-1"), "utf-8"));
        params.put("method",new String(method.getBytes("ISO-8859-1"), "utf-8"));
        params.put("total_amount",new String(total_amount.getBytes("ISO-8859-1"), "utf-8"));
        params.put("sign",new String(sign.getBytes("ISO-8859-1"), "utf-8"));
        params.put("trade_no",new String(trade_no.getBytes("ISO-8859-1"), "utf-8"));
        params.put("auth_app_id",new String(auth_app_id.getBytes("ISO-8859-1"), "utf-8"));
        params.put("version",new String(version.getBytes("ISO-8859-1"), "utf-8"));
        params.put("app_id",new String(app_id.getBytes("ISO-8859-1"), "utf-8"));
        params.put("sign_type",new String(sign_type.getBytes("ISO-8859-1"), "utf-8"));
        params.put("seller_id",new String(seller_id.getBytes("ISO-8859-1"), "utf-8"));
        params.put("timestamp",new String(timestamp.getBytes("ISO-8859-1"), "utf-8"));


        Record record = new Record();
        record.setRecord(out_trade_no);
        User user = new User();
        user.setUserName("张三");
        record.setPayeeUser(user);
        record.setPayerName(user.getUserName());
        User user1 = new User();
        user1.setUserName("李四");
        record.setPayeeUser(user1);
        record.setPayeeName(user1.getUserName());
        House house = new House();
        house.setHouseName("温馨的家");
        record.setHouse(house);
        record.setHouseName(house.getHouseName());
        record.setDealTime(new Date());
        record.setDealMoney(BigDecimal.valueOf(1000));
        record.setDealState(1);
        record.setState(0);


//        Map requestParams = request.getParameterMap();
//        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
//            String name = (String) iter.next();
//            String[] values = (String[]) requestParams.get(name);
//            String valueStr = "";
//            for (int i = 0; i < values.length; i++) {
//                valueStr = (i == values.length - 1) ? valueStr + values[i]
//                        : valueStr + values[i] + ",";
//            }
//            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
//            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
//            params.put(name, valueStr);
//        }

        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
        //商户订单号

        //String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

        //支付宝交易号

        //String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
        //计算得出通知验证结果
        //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
        boolean verify_result = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, "RSA2");

        if(verify_result){//验证成功
            //////////////////////////////////////////////////////////////////////////////////////////
            //请在这里加上商户的业务逻辑程序代码
            //该页面可做页面美工编辑
//            out.clear();
//            out.println("验证成功<br />");
            //——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

            service.save(record);
            System.out.println("验证成功");

            //////////////////////////////////////////////////////////////////////////////////////////
        }else{
            //该页面可做页面美工编辑
//            out.clear();
//            out.println("验证失败");
            record.setDealState(0);
            service.save(record);
            System.out.println("验证失败");
        }
        return "";
    }
}
