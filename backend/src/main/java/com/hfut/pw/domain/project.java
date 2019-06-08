package com.hfut.pw.domain;

public class project {
    private Integer id;
    private String prjname;
    private String pos;
    private String intro;
    private String time;
    private String duty;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPrjname(String prjname) {
        this.prjname = prjname;
    }

    public String getPrjname() {
        return this.prjname;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getPos() {
        return this.pos;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getIntro() {
        return this.intro;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return this.time;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getDuty() {
        return this.duty;
    }
}
