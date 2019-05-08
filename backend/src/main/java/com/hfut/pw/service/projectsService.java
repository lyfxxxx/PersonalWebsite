package com.hfut.pw.service;

import com.hfut.pw.dao.projectMapper;
import com.hfut.pw.domain.project;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("projectsService")
public class projectsService {
    @Resource
    private projectMapper pm;

    public List<project> getProjects() {
        return pm.getProjects();
    }
}
