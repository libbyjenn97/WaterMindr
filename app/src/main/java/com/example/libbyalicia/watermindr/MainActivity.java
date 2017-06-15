package com.example.libbyalicia.watermindr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button waterMeBtn;
    Button addPlantBtn;
    Button whatsNextBtn;
    Button viewMyPlantsBtn;
    Button tipsAndTricksBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        waterMeBtn = (Button) findViewById(R.id.waterMe);
        addPlantBtn = (Button) findViewById(R.id.addPlant);
        whatsNextBtn = (Button) findViewById(R.id.WhatsNext);
        viewMyPlantsBtn = (Button) findViewById(R.id.ViewMyPlants);
        tipsAndTricksBtn = (Button) findViewById(R.id.tipsandtricks);

        //set on click listeners to open activities
        waterMeBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                //Intent myIntent = new Intent(MainActivity.this, WaterPlantActivity.class);
                Intent myIntent = new Intent(MainActivity.this, NotificationView.class);
                startActivity(myIntent); //start
            }
        });

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
        //1 = once a week
        //3 = every second day
        //7 = everyday
        dbHandler.addPlantGuide(new PlantGuide("Broccoli", 3, "Autumn"));
        dbHandler.addPlantGuide(new PlantGuide("Cabbage", 1, "Spring"));
        dbHandler.addPlantGuide(new PlantGuide("Cactus", 1, "Summer"));
        dbHandler.addPlantGuide(new PlantGuide("Cucumber", 3, "Summer"));
        dbHandler.addPlantGuide(new PlantGuide("Eggplant", 1, "Summer"));
        dbHandler.addPlantGuide(new PlantGuide("Garlic", 3, "Winter"));
        dbHandler.addPlantGuide(new PlantGuide("Lettuce", 7, "Winter"));
        dbHandler.addPlantGuide(new PlantGuide("Peas", 1, "Summer"));
        dbHandler.addPlantGuide(new PlantGuide("Red Onion", 7, "Winter"));
        dbHandler.addPlantGuide(new PlantGuide("Rose", 3, "Summer"));
        dbHandler.addPlantGuide(new PlantGuide("Sun Flower", 7, "Summer"));
        dbHandler.addPlantGuide(new PlantGuide("Tomatoes", 3, "Spring"));
        dbHandler.addPlantGuide(new PlantGuide("Tulip", 7, "Spring"));

    }
}
