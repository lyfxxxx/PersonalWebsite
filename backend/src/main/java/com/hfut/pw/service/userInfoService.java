package com.hfut.pw.service;

import com.hfut.pw.dao.userInfoMapper;
import com.hfut.pw.domain.userInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userInfoService")
public class userInfoService {
    @Resource
    private userInfoMapper uim;

    public userInfo getUserByName(String name) {
        return uim.getUserInfoByName(name);
    }
}
