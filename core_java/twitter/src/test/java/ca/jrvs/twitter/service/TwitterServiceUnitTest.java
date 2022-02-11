package ca.jrvs.apps.twitter.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.TweetTemp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterServiceUnitTest {

  @Mock
  CrdDao dao;

  @InjectMocks
  TwitterService services;

  @Test
  public void postTweets() {

    when(dao.create(any())).thenReturn(new Tweet());
    services.postTweet(TweetTemp.createTweet("test", 50.0, 0.0));

  }

  @Test
  public void showTweet() {
    services.showTweet("100", null);
  }

  @Test
  public void deleteTweet() throws Exception {
    services.deleteTweets(new String[] {"100", "202"});
  }

}