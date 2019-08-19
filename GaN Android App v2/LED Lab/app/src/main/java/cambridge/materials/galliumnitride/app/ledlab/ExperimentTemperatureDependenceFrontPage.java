package cambridge.materials.galliumnitride.app.ledlab;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ExperimentTemperatureDependenceFrontPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experiment_temperature_dependence_front_page);

        //Fonts, to be used later
        Typeface tajamuka = Typeface.createFromAsset(getAssets(),  "fonts/tajamuka_script.ttf");
        Typeface gruppo = Typeface.createFromAsset(getAssets(),  "fonts/gruppo.ttf");

        //Set title bar fonts
        ((TextView)findViewById(R.id.AppName)).setTypeface(tajamuka);
        ((TextView)findViewById(R.id.by)).setTypeface(tajamuka);
        ((TextView)findViewById(R.id.creator)).setTypeface(gruppo);

        ImageButton BackButton = findViewById(R.id.backButton);
        BackButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ExperimentTemperatureDependenceFrontPage.this, LaboratoryPage.class);
                startActivity(intent);
            }
        });

        ImageButton HomeButton = findViewById(R.id.homeButton);
        HomeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ExperimentTemperatureDependenceFrontPage.this, HomePage.class);
                startActivity(intent);
            }
        });

        //Set up click listeners for experiment buttons
        ImageButton IntrinsicButton = findViewById(R.id.IntrinsicSemiconductorButton);
        IntrinsicButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ExperimentTemperatureDependenceFrontPage.this, ExperimentTemperatureDependenceIntrinsic.class);
                startActivity(intent);
            }
        });

        ImageButton NTypeButton = findViewById(R.id.NTypeSemiconductorButton);
        NTypeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ExperimentTemperatureDependenceFrontPage.this, ExperimentTemperatureDependenceNType.class);
                startActivity(intent);
            }
        });

        ImageButton PTypeButton = findViewById(R.id.PTypeSemiconductorButton);
        PTypeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ExperimentTemperatureDependenceFrontPage.this, ExperimentTemperatureDependencePType.class);
                startActivity(intent);
            }
        });









    }
}
