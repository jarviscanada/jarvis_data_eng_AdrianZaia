package main.java.ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import com.google.gdata.util.common.base.PercentEscaper;
import ca.jrvs.apps.twitter.util.JsonUtil;
import com.sun.jndi.toolkit.url.Uri;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import javax.management.RuntimeErrorException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import ca.jrvs.apps.twitter.util.JsonParsing;

public class TwitterDao implements CrdDao<Tweet, String> {

    //URI Constants
    private static final String API_BASE_URI = "https://api.twitter.com";
    private static final String POST_PATH = "/1.1/statuses/update.json";
    private static final String SHOW_PATH = "/1.1/statuses/show.json";
    private static final String DELETE_PATH = "/1.1/statuses/destroy";

    //URI Symbols
    private static final String QUERY_SYM = "?";
    private static final String AMPERSAND = "&";
    private static final String EQUAL = "=";

    //Http Response Code
    private static final int HTTP_OK = 200;
    private HttpHelper httpHelper;


    /**
    * Constructor
    * @param httpHelper
    */
    public TwitterDao (HttpHelper httpHelper){
    this.httpHelper = httpHelper;
  }

  @Override
  public Tweet create(Tweet message) {
      URI uri;
      try{
          uri = getPostUri(message);
      } catch (URISyntaxException | UnsupportedEncodingException e){
          throw new IllegalArgumentException("Not A Valid Input", e);
      }
      HttpResponse response = httpHelper.httpPost(uri);

      return parseResponseBody(response, HTTP_OK);
    }
    

  private Tweet parseResponseBody (HttpResponse response, Integer expectedStatusCode){
      Tweet tweet = null;

      int status = response.getStatueLine().getStatusCode();
      if (status != expectedStatusCode){
          try{
              System.out.println(EntityUtils.toString(response.getEntity()));
          } catch (IOException e) {
              System.out.println("No Entity");
          }
          throw new RuntimeException("Unexpected HTTP Status: " + status);
      }

      if (response.getEntity()  == null){
          throw new RuntimeException("Empty Response");
      }

      String jsonStr;
      try {
          jsonStr = EntityUtils.toString(response.getEntity());
      } catch (IOException e) {
          throw new RuntimeException("Failed to convert entity to String", e);
      }

      try {
          tweet = JsonUtil.toObjectFromJson(jsonStr, Tweet.class);
      } catch (IOException e){
          throw new RuntimeException("Unable to convert JSON str to Object", e);
      }

      return tweet;
    }

    @Override
    public Tweet findById(String s) {
      URI uri;
  
      PercentEscaper percentEscaper = new PercentEscaper("", false);
      String id = "id" + EQUAL + s;
      uri = URI.create(API_BASE_URI + SHOW_PATH + QUERY_SYM + id);
      HttpResponse response = httpHelper.httpGet(uri);
  
      return parseResponseBody(response, HTTP_OK);
    }
  
  
    @Override
    public Tweet deleteById(String s) {
      URI uri;
  
      PercentEscaper percentEscaper = new PercentEscaper("", false);
      uri = URI.create(API_BASE_URI + DELETE_PATH + "/" + s + ".json");
      HttpResponse response = httpHelper.httpPost(uri);
  
      return parseResponseBody(response, HTTP_OK);
    }
  
}