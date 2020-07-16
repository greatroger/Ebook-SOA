package com.example.usrservice.entry;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Usr_Loc implements Serializable {
    public int getUsr_id() {
        return usr_id;
    }

    public List<LocInfor> getLocList() {
        return locList;
    }

    public void setUsr_id(int usr_id) {
        this.usr_id = usr_id;
    }

    public void setLocList(List<LocInfor> locList) {
        this.locList = locList;
    }

    int usr_id;
    List<LocInfor> locList = new ArrayList<>();

    public Usr_Loc(int id){
        usr_id = id;
    }
    public Usr_Loc(int id, List<LocInfor> locList){
        usr_id = id; this.locList = locList;
    }

    public void addLoc(LocInfor loc){
        locList.add(loc);
    }
}
