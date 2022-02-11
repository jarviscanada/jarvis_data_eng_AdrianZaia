package ca.jrvs.apps.twitter.model;

import java.util.List;

public class Entities {

  private List<Hashtags> hastags;
  private List<UserMentions> userMentions;


  public Entities (List<Hashtags> hastags, List<UserMentions> userMentions){
    this.hastags = hastags;
    this.userMentions = userMentions;
  }

  public List<Hashtags> getHastags() {
    return hastags;
  }

  public void setHastags(List<Hashtags> hastags) {
    this.hastags = hastags;
  }


  public List<UserMentions> getUserMentions() {
    return userMentions;
  }

  public void setUserMentions(List<UserMentions> userMentions) {
    this.userMentions = userMentions;
  }
}