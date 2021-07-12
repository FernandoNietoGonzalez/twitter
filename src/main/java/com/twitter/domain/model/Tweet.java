package com.twitter.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class Tweet {

    private static final long serialVersionUID = 396137782345793269L;

    private Long id;
    private Long tweetId;
    private LocalDateTime createdAt;
    private String user;
    private String text;
    private String location;
    private String language;
    private boolean validated;
    private List<Hashtag> hashtags;

    public void merge(final Tweet saved, final Tweet toSave) {
        toSave.setId(saved.getId());
    }

}
