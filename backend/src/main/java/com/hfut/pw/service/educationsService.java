package com.hfut.pw.service;

import com.hfut.pw.dao.educationMapper;
import com.hfut.pw.domain.education;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("educationsService")
public class educationsService {
    @Resource
    private educationMapper em;

    public List<education> getEducations() {
        List<education> list = em.getEducations();
        return list;
    }
}
