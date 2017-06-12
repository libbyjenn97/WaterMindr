package com.example.libbyalicia.watermindr;

/**
 * Created by libby Jennings and Alicia Craig on 7/06/17.
 */

public class MyPlants {


    private int Id;
    private String plantName;
    private long waterFrequency;
    private String optimalSeason;

    //set the default constructor
    MyPlants(int id, String plantName, long waterFrequency, String optimalSeason){

        this.Id = id;
        this.plantName = plantName;
        this.waterFrequency = waterFrequency;
        this.optimalSeason = optimalSeason;
    }

    //set alternative constructor
    MyPlants(String plantName, long waterFrequency, String optimalSeason){
        this.plantName = plantName;
        this.waterFrequency = waterFrequency;
        this.optimalSeason = optimalSeason;
    }

    //parameterless constructor
    public MyPlants() {

    }

    //set plantguide ID for database purposes
    public void setID(int id) {
        this.Id = id;
    }

    //get plantguide ID
    public int getID() {
        return this.Id;
    }

    //set plantguide name
    public void setName(String plantName) {
        this.plantName = plantName;
    }

    //get plantguide name
    public String getName() {
        return this.plantName;
    }

    //set plantguide watering frequency
    public void setWaterFrequency(long waterFrequency) {
        this.waterFrequency = waterFrequency;
    }

    //get plantguide watering frequency
    public long getWaterFrequency() {
        return this.waterFrequency;
    }

    //set plantguide optimal season
    public void setOptimalSeason(String optimalSeason) {
        this.optimalSeason = optimalSeason;
    }

    //get plantguide optimal season
    public String getOptimalSeason() {
        return this.optimalSeason;
    }
}
