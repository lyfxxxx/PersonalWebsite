package com.hfut.pw.controller;

import com.hfut.pw.domain.education;
import com.hfut.pw.service.educationsService;
import com.hfut.pw.util.resultFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/api")
public class educationsController {
    @Resource
    private educationsService es;

    @CrossOrigin
    @RequestMapping(value = "/educations", method = RequestMethod.GET)
    @ResponseBody
    public Object getEducations() {
        List<education> list = es.getEducations();
        return resultFactory.buildSuccessRes(list);
    }

    @CrossOrigin
    @RequestMapping(value = "/education", method = RequestMethod.POST)
    @ResponseBody
    public Object getEducation(@RequestParam(value = "id") Integer id) {
        education edu = es.getEducationById(id);
        if(edu != null) {
            return resultFactory.buildSuccessRes(edu);
        } else {
            return resultFactory.buildFailedRes("查询不到该项数据！");
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/updateEdu", method = RequestMethod.POST)
    @ResponseBody
    public Object updateEdu(@RequestBody education edu) {
        if(es.updateEducation(edu)) {
            return resultFactory.buildSuccessRes(null);
        } else {
            return resultFactory.buildFailedRes("传输数据失败或查询不到该项数据！");
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/deleteEdu", method = RequestMethod.POST)
    @ResponseBody
    public Object deleteEdu(@RequestParam(value = "id") Integer id) {
        List<education> list = es.deleteEducation(id);
        if(list != null) {
            return resultFactory.buildSuccessRes(list);
        } else {
            return resultFactory.buildFailedRes("传输数据失败或查询不到该项数据！");
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/insertEdu", method = RequestMethod.POST)
    @ResponseBody
    public Object insertEdu(@RequestBody education edu) {
        if(es.insertEdu(edu)) {
            return resultFactory.buildSuccessRes(null);
        } else {
            return resultFactory.buildFailedRes("传输数据失败");
        }
    }
}
