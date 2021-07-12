package com.fernando.nieto.acciona.domain.cronjobs;

import com.fernando.nieto.acciona.domain.port.input.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class SaveTweetsTask {

	@Autowired
	private TweetService tweetService;

	@Scheduled(fixedRate = 5000)
	public void saveTweet() {
		tweetService.saveTweets();
	}
}