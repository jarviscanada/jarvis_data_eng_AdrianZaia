package ca.jrvs.apps.twitter.dao;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.JsonParsing;
import ca.jrvs.apps.twitter.util.TweetTemp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterDaoUnitTest {

  @Mock
  HttpHelper mockHelper;

  @InjectMocks
  TwitterDao dao;


  @Test
  public void createTweet() throws Exception {

    String hashTag = "#bce";
    String text = "Tweeter";
    Double lat = 1d;
    Double lon = -1d;

    when(mockHelper.httpPost(isNotNull())).thenThrow(new RuntimeException("mock"));
    try {
      dao.create(TweetTemp.createTweet(text, lon, lat));
      fail();
    } catch (RuntimeException e){
      assertTrue(true);
    }


    String tweetJson =  "{\n"
        +"\"createdAt\":\"Mon feb 18 21:24:39 +0000 2019\",\n"
        +"\"id\":2564480,\n"
        +"\"idStr\":\"2564480\",\n"
        +"\"text\":\"text with loc223\",\n"
        +"\"entities\":{\n"
        +"\"hashtags\":[],"
        +"\"userMentions\":[]"
        +"},\n"
        +"\"coordinates\":null,"
        +"\"retweetCount\":0,\n"
        +"\"favouriteCount\":0,\n"
        +"\"favorited\":false,\n"
        +"\"retweeted\":false\n"
        +"}";


    when(mockHelper.httpPost(isNotNull())).thenReturn(null);
    TwitterDao spyDao = Mockito.spy(dao);
    Tweet expectedTweet = JsonParsing.toObjectFromJson(tweetJson, Tweet.class);

    doReturn(expectedTweet).when(spyDao).parseResponseBody(any(), anyInt());
    Tweet tweet = spyDao.create(TweetTemp.createTweet(text,lon, lat));
    assertNotNull(tweet);
    assertNotNull(tweet.getText());
  }


  @Test
  public void findTweetById() throws Exception{

    String tweetJson =  "{\n"
        +"\"createdAt\":\"Mon feb 18 21:24:39 +0000 2019\",\n"
        +"\"id\":2564480,\n"
        +"\"idStr\":\"2564480\",\n"
        +"\"text\":\"text with loc223\",\n"
        +"\"entities\":{\n"
        +"\"hashtags\":[],"
        +"\"userMentions\":[]"
        +"},\n"
        +"\"coordinates\":null,"
        +"\"retweetCount\":0,\n"
        +"\"favouriteCount\":0,\n"
        +"\"favorited\":false,\n"
        +"\"retweeted\":false\n"
        +"}";


    TwitterDao spyDao = Mockito.spy(dao);
    Tweet tweet = JsonParsing.toObjectFromJson(tweetJson, Tweet.class);

    doReturn(tweet).when(spyDao).parseResponseBody(any(), anyInt());
    Tweet findTweet = spyDao.findById("2564480");
    assertNotNull(findTweet);
    assertNotNull(tweet.getText());

  }


  @Test
  public void deleteTweetById() throws Exception {

    String tweetJson =  "{\n"
        +"\"createdAt\":\"Mon feb 18 21:24:39 +0000 2019\",\n"
        +"\"id\":2564480,\n"
        +"\"idStr\":\"2564480\",\n"
        +"\"text\":\"text with loc223\",\n"
        +"\"entities\":{\n"
        +"\"hashtags\":[],"
        +"\"userMentions\":[]"
        +"},\n"
        +"\"coordinates\":null,"
        +"\"retweetCount\":0,\n"
        +"\"favouriteCount\":0,\n"
        +"\"favorited\":false,\n"
        +"\"retweeted\":false\n"
        +"}";

    when(mockHelper.httpPost(isNotNull())).thenReturn(null);
    TwitterDao spyDao = Mockito.spy(dao);
    Tweet tweet = JsonParsing.toObjectFromJson(tweetJson, Tweet.class);
    doReturn(tweet).when(spyDao).parseResponseBody(any(), anyInt());

    Tweet findTweet = spyDao.findById("2564480");
    assertNotNull(findTweet);
    assertNotNull(tweet.getText());

    Tweet deleteTweet = spyDao.deleteById("2564480");
    assertNotNull(deleteTweet);

  }
}