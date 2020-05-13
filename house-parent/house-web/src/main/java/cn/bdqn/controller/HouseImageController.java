package cn.bdqn.controller;

import cn.bdqn.domain.HouseImage;
import cn.bdqn.service.HouseImageService;
import cn.bdqn.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/houseImage")
public class HouseImageController {


    //房屋图片业务类
    @Autowired
    private HouseImageService houseImageService;


    /**
     * 根据房屋id和图片位置id查询
     * @param houseId
     * @param imagePlaceId
     * @return
     */
    @RequestMapping("/query")
    @ResponseBody
    public Result queryByHouseIdAndImagePlaceId(Integer houseId,Integer imagePlaceId){

        Result result = new Result();
        try{
            List<HouseImage> houseImages = houseImageService.queryByHouseIdAndImagePlaceId(houseId,imagePlaceId);
            result.setData(houseImages);
            result.setMessage("查询成功~");
            return result;
        }catch (Exception e){
            result.setMessage("查询失败~");
            e.printStackTrace();
            return result;
        }
    }

}
