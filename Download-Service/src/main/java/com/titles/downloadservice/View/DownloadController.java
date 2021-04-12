package com.titles.downloadservice.View;

import com.titles.downloadservice.Controller.DownloadService;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DownloadController {

    DownloadService service = DownloadService.defaultService;

    @GetMapping("/check")
    public JSONObject check()  {
        return service.check();
    }

    @GetMapping("/test")
    public JSONObject fetchTest(@RequestParam Integer key)  {
        return service.test(key);
    }

    @GetMapping("/fetch")
    public JSONObject fetch()  {
        return service.fetch();
    }

}