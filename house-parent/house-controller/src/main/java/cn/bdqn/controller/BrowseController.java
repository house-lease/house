package cn.bdqn.controller;

import cn.bdqn.service.BrowseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/browse")
public class BrowseController {

    @Autowired
    private BrowseService service;

    @RequestMapping("/addInfo")
    @ResponseBody
    public String addInfo(){
        int num = 0;
        try {
            num = service.insertBrowseByUserIdAndHouseId(1,1);
        }catch (Exception e){
            e.printStackTrace();
        }

        return ""+num;
    }

    @RequestMapping("/deleteInfo")
    @ResponseBody
    public String deleteInfo(){

        service.deleteBrowseByBrowseId(1);

        return "index";
    }

    @RequestMapping("/queryInfoByUserId")
    @ResponseBody
    public String queryInfoByUserId(){

        int num = service.queryInfoByUser_id(1);

        return ""+num;

    }

}
