package com.hfut.pw.service;

import com.hfut.pw.dao.projectMapper;
import com.hfut.pw.domain.education;
import com.hfut.pw.domain.project;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("projectsService")
public class projectsService {
    @Resource
    private projectMapper pm;

    public List<project> getProjects() {
        return pm.getProjects();
    }

    public project getProjById(Integer id) {
        project proj = pm.getProjById(id);
        return proj;
    }

    public boolean updateProj(project proj) {
        if(proj == null) {
            return false;
        }
        project test = this.getProjById(proj.getId());
        if(test == null) {
            return false;
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("id", proj.getId());
            map.put("prjname", proj.getPrjname());
            map.put("pos", proj.getPos());
            map.put("intro", proj.getIntro());
            map.put("time", proj.getTime());
            map.put("duty", proj.getDuty());
            pm.updateProjById(map);
            return true;
        }
    }

    public List<project> deleteProj(Integer id) {
        List<project> list = null;
        pm.deleteProjById(id);
        list = pm.getProjects();
        return list;
    }

    public boolean insertProj(project proj) {
        Map<String, Object> map = new HashMap<>();
        if(proj == null)
            return false;
        else {
            map.put("prjname", proj.getPrjname());
            map.put("pos", proj.getPos());
            map.put("intro", proj.getIntro());
            map.put("time", proj.getTime());
            map.put("duty", proj.getDuty());
            pm.insertProj(map);
            return true;
        }
    }
}
