package com.titles.selectionservice.View;

import com.titles.selectionservice.Controller.SelectionService;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
public class SelectionController {

    SelectionService service = SelectionService.defaultService;

    @GetMapping("/check")
    public JSONObject test(@RequestParam Integer key)  {
        return service.test();
    }

    @PostMapping("/papers")
    @ResponseBody
    public JSONObject update(@RequestBody JSONObject data) {
        return service.update(data);
    }

    @GetMapping("/selections/selection")
    public JSONObject getSelection(@RequestParam Integer userID) {
        return service.getSelection(userID);
    }

    @GetMapping("/selections")
    public JSONObject getSelections(@RequestParam Integer key)  { return service.getSelections(key); }

}

