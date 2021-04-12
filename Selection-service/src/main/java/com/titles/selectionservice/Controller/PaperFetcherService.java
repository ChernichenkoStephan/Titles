package com.titles.selectionservice.Controller;

import com.titles.selectionservice.Controller.Interfacies.PaperFetcher;
import com.titles.selectionservice.Model.Paper;
import org.json.simple.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class PaperFetcherService implements PaperFetcher {
    public static PaperFetcherService defaultService =  new PaperFetcherService();

    private final RestTemplate restTemplate;

    private PaperFetcherService() {
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        this.restTemplate = restTemplateBuilder.build();
    }

    //private final String route = "http://db-service:9091/database/";
    private final String route = "http://download-service:8080/downloader/test?key=322";

    @Override
    public Set<Paper> fetch() {
        System.out.println("Fetching papers...");
        HashSet<Paper> papers = new HashSet<>();
        try {
            String request = route;// + "papers?period=1";
            Optional<JSONObject> responce = Optional.ofNullable(this.restTemplate.getForObject(request, JSONObject.class));
            responce.ifPresent(jps -> papers.addAll(JSONConverter.toPapers(responce.get())) );
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return papers;
    }

}
