package com.titles.selectionservice.Controller;

import com.titles.selectionservice.Controller.Interfacies.ProfileFetcher;
import com.titles.selectionservice.Model.Profile;
import org.json.simple.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ProfileFetcherService implements ProfileFetcher {
    public static ProfileFetcherService defaultService = new ProfileFetcherService();

    private final RestTemplate restTemplate;

    private final String route = "http://profile-service:8080/profiler/";

    private ProfileFetcherService() {
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        this.restTemplate = restTemplateBuilder.build();
    }
    @Override
    public Optional<Profile> fetch(Integer userId) {
        System.out.println("Fetching profile...");
        Optional<Profile> res = Optional.empty();
        try {
            String request = route + "profiles/profile?userID=" + userId.toString();
            Optional<JSONObject> responce = Optional.ofNullable(this.restTemplate.getForObject(request, JSONObject.class));
            if (responce.isPresent()) res = JSONConverter.toProfile(responce.get());
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return res;
    }

    @Override
    public Set<Profile> fetch() {
        System.out.println("Fetching profiles...");
        HashSet<Profile> profiles = new HashSet<>();
        try {
            String request = route + "profiles/";
            Optional<JSONObject> responce =
                    Optional.ofNullable(this.restTemplate.getForObject(request, JSONObject.class));
            responce.ifPresent(res -> profiles.addAll(JSONConverter.toProfiles(res)));

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return profiles;
    }



}
