package com.example.alarmbot;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebHookService {

    @Value("${discord-web-hook}")
    private String discordUrl;

    //TODO : 크론식 변경
    @Scheduled(cron = "0 57 23 * * *", zone = "Asia/Seoul")
    public void sendToDiscord() {

        //TODO : 크롤링 결과 담기
        String date = "date";
        String title = "notification title";
        String url = "www.notification.com";
        String content = "cron test";

        DiscordMessageRequest discordMessageRequest = new DiscordMessageRequest(date, title, url, content);


        //메세지 내용을 json 형태로 변환
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("content", discordMessageRequest.getContent());
        //jsonObject.put("date", discordMessageRequest.getDate());
        //jsonObject.put("title", discordMessageRequest.getTitle());
        //jsonObject.put("url", discordMessageRequest.getUrl());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = new HttpEntity<>(jsonObject.toString(), httpHeaders);
        restTemplate.postForObject(discordUrl, entity, String.class);

    }

}
