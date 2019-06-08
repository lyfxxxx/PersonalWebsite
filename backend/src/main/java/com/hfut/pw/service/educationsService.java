package com.hfut.pw.service;

import com.hfut.pw.dao.educationMapper;
import com.hfut.pw.domain.education;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("educationsService")
public class educationsService {
    @Resource
    private educationMapper em;

    public List<education> getEducations() {
        List<education> list = em.getEducations();
        if(list == null) {
            list = new ArrayList<>();
        }
        return list;
    }

    public education getEducationById(Integer id) {
        education edu = em.getEducationById(id);
        return edu;
    }

    public boolean updateEducation(education edu) {
        if(edu == null) {
            return false;
        }
        education test = this.getEducationById(edu.getId());
        if(test == null) {
            return false;
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("major", edu.getMajor());
            map.put("id", edu.getId());
            map.put("school", edu.getSchool());
            map.put("stage", edu.getStage());
            map.put("time", edu.getTime());
            em.updateEduById(map);
            return true;
        }
    }

    public List<education> deleteEducation(Integer id) {
        List<education> list = null;
        em.deleteEduById(id);
        list = em.getEducations();
        return list;
    }

    public boolean insertEdu(education edu) {
        Map<String, Object> map = new HashMap<>();
        if(edu == null)
            return false;
        else {
            map.put("school", edu.getSchool());
            map.put("stage", edu.getStage());
            map.put("time", edu.getTime());
            map.put("major", edu.getMajor());
            em.insertEdu(map);
            return true;
        }
    }
}
