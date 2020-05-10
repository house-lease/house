package cn.bdqn.controller;

import cn.bdqn.domain.Address;
import cn.bdqn.service.AddressService;
import cn.bdqn.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @RequestMapping("/queryByParent")
    @ResponseBody
    public Result queryByParent(){
        Result result = new Result();
        try{
            //调用查询父类地理位置的方法
            List<Address> addresses = addressService.queryByParent();
            result.setData(addresses);
            result.setMessage("查询成功~");
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("查询成功~");
            return result;
        }
    }
}
