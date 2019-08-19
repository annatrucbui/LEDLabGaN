package cambridge.materials.galliumnitride.app.ledlab;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

//Used when laboratory page is opened

public class LaboratoryPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_laboratory);

        //Fonts, to be used later
        Typeface tajamuka = Typeface.createFromAsset(getAssets(),  "fonts/tajamuka_script.ttf");
        Typeface gruppo = Typeface.createFromAsset(getAssets(),  "fonts/gruppo.ttf");
        Typeface pajamapants = Typeface.createFromAsset(getAssets(),  "fonts/pajamapants.ttf");

        //Set title bar fonts
        ((TextView)findViewById(R.id.AppName)).setTypeface(tajamuka);
        ((TextView)findViewById(R.id.by)).setTypeface(tajamuka);
        ((TextView)findViewById(R.id.creator)).setTypeface(gruppo);
        ((TextView) findViewById(R.id.LaboratoryTitle)).setTypeface(pajamapants);

        ImageButton BackButton = findViewById(R.id.backButton);
        BackButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(LaboratoryPage.this, HomePage.class);
                startActivity(intent);
            }
        });

        ImageButton HomeButton = findViewById(R.id.homeButton);
        HomeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(LaboratoryPage.this, HomePage.class);
                startActivity(intent);
            }
        });

        //Set up click listeners for experiment buttons
        ImageButton ColourTuningButton = findViewById(R.id.ColourTuningExperimentButton);
        ColourTuningButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(LaboratoryPage.this, ExperimentColourTuning.class);
                startActivity(intent);
            }
        });

        ImageButton IVPlotterButton = findViewById(R.id.IVPlotterExperimentButton);
        IVPlotterButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(LaboratoryPage.this, ExperimentIVPlotter.class);
                startActivity(intent);
            }
        });

        ImageButton LEDSwitchButton = findViewById(R.id.LEDSwitchExperimentButton);
        LEDSwitchButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(LaboratoryPage.this, ExperimentLEDSwitch.class);
                startActivity(intent);
            }
        });

        ImageButton TempDependenceButton = findViewById(R.id.TemperatureDependenceButton);
        TempDependenceButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(LaboratoryPage.this, ExperimentTemperatureDependenceFrontPage.class);
                startActivity(intent);
            }
        });
    }
}
