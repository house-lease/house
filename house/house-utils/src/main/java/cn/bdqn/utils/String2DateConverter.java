package cn.bdqn.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期格式转化
 */
public class String2DateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String source) {
        //判断是否等于空
        if (source==null){
            return null;
        }
        try{
            //创建日期格式化对象
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(source);//返回结果
        }catch (Exception e){
            try{
                //创建日期格式化对象
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                return sdf.parse(source);//返回结果
            }catch (Exception e1){
                throw new RuntimeException("参数异常");
            }
        }
    }
}
