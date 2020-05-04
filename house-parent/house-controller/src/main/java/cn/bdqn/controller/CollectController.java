package cn.bdqn.controller;

import cn.bdqn.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/collect")
public class CollectController {

    @Autowired
    private CollectService service;

    @RequestMapping("/addCollectRecord")
    @ResponseBody
    public String addCollectRecord() {

        try {
            service.insertCollectByUserIdAndHouseId(1, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return "index";

    }

    @RequestMapping("/deleteInfo")
    @ResponseBody
    public String deleteInfo() {

        service.deleteCollectByCollectId(1);

        return "index";
    }

    @RequestMapping("/queryInfoByUserId")
    @ResponseBody
    public String queryInfoByUserId() {

        int num = service.queryInfoByUser_id(1);

        return "" + num;

    }

}
