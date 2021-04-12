package com.titles.profileservice.View;

import com.titles.profileservice.Controller.ProfileService;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ProfileController {

    private final ProfileService profileService = ProfileService.defaultService;

    /* ----------------------- System requests ----------------------- */

    @GetMapping("/check")
    public JSONObject complicatedTest()  {
        return profileService.test();
    }

    @GetMapping("/clear")
    public JSONObject clear()  {
        return profileService.clear();
    }

    /* ----------------------- Profiles requests ----------------------- */

    @GetMapping("/profiles")
    public JSONObject getProfiles()  {
        return profileService.getProfiles();
    }

    @GetMapping("/profiles/profile")
    @ResponseBody
    public JSONObject getProfile(@RequestParam Integer userID) { return profileService.getProfile(userID); }

    @PostMapping("/profiles/profile")
    @ResponseBody
    public JSONObject setPreferences(@RequestParam Integer userID, @RequestBody JSONObject preferences) {
        return profileService.setPreferences(userID, preferences);
    }

    @GetMapping("/profiles/profile/new")
    @ResponseBody
    public JSONObject newProfile(@RequestParam Integer userID) { return profileService.newProfile(userID); }

    /* ----------------------- Mem's requests ----------------------- */

    @GetMapping("/mems")
    public JSONObject getMems()  {
        return profileService.getMems();
    }

    @PostMapping("/mems")
    @ResponseBody
    public JSONObject setMems(@RequestBody JSONObject mems)  { return profileService.setMems(mems); }

    /* ----------------------- Bubbles requests ----------------------- */

    @GetMapping("/bubbles")
    public JSONObject getBubbles()  {
        return profileService.getBubbles();
    }

    @PostMapping("/bubbles")
    @ResponseBody
    public JSONObject setBubbles(@RequestBody JSONObject bubbles)  {
        return profileService.setBubbles(bubbles);
    }

}
