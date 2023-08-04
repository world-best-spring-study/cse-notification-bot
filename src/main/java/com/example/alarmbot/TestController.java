package com.example.alarmbot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class TestController {

    @Autowired
    WebHookService webHookService;

    @PostMapping
    public void sendNotification(@RequestBody DiscordMessageRequest discordMessageRequest) {
        webHookService.sendToDiscord(discordMessageRequest);
    }

}
