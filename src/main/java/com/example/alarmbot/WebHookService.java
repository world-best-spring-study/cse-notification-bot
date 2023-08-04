package com.example.alarmbot;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebHookService {

    @Value("${discord-web-hook}")
    private String discordUrl;
    public void sendToDiscord(DiscordMessageRequest discordMessageRequest) {

        //메세지 내용을 json 형태로 변환
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("content", discordMessageRequest.getContent());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = new HttpEntity<>(jsonObject.toString(), httpHeaders);
        restTemplate.postForObject(discordUrl, entity, String.class);

    }

}
