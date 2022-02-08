package ca.jrvs.apps.twitter.dao.helper;

import static org.junit.Assert.*;

import java.net.URI;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class TwitterHttpUnitTest {

  @Test
  public void httpPost() throws Exception{

    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");

    System.out.println(consumerKey + " | " + consumerSecret + " | " + accessToken + " | " + tokenSecret);

    TwitterHttpHelper twitterHttpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
    URI uri = new URI("https://api.twitter.com/1.1/statuses/update.json?status=testing_wtih_java");
    HttpResponse response = twitterHttpHelper.httpPost(uri);
    System.out.println(EntityUtils.toString(response.getEntity()));

  }
}