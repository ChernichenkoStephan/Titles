package com.titles.databaseservice.Controller;

import com.titles.databaseservice.Controller.Interfacies.DataBase;
import com.titles.databaseservice.Model.Paper;
import com.titles.databaseservice.cold.ColdDBService;
import com.titles.databaseservice.Controller.Interfacies.HotDataBase;
import com.titles.databaseservice.Model.HotPaper;
import org.json.simple.JSONObject;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class DBService {
    private DBService(){}
    public static DBService defaultService = new DBService();
    private DataBase hdb;
    private DataBase cdb;
    private Set<DataBase> dbs;
    public void init(HotDataBase hdb, ColdDBService cdb){
        this.cdb = cdb;
        this.hdb = hdb;
        this.dbs = Set.of(hdb, cdb);
    }

    public JSONObject saveRequest(JSONObject data) {
        Set<Paper> papers = JSONConverter.toPapers(data);
        JSONObject jsonObject = new JSONObject();
        for(DataBase db : dbs) db.savePaper(papers);
        if(data!=null)
        {
            jsonObject.put("status",1);
            jsonObject.put("message","Success");
        }
        else{
            jsonObject.put("status",0);
            jsonObject.put("message","Papers not found");
        }
        return jsonObject;
    }

    public JSONObject getRequest() {
        JSONObject papers = JSONConverter.papersToJSON(hdb.findAll());
        return papers;
    }
    public JSONObject getParamRequest(Integer id){
        JSONObject jsonObject = new JSONObject();
        JSONObject paper = JSONConverter.paperToJSON(hdb.findByID(id));
        if(!hdb.findByID(id).getSource().equals("source")) {
            jsonObject.put("papers", paper);
            jsonObject.put("status", 1);
            jsonObject.put("message", "Success");
        }
        else{
            jsonObject.put("papers", null);
            jsonObject.put("status", 0);
            jsonObject.put("message", "Paper not found");
        }
        return jsonObject;
    }
    public JSONObject getDaysRequest(Integer date){
        Date d = new Date();
        Set<Paper> sp = new HashSet<>();
        JSONObject jsonObject = new JSONObject();

        for (HotPaper hp : hdb.findAllForTime())
        {
            long milliseconds = d.getTime() - hp.getAddTime().getTime();
            int days = (int) (milliseconds / (24 * 60 * 60 * 1000));
            if((date==1)&&(!hp.getReaded()))
            {
                Integer id = hp.getHpID();
                hp.setReaded(true);
                hdb.saveHpPaper(hp,id);
                sp.add(hp.toPaper());
            }
            if((date!=1)&&(days<=date))
            {
                sp.add(hp.toPaper());
            }
        }
        JSONObject paper = JSONConverter.papersToJSON(sp);
        if(sp.size()!=0)
        {
            jsonObject.put("papers", paper);
            jsonObject.put("status", 1);
            jsonObject.put("message", "Success");
            return jsonObject;
        } else{
            jsonObject.put("papers", null);
            jsonObject.put("status", 0);
            jsonObject.put("message", "Papers not found");
        }
        return jsonObject;
    }
    public JSONObject deleteRequest(Integer paperID) {
        JSONObject jsonObject = new JSONObject();
        if (hdb.findByID(paperID) != null) {
            hdb.deleteByID(paperID);
            jsonObject.put("status", 1);
            jsonObject.put("message", "Success");
        } else {
            jsonObject.put("status", 0);
            jsonObject.put("message", "Paper not found");
        }
        return jsonObject;
    }
    public JSONObject deleteAll(Integer key)
    {
        JSONObject jsonObject = new JSONObject();
        hdb.deleteAll(key);
        if(key==228) {
            jsonObject.put("status", 1);
            jsonObject.put("message", "Success");
        } else {
            jsonObject.put("status", 0);
            jsonObject.put("message", "Key is incorrect");
        }
        return jsonObject;
    }
    public JSONObject checkRequest()
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", 1);
        jsonObject.put("message", "Working");
        return jsonObject;
    }

    public JSONObject findMem()
    {
        JSONObject jsonObject = new JSONObject();
        if(hdb.findMem().size()!=0) {
            jsonObject.put("mems", hdb.findMem());
            jsonObject.put("status", 1);
            jsonObject.put("message", "Success");
        } else{
            jsonObject.put("mems", null);
            jsonObject.put("status", 0);
            jsonObject.put("message", "Mems not found");
        }
        return jsonObject;
    }
}
