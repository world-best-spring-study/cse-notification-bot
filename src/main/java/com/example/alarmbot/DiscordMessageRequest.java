package com.example.alarmbot;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DiscordMessageRequest {

    String date;
    String title;
    String url;

}
