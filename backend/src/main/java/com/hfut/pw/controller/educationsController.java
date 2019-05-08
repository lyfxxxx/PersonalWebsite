package com.hfut.pw.controller;

import com.hfut.pw.domain.education;
import com.hfut.pw.service.educationsService;
import com.hfut.pw.util.resultFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
