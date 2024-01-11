package com.example.alarmbot;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class WebHookService {

    @Value("${discord-web-hook}")
    private String discordUrl;

    //TODO : 크론식 변경
    @Scheduled(cron = "0 24 15 * * *", zone = "Asia/Seoul")
    public void checkNotification() {

        log.info(" *** sendMessage cronjob start *** ");
        //TODO : 크롤링 결과 담기
        String date = "date";
        String title = "notification title";
        String url = "https://www.naver.com/";

        this.sendToDiscord(DiscordMessageRequest.builder().
                date(date).
                title(title).
                url(url).build());
        log.info(" *** sendMessage cronjob finish *** ");

    }

    public void sendToDiscord(DiscordMessageRequest discordMessageRequest) {

        //메세지 내용을 json 형태로 변환
        JSONObject jsonObject = new JSONObject();

        String content = "게시일 : " + discordMessageRequest.getDate() + "\n제목 : " + discordMessageRequest.getTitle() +
                "\n링크 : " + discordMessageRequest.getUrl();
        jsonObject.put("content", content);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = new HttpEntity<>(jsonObject.toString(), httpHeaders);
        restTemplate.postForObject(discordUrl, entity, String.class);

    }

}
