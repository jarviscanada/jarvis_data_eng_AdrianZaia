package ca.jrvs.apps.twitter.service;


import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.TweetTemp;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.TestExecutionListener;
import org.springframework.test.context.TestExecutionListeners;

public class TwitterServiceIntTest {

  private CrdDao dao;
  private TwitterService service;


  @Before
  public void setUp(){
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");
    System.out.println(consumerKey + " | " + consumerSecret + " | " + accessToken + " | " + tokenSecret);


    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
    this.dao = new TwitterDao(httpHelper);
    this.service = new TwitterService(dao);
  }


  @Test
  public void postTweets(){

    Tweet tweetTest = new Tweet();
    String text = "This is a test. More than 140 characters.";
    Double lat = 1d;
    Double lon = 1d;
    tweetTest.setText(text);
    Tweet tweet = TweetTemp.createTweet(text, lon, lat);
    Tweet tweetService = service.postTweet(tweetTest);

  }

  @Test
  public void showTweets(){
    Tweet tweetTest = new Tweet();
    String text = "This is a test. More than 140 characters.";
    tweetTest.setText(text);
    tweetTest.setId(100);
    Tweet tweetService = service.showTweet("100", null);

  }

  @Test
  public void deleteTweets(){
    String[] ids = new String[] {"100", "101", "203"};
    try {
      List<Tweet> tweetService = service.deleteTweets(ids);
    } catch (Exception e) {
      throw new IllegalArgumentException("Did not delete Tweet");
    }
  }

}