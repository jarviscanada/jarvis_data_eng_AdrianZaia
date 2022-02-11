package ca.jrvs.apps.twitter.model;


import java.util.List;

public class Coordinates {

  private List<Double> coordinates;
  private String type;


  public Coordinates(List<Double> coordinates, String type){
    this.coordinates = coordinates;
    this.type = type;
  }


  public Coordinates (){
    //Create empty object
  }


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  public List<Double> getCoordinates() {
    return coordinates;
  }


  public void setCoordinates(List<Double> coordinates) {
    this.coordinates = coordinates;
  }



}