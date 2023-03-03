package com.yangxinyu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yangxinyu.constant.MessageConstant;
import com.yangxinyu.entity.PageResult;
import com.yangxinyu.entity.QueryPageBean;
import com.yangxinyu.entity.Result;
import com.yangxinyu.entity.Setmeal;
import com.yangxinyu.qiniu.RedisConstant;
import com.yangxinyu.service.SetmealService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    @Reference
    private SetmealService setmealService;

    /**
     * 上传图片
     * @param picture
     * @return
     */
    @RequestMapping("/uploadPicture")
    public Result uploadPicture(MultipartFile picture){
        try {
            //获取图片的字节数组
            byte[] bytes = picture.getBytes();
            //获取原始文件名
            String pictureName = picture.getOriginalFilename();
            //截取后缀
            int index = pictureName.indexOf(".");
            String suffix  = pictureName.substring(index);
            //生成新的文件名
            String newPictureName = UUID.randomUUID().toString() + suffix;
            //上传图片
            setmealService.uploadPicture(bytes,newPictureName);

            return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS, RedisConstant.PICTURE_SPACE_DOMAINNAME+"/"+newPictureName);
        } catch (Exception e) {
            return  new Result(false,MessageConstant.PIC_UPLOAD_FAIL);
        }
    }


    /**
     * 添加套餐游
     * @param travelgroupIds
     * @param setmeal
     * @return Result
     */
    @RequestMapping("/addSetmeal")
    public Result addSetmeal(@RequestParam("travelgroupIds") Integer[] travelgroupIds,
                             @RequestParam(value = "setmealId",required = false) Integer setmealId,
                             @RequestBody Setmeal setmeal){

        try {
            setmealService.addSetmeal(travelgroupIds,setmealId,setmeal);
            return new Result(true,MessageConstant.ADD_SETMEAL_SUCCESS);
        } catch (Exception e) {
            return new Result(false,MessageConstant.ADD_SETMEAL_FAIL);
        }
    }

    /**
     * 分页查询套餐游
     * @param queryPageBean
     * @return
     */
    @RequestMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        System.out.println(queryPageBean.getCurrentPage()+queryPageBean.getQueryString());
        try {
            PageResult pageResult = setmealService.findPage(queryPageBean);
            return new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,pageResult);
        } catch (Exception e) {
            return new Result(false,MessageConstant.QUERY_SETMEAL_FAIL);
        }
    }

    /**
     * 获取套餐简单信息，不包括组团和自由行
     * @param id
     * @return
     */
    @RequestMapping("/getSetmealByIdSimple2")
    public Result getSetmealByIdSimple(Integer id){
        Setmeal setmeal = setmealService.getSetmealByIdSimple(id);
        return new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,setmeal);
    }

    @RequestMapping("/getTravelGroupIds")
    public Result getTravelGroupIds(Integer id){
        List<Integer> travelGroupIds = setmealService.getTravelGroupIds(id);
        return new Result(true,"查询跟团游id成功！",travelGroupIds);
    }

    @RequestMapping("/deleteById")
    public Result deleteById(Integer id){
        try {
            setmealService.deleteById(id);
            return new Result(false,"删除套餐成功！");
        } catch (Exception e) {
            return new Result(true,"删除套餐失败！");
        }
    }
}
