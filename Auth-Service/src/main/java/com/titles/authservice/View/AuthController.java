package com.titles.authservice.View;

import com.titles.authservice.Controller.AuthService;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

// docker build -t auth-service .

@RestController
public class AuthController {
private AuthService service = AuthService.defaultService;

    @GetMapping("/check")
    public JSONObject check()  {
        return service.check();
    }

    @GetMapping("/login")
    @ResponseBody
    public JSONObject loginRequest(@RequestParam(value = "login",required = false) String login,
                                   @RequestParam(value = "password",required = false) String password) {
        return service.loginRequest(login, password);
    }
    @PostMapping("/registration")
    @ResponseBody
    public JSONObject registrationRequest(@RequestParam(value = "login",required = false) String login,
                                          @RequestParam(value = "password",required = false) String password) {
        return service.registrationRequest(login,password);
    }
    @GetMapping("/users")
    @ResponseBody
    public JSONObject usersRequest(@RequestParam(value = "key",required = false) String key) {
        return service.usersRequest(key);
    }
    @PutMapping("/update")
    @ResponseBody
    public JSONObject putRequest(@RequestParam(value = "login",required = false) String login,
                                 @RequestParam(value = "password",required = false) String password,
                                 @RequestParam(value = "newlogin",required = false) String newlogin,
                                 @RequestParam(value = "newpassword",required = false) String newpassword) {
        return service.putRequest(login,password,newlogin,newpassword);
    }

}
