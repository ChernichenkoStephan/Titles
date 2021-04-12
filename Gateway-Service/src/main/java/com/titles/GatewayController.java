package com.titles;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class GatewayController {

    GatewayService service = GatewayService.defaultService;

    @GetMapping("/check")
    public JSONObject check()  {
        return service.check();
    }

    @GetMapping("/selection")
    public JSONObject getSelection(@RequestParam Integer userID)  {
        return service.getSelection(userID);
    }

    @GetMapping("/profile/new")
    @ResponseBody
    public JSONObject newProfile(@RequestParam Integer userID)  {
        return service.newProfile(userID);
    }

    @PostMapping("/profile")
    @ResponseBody
    public JSONObject updateProfile(@RequestParam Integer userID, @RequestBody JSONObject preferences)  {
        return service.updateProfile(userID, preferences);
    }

    @GetMapping("/sign-in")
    @ResponseBody
    public JSONObject signIN(@RequestParam String login, @RequestParam String password)  {
        return service.signIN(login, password);
    }

    @GetMapping("/sign-up")
    @ResponseBody
    public JSONObject signUP(@RequestParam String login, @RequestParam String password)  {
        return service.signUP(login, password);
    }

    @GetMapping("/auth-update")
    @ResponseBody
    public JSONObject updateAccount(@RequestParam String currentLogin,
                                    @RequestParam String currentPassword,
                                    @RequestParam String newLogin,
                                    @RequestParam String newPassword)  {
        return service.updateAccount(currentLogin, currentPassword, newLogin, newPassword);
    }

}
