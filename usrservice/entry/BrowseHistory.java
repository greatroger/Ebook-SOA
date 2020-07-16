package com.example.usrservice.entry;

import javax.naming.directory.SearchResult;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BrowseHistory implements Serializable {
    public int getUser_id() {
        return user_id;
    }

    public List<BrowseData> getBrowseDataList() {
        return browseDataList;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setBrowseDataList(List<BrowseData> browseDataList) {
        this.browseDataList = browseDataList;
    }

    int user_id;
    List<BrowseData> browseDataList = new ArrayList<>();

    public BrowseHistory(int id){
        user_id = id;
    }
    public BrowseHistory(int id, List<BrowseData> browseDataList){
        user_id = id;
        this.browseDataList = browseDataList;
    }

    public int getHistoryNum(){
        return browseDataList.size();
    }
    public void addBrowseData(BrowseData browseData){
        browseDataList.add(browseData);
    }
}
