package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;

public class TwitterService implements Service {

    private CrdDao dao;
    private final integer maxLength = 140;
    private final integer maxCoordiante = 90;
    private final integer minCoordinate = -90;

    @Autowired
    public TwitterService(CrdDao dao){
        this.dao =dao;
    }

    @Override
    public Tweet postTweet(Tweet tweet){

        try {
            validatePostTweet(tweet);
        } catch (Exception e){
            e.printStackTrace();
        }
        return (Tweet) dao.create (tweet);
    }

    //Tweet Validation
    private void validatePostTweet(Tweet tweet) throws Exception {

        Double longitude = tweet.getCoordinates().getCoordinates().get(0);
        Double latitude = tweet.getCoordinates().getCoordinates().get(1);
    
        if (tweet.getText().length() >= maxTextLength){
          throw new Exception("You have exceeded the max character length of 140 characters");
        } else if (longitude > maxTextLength || longitude < minCoordinate){
          throw new Exception("Longitude value  is out of range");
        } else if (latitude > maxTextLength || latitude < minCoordinate){
          throw new Exception("Latitude value is out of range");
        }
    
      }

    //showTweet
    @Override
    public Tweet showTweet(String id, String[] fields) {
        try {
            validateId(id);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return (Tweet) dao.findById(id);
    }

    private void validateId (String id) throws Exception {
        Pattern pattern = Pattern.compile("[^0-9]");
        Matcher matcher = pattern.matcher(id);
        
        if (matcher.matches()){
          throw new IllegalArgumentException("Contains illegal value inside id");
        }

      }

    @Override
    public List<Tweet> deleteTweets(String[] ids) throws Exception {
        ArrayList<Tweet> tweets = new ArrayList<>();
    
          for (String id: ids) {
              try {
                validateId(id);
                tweets.add((Tweet) dao.findById(id));
              } catch (Exception e) {
                e.printStackTrace();
                throw new IllegalArgumentException("Could not delete Tweet");
              }
          }

        return tweets;
    }
}
