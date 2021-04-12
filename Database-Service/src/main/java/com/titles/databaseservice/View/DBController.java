package com.titles.databaseservice.View;

import com.titles.databaseservice.Controller.DBService;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
public class DBController {
private DBService dbService = DBService.defaultService;

    @PostMapping("/papers")
    @ResponseBody
    public JSONObject saveRequest(@RequestBody JSONObject data) {
        return dbService.saveRequest(data);
    }
    @GetMapping("/papers")
    @ResponseBody
    public JSONObject getRequest(){return dbService.getRequest();}
    @GetMapping("/papers/paper")
    @ResponseBody
    public JSONObject getParamRequest(@RequestParam (value = "id",required = false) Integer id){return dbService.getParamRequest(id);}
    @GetMapping("/daypapers")
    @ResponseBody
    public JSONObject getDaysRequest(@RequestParam (value = "period",required = false) Integer date){return dbService.getDaysRequest(date);}
    @GetMapping("/deletepaper")
    @ResponseBody
    public JSONObject deleteRequest(@RequestParam (value = "id",required = false) Integer id){return dbService.deleteRequest(id);}
    @GetMapping("/clear")
    @ResponseBody
    public JSONObject deleteAll(@RequestParam (value = "key",required = false) Integer key){return dbService.deleteAll(key);}
    @GetMapping("/check")
    @ResponseBody
    public JSONObject checkRequest(){return dbService.checkRequest();}
    @GetMapping("/mems")
    @ResponseBody
    public JSONObject memRequest(){return dbService.findMem();}

}
