package cn.bdqn.controller;

import cn.bdqn.domain.Start;
import cn.bdqn.service.StartService;
import cn.bdqn.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/start")
public class StartController {

    @Autowired
    private StartService startService;

    @RequestMapping("/queryAll")
    @ResponseBody
    public Result queryAll(){

        Result result = new Result();
        try {
            List<Start> starts = startService.queryAll();
            result.setData(starts);
            result.setMessage("查询成功");
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("查询失败");
            return result;
        }
    }

    @RequestMapping("/queryByStartValue")
    @ResponseBody
    public Result queryByStartValue(Integer starValue){
        Result result = new Result();
        try {
            List<Start> starts = startService.queryByStartValue(starValue);
            result.setData(starts);
            result.setMessage("查询成功~");
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("查询失败");
            return result;
        }
    }

}
