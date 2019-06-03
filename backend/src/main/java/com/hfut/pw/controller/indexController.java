package com.hfut.pw.controller;

import com.hfut.pw.domain.image;
import com.hfut.pw.domain.personalInfo;
import com.hfut.pw.service.indexService;
import com.hfut.pw.util.resultFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/api")
public class indexController {
    @Resource
    private indexService is;

    @CrossOrigin
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @ResponseBody
    public Object getIndexInfo() {
        List<personalInfo> list = is.getPersonalInfo();
        String path = is.getPersonalPhoto();
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("info", list);
        dataMap.put("personal", path);
        return resultFactory.buildSuccessRes(dataMap);
    }

    @CrossOrigin
    @RequestMapping(value = "/getEditInfo", method = RequestMethod.GET)
    @ResponseBody
    public Object getInfo() {
        List<personalInfo> list = is.getPersonalInfo();
        return resultFactory.buildSuccessRes(list);
    }

    @CrossOrigin
    @RequestMapping(value = "/insertNewInfo", method = RequestMethod.POST)
    @ResponseBody
    public Object insertNewIntro(@RequestParam(value = "newInfo") String newInfo) {
        is.insertNewIntro(newInfo);
        return resultFactory.buildSuccessRes(null);
    }

    @CrossOrigin
    @RequestMapping(value = "/updateInfo", method = RequestMethod.POST)
    @ResponseBody
    public Object updateIntro(@RequestBody personalInfo pi) {
        is.updateIntro(pi.getIntro(), pi.getId());
        return resultFactory.buildSuccessRes(null);
    }

    @CrossOrigin
    @RequestMapping(value = "/deleteInfo", method = RequestMethod.POST)
    @ResponseBody
    public Object deleteIntro(@RequestBody personalInfo pi) {
        if(is.deleteIntro(pi.getId())) {
            return resultFactory.buildSuccessRes(null);
        } else {
            return resultFactory.buildFailedRes("该项不存在！");
        }
    }

    @CrossOrigin
    @PostMapping(value = "/upload/pp")
    @ResponseBody
    public Object uploadPersonalPhoto(@RequestParam(value = "img", required = true) MultipartFile file,
                                      @RequestParam(value = "imgName") String fileName) {
        if(!file.isEmpty()) {
            String uploadPath = "uploads/pp/" + fileName;
            try {
                String path = ResourceUtils.getURL("classpath:static/").getPath() + uploadPath;
                File newFile = new File(path);
                if (newFile.exists()) {
                    newFile.delete();
                }
                System.out.println(uploadPath);
                System.out.println(path);
                newFile.createNewFile();
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(newFile));
                out.write(file.getBytes());
                out.flush();
                out.close();
                is.changePersonalPhoto(uploadPath);
            } catch(FileNotFoundException e) {
                e.printStackTrace();
                return resultFactory.buildFailedRes("上传路径未找到！");
            } catch(IOException e) {
                e.printStackTrace();
                return resultFactory.buildFailedRes("原图删除失败或写入文件失败！");
            }
            return resultFactory.buildSuccessRes(uploadPath);
        }
        else {
            return resultFactory.buildFailedRes("未接收到前端传输文件！");
        }
    }
}
