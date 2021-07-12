package com.twitter.infrastructure.twitter.config;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Configuration
public class TwitterApiConfig {

    @Value("${acciona.consumerKey}")
    private String consumerKey;
    @Value("${acciona.consumerSecret}")
    private String consumerSecret;
    @Value("${acciona.accessToken}")
    private String accessToken;
    @Value("${acciona.accessTokenSecret}")
    private String accessTokenSecret;

    @SneakyThrows
    @Bean
    public Twitter twitter () {
        final ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(accessTokenSecret);
        final TwitterFactory tf = new TwitterFactory(cb.build());
        return tf.getInstance();
    }
}
