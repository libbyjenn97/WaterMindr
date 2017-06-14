package com.example.libbyalicia.watermindr;

/**
 * Created by Libby Jennings and ALicia Craig on 14/06/17.
 */

public class PlantsToWater {


    private int Id;
    private String plantName;

    //set the default constructor
    PlantsToWater(int id, String plantName){

        this.Id = id;
        this.plantName = plantName;
    }

    //set alternative constructor
    PlantsToWater(String plantName){
        this.plantName = plantName;
    }

    //parameterless constructor
    public PlantsToWater() {

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
}