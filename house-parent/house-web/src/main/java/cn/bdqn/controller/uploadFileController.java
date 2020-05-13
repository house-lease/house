package cn.bdqn.controller;

import cn.bdqn.domain.HouseImage;
import cn.bdqn.service.HouseImageService;
import cn.bdqn.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
@RequestMapping("/upload")
public class uploadFileController {

    @Autowired
    private HouseImageService houseImageService;

    @RequestMapping("/image")
    @ResponseBody
    public Result uploadFile(HttpServletRequest request,
                             @RequestParam("image") MultipartFile image, Integer houseId, Integer imagePlaceId)throws Exception{


        HouseImage houseImage = new HouseImage();
        Result result = new Result();
        try{

            // 1、得到文件上传的路径
            String path = request.getServletContext().getRealPath("/image/");
            File destPath = new File(path);
            if(!destPath.exists()){
                destPath.mkdirs();
            }
            // 获得原始名称
            String originalFilename = image.getOriginalFilename();
            // 文件上传
            image.transferTo(new File(destPath,originalFilename));
            //封装数据
            houseImage.setImageUrl("http://localhost:8080/house/image/"+originalFilename);
            houseImage.setState(0);
            //添加对象
            houseImageService.save(houseImage,houseId,imagePlaceId);
            result.setData(true);
            result.setMessage("上传成功~");
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setData(false);
            result.setMessage("上传失败~");
            return result;
        }

    }
}
