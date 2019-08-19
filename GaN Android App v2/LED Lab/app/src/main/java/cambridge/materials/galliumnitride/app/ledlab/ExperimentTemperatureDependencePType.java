package cambridge.materials.galliumnitride.app.ledlab;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

//Used when experiment is opened

public class ExperimentTemperatureDependencePType extends AppCompatActivity {
    int mTemperature = 0;
    int mMaxTemperature = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experiment_temperature_dependence_ptype);



        Log.i("TAG", "testing1");

        //Fonts, to be used later
        Typeface tajamuka = Typeface.createFromAsset(getAssets(), "fonts/tajamuka_script.ttf");
        Typeface gruppo = Typeface.createFromAsset(getAssets(), "fonts/gruppo.ttf");

        Log.i("TAG", "testing2");
        //Set title bar fonts
        ((TextView) findViewById(R.id.AppName)).setTypeface(tajamuka);
        ((TextView) findViewById(R.id.by)).setTypeface(tajamuka);
        ((TextView) findViewById(R.id.creator)).setTypeface(gruppo);
        ((TextView) findViewById(R.id.TemperatureDependencepTypeTitle)).setTypeface(tajamuka);
        Log.i("TAG", "testing3");

        //Set up click listeners for back and home button
        ImageButton BackButton = findViewById(R.id.backButton);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExperimentTemperatureDependencePType.this, ExperimentTemperatureDependenceFrontPage.class);
                startActivity(intent);
            }
        });
        Log.i("TAG", "testing4");

        ImageButton HomeButton = findViewById(R.id.homeButton);
        HomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExperimentTemperatureDependencePType.this, HomePage.class);
                startActivity(intent);
            }
        });
        Log.i("TAG", "testing5");

        findViewById(R.id.pTypeFullPlot).setVisibility(View.GONE);
        Log.i("TAG", "testing6");


        //Set up listener for temperature slider
        final SeekBar TemperatureSlider = findViewById(R.id.TemperatureSliderpType);
        TemperatureSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {


                //Move slider to last position it was in when switch was on
                if (mTemperature == mMaxTemperature){
                    mTemperature = mMaxTemperature;
                    TemperatureSlider.setProgress(mMaxTemperature+30);
                } else {
                    //change progress to 0 and then to where it should be to change current and temperature too
                    int progress = TemperatureSlider.getProgress();
                    TemperatureSlider.setProgress(0);
                    TemperatureSlider.setProgress(progress);
                }
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean bool) {
                Log.i("TAG", "testing7");

                //get temperature value from slider progress
                int temperature = seekBar.getProgress() * 30;

                //Get text views to be used later
                TextView temperatureOutput = findViewById(R.id.TemperaturepTypeOutput);


                Log.i("TAG", "testing8");
                //Only allow slider to change by +/- 30

                Log.i("TAG", "testing9");
                //Set temperature display text
                temperatureOutput.setText("" + temperature + "K");
                //Get image views to be used late
                ImageView graphPlotFull = findViewById(R.id.pTypeFullPlot);
                ImageView bandDiagram = findViewById(R.id.pTypeBandDiagram);
                Log.i("TAG", "testing10");

                //Replace changing graph with labeled graph
                if (temperature == 600) {
                    Log.i("TAG", "testing11");

                    graphPlotFull.setVisibility(View.VISIBLE);
                    bandDiagram.setVisibility(View.VISIBLE);
                    Log.i("TAG", "testing12");
                }

                //Change graph if temperature goes beyond current max
                if (temperature >= 0) {
                    Log.i("TAG", "testing13");
                    mMaxTemperature = temperature;
                    graphPlotFull.setImageResource(getResources().getIdentifier("experiment_temperature_dependence_ntype_plot_" + (temperature), "drawable", "cambridge.materials.galliumnitride.app.ledlab"));
                    bandDiagram.setImageResource(getResources().getIdentifier("experiment_temperature_dependence_ptype_" + (temperature), "drawable", "cambridge.materials.galliumnitride.app.ledlab"));
                    Log.i("TAG", "testing14");
                }
                mTemperature = temperature;
            }
        });
    }

}





