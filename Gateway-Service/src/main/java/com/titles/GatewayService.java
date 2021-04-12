package com.titles;

import org.json.simple.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class GatewayService {

    private final RestTemplate restTemplate;

    public static GatewayService defaultService = new GatewayService();

    private final String selectionerRoute = "http://selection-service:8080/selectioner/";
    private final String profilerRoute = "http://profile-service:8080/profiler/";
    private final String authorizerRoute = "http://auth-service:8080/authorizer/";

    private GatewayService() {
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        this.restTemplate = restTemplateBuilder.build();
    }

    public JSONObject getSelection(Integer userID)  {
        String request = selectionerRoute + "selections/selection?userID=" + userID.toString();
        System.out.println("Fetching selection...\nRoute: " + request);
        Optional<JSONObject> responce = makeGetRequest(request);
        return unwrap(responce);
    }

    public JSONObject newProfile(Integer userID)  {
        String request = profilerRoute + "profiles/profile/new?userID=" + userID.toString();
        System.out.println("New profile request...\nRoute: " + request);
        Optional<JSONObject> responce = makeGetRequest(request);
        return unwrap(responce);
    }

    public JSONObject updateProfile(Integer userID, JSONObject preferences)  {
        String request = profilerRoute + "profiles/profile?userID=" + userID.toString();
        System.out.println("Uploading preferences...\nRoute: " + request);
        Optional<JSONObject> responce = makePostRequest(request, preferences);
        return unwrap(responce);
    }

    public JSONObject signIN(String login, String password)  {
        String request = String.format("%slogin?login=%s&password=%s", authorizerRoute, login, password);
        System.out.println("Signing in...\nRoute: " + request);
        Optional<JSONObject> responce = makeGetRequest(request);
        return unwrap(responce);
    }

    public JSONObject signUP(String login, String password)  {
        String request = String.format("%sregistration?login=%s&password=%s", authorizerRoute, login, password);
        System.out.println("Signing up...\nRoute: "  + request);
        Optional<JSONObject> responce = makePostRequest(request, null);
        return unwrap(responce);
    }

    public JSONObject updateAccount(String currentLogin,
                                    String currentPassword,
                                    String newLogin,
                                    String newPassword)  {
        System.out.println("Updating users account...");
        String request = String.format("%slogin?login=%s?password?=%s?newlogin=%s?newpassword=%s",
                authorizerRoute, currentLogin, currentPassword, newLogin, newPassword);
        Optional<JSONObject> responce = makePutRequest(request, null);
        return unwrap(responce);
    }

    public JSONObject check()  {
        JSONObject resp = new JSONObject();
        resp.put("code", 1);
        resp.put("message", "Working right, everything is fine");
        return resp;
    }

    private JSONObject unwrap(Optional<JSONObject> object) {
        if (object.isPresent()) return object.get();
        else {
            JSONObject res = new JSONObject();
            res.put("code", 0);
            res.put("message", "Internal api error");
            return res;
        }
    }

    private Optional<JSONObject> makeGetRequest(String request) {
        Optional<JSONObject> responce = Optional.empty();
        try {
            responce = Optional.ofNullable(this.restTemplate.getForObject(request, JSONObject.class));
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return responce;
    }

    private Optional<JSONObject> makePostRequest(String request, JSONObject body) {
        Optional<JSONObject> responce = Optional.empty();
        try {
            responce = Optional.ofNullable(restTemplate.postForObject(request, body, JSONObject.class));
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return responce;
    }

    private Optional<JSONObject> makePutRequest(String request, JSONObject body) {
        Optional<JSONObject> responce = Optional.empty();
        try {
            responce = Optional.ofNullable(restTemplate.postForObject(request, body, JSONObject.class));
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return responce;
    }


}
