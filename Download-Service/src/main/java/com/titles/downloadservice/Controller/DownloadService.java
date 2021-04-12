package com.titles.downloadservice.Controller;

import com.titles.downloadservice.Controller.Interfaces.Sender;
import com.titles.downloadservice.Controller.Interfaces.Source;
import com.titles.downloadservice.Model.Paper.Paper;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * DownloadService
 */
@Service
public class DownloadService {

    private Sender sender;
    private List<Source> sources;

    /**
     * Singleton object
     */
    public static DownloadService defaultService = new DownloadService();

    private DownloadService() { }

    /**
     * Init method for singleton
     * @param sender Entity that sends data to other services
     * @param sources Entities for data fetching
     */
    public void init(Sender sender, List<Source> sources) {
        this.sender = sender;
        this.sources = sources;
    }

    /**
     * Fetch data from api's method
     * @return list of united data from api's
     */
    private List<Paper> fetchData() {
        ArrayList<Paper> res = new ArrayList<>();
        for (Source s: sources) {
            res.addAll(s.getData());
        }
        return res;
    }

    /**
     * Method for full titles network fetch
     * @param data data to send to db
     * @return proper response with massage and code
     */
    private JSONObject sendData(List<Paper> data) {
        if (sender.send(data) == 0) return makeResponce(0, "Failure, bo data loaded");;
        return makeResponce(1, "Success");
    }

    public JSONObject test(Integer key) {
        JSONObject res = new JSONObject();
        if (key == 322) {
            List<Paper> data = fetchData();// data
            res = JSONConverter.toJSON(data);
        }
        return res;
    }

    /**
     * Method for full titles network fetch
     * @return proper response with massage and code
     */
    public JSONObject fetch() {
        List<Paper> data = fetchData();
        if (data.isEmpty())
            return makeResponce(0, "Failure, bo data loaded");;
        return makeResponce(1, "Success");
    }

    public JSONObject check() {
        return makeResponce(1, "Working right, everything is fine");
    }

    /**
     * Method for making custom json response
     * @param code response code
     * @param massage response massage
     * @return custom json response
     */
    private JSONObject makeResponce(Integer code, String massage) {
        JSONObject resp = new JSONObject();
        resp.put("code", code);
        resp.put("message", massage);
        return resp;
    }

}



