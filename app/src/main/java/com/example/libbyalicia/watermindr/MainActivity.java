package com.example.libbyalicia.watermindr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button addPlantBtn;
    Button whatsNextBtn;
    Button viewMyPlantsBtn;
    Button tipsAndTricksBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addPlantBtn = (Button) findViewById(R.id.addPlant);
        whatsNextBtn = (Button) findViewById(R.id.WhatsNext);
        viewMyPlantsBtn = (Button) findViewById(R.id.ViewMyPlants);
        tipsAndTricksBtn = (Button) findViewById(R.id.tipsandtricks);

        //set on click listeners to open activities
        addPlantBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent myIntent = new Intent(MainActivity.this, AddPlantActivity.class);
                startActivity(myIntent); //start
            }
        });

        //set on click listeners to open activities
        whatsNextBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent myIntent = new Intent(MainActivity.this, WhatsNextActivity.class);
                startActivity(myIntent); //start
            }
        });

        //set on click listeners to open activities
        viewMyPlantsBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent myIntent = new Intent(MainActivity.this, ViewMyPlantsActivity.class);
                startActivity(myIntent); //start
            }
        });

        //set on click listeners to open activities
        tipsAndTricksBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent myIntent = new Intent(MainActivity.this, GardeningVideosActivity.class);
                startActivity(myIntent); //start
            }
        });

        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

        dbHandler.deleteAllPlantGuides(); //delete all plant guides before re-adding to database


        /**
         * CRUD Operations
         * */
        // Inserting plant guides
        Log.d("Insert: ", "Inserting ..");

        //Watering frequency: 1, 3, 5 & 7
        //1 = every saturday
        //3 = every mon, tues, weds
        //5 = every weekday
        //7 = everyday
        dbHandler.addPlantGuide(new PlantGuide("Cactus", 1, "Summer"));
        dbHandler.addPlantGuide(new PlantGuide("Sun Flower", 7, "Summer"));
        dbHandler.addPlantGuide(new PlantGuide("Tulip", 7, "Spring"));
        dbHandler.addPlantGuide(new PlantGuide("Rose", 5, "Summer"));
        dbHandler.addPlantGuide(new PlantGuide("Garlic", 3, "Winter"));

    }
}
