package com.fernando.nieto.acciona.infrastructure.twitter;

import com.fernando.nieto.acciona.domain.exception.InvalidTweetException;
import com.fernando.nieto.acciona.domain.model.Tweet;
import com.fernando.nieto.acciona.domain.port.output.TwitterService;
import com.fernando.nieto.acciona.infrastructure.twitter.adapter.TweetAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Component
@Slf4j
public class TwitterServiceImpl implements TwitterService {

    @Autowired
    private Twitter twitter;

    private TweetAdapter adapter = TweetAdapter.INSTANCE;

    private static final String LANG = "lang:%s";

    @Override
    public List<Tweet> getTweetsToPersist(final Integer followers, final List<String> languages) {
        return languages.stream().map(lang -> {
            List<Tweet> result;
            try {
                log.debug("Finding tweets in Twitter ");
                final Query query = new Query(format(LANG, lang));
                query.setCount(15);
                final QueryResult tweets = twitter.search(query);
                log.debug("Found tweets in Twitter {}", tweets.getTweets().size());
                result = tweets.getTweets()
                        .stream()
                        .filter(tweet -> tweet.getUser().getFollowersCount() > followers)
                        .filter(tweet -> languages.contains(tweet.getLang().toLowerCase()))
                        .map(tweet -> adapter.adapt(tweet))
                        .collect(Collectors.toList());

            } catch (final TwitterException e) {
                final String error = format("Error retrieving tweets : %s", e.getMessage());
                log.error(error);
                throw new InvalidTweetException(e.getMessage());
            }
            return result;
        }).flatMap(Collection::stream)
          .collect(Collectors.toList());
    }
}
