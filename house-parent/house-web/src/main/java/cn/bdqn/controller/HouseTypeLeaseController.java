package cn.bdqn.controller;


import cn.bdqn.domain.HouseLease;
import cn.bdqn.domain.HouseType;
import cn.bdqn.service.HouseLeaseService;
import cn.bdqn.service.HouseTypeService;
import cn.bdqn.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/houseTypeLease")
public class HouseTypeLeaseController {


    @Autowired
    private HouseTypeService houseTypeService;

    @Autowired
    private HouseLeaseService houseLeaseService;

    /**
     * 查询房屋类型
     * @return
     */
    @RequestMapping("/type")
    @ResponseBody
    public Result queryAllType(){

        Result result = new Result();
        try {
            List<HouseType> houseTypes = houseTypeService.queryAll();
            result.setData(houseTypes);
            result.setMessage("查询成功");
            return  result;
        }catch (Exception e){
            result.setMessage("查询失败");
            return  result;
        }

    }


    /**
     * 查询房屋租赁类型
     * @return
     */
    @RequestMapping("/lease")
    @ResponseBody
    public Result queryAllLease(){

        Result result = new Result();
        try {
            List<HouseLease> houseLeases = houseLeaseService.queryAll();
            result.setData(houseLeases);
            result.setMessage("查询成功");
            return  result;
        }catch (Exception e){
            result.setMessage("查询失败");
            return  result;
        }

    }

}
