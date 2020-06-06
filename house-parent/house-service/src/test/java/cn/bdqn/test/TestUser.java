package cn.bdqn.test;


import cn.bdqn.domain.Ocr;
import cn.bdqn.utils.DateUtil;
import cn.bdqn.utils.HttpUtils;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.aspectj.weaver.ast.Or;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TestUser {

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());//设置要操作的时间
        System.out.println(calendar.get(Calendar.DATE));
        calendar.add(Calendar.DATE, 1);
        System.out.println(DateUtil.date2String(calendar.getTime()));
    }

    @Test
    public void test()throws Exception{

        String host = "https://ocr2idcard.market.alicloudapi.com";
        String path = "/OcridCard";
        String method = "POST";
        String appcode = "e5cd97bb1e904429a0daf5b9b616431b";//这里是阿里云的AppCode
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        //改这里
        //这里是图片路径
        bodys.put("image", "http://182.92.168.223:8080/house/idCard/zheng.png");
        //或者base64
        //bodys.put("image", "data:image/jpeg;base64,........");   //jpg图片
        //bodys.put("image", "data:image/png;base64,........");   //png图片


        try {
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());//如不输出json, 请打开这行代码，打印调试头部状态码。
            //状态码: 200 正常；400 URL无效；401 appCode错误； 403 次数用完； 500 API网管错误
            //获取response的body
            String message = EntityUtils.toString(response.getEntity());
            //转换为json
            JSONObject jsonObject = JSONObject.parseObject(message);
            JSONObject ocr = JSONObject.parseObject(jsonObject.get("ocr").toString());
            String idCard =  (String) ocr.get("idCard");
            String name =  (String) ocr.get("name");
            System.out.println(idCard+"---"+name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
