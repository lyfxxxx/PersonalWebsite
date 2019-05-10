package com.hfut.pw.controller;

import com.hfut.pw.domain.userInfo;
import com.hfut.pw.service.userInfoService;
import com.hfut.pw.util.resultFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/api")
public class userInfoController {
    @Resource
    private userInfoService uis;

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object LoginValid(@RequestBody userInfo user) {
        String failMessage = new String("用户名或密码不正确！");
        userInfo inputUser = uis.getUserByName(user.getName());
        if(user.getPassword().equals(inputUser.getPassword()))
            return resultFactory.buildSuccessRes(user);
        else
            return resultFactory.buildFailedRes(failMessage);
    }
}
