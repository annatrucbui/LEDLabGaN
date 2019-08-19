package cambridge.materials.galliumnitride.app.ledlab;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

//Used when I-V plotter experiment is opened

public class ExperimentIVPlotter extends AppCompatActivity {
    boolean created = false;
    int mSwitch = 0;
    int mVoltage = 0;
    int mMaxVoltage = 0;
    int mMinVoltage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experiment_iv_plotter);
        Log.i("TAG", "testing1");

        //Fonts, to be used later
        Typeface tajamuka = Typeface.createFromAsset(getAssets(), "fonts/tajamuka_script.ttf");
        Typeface gruppo = Typeface.createFromAsset(getAssets(), "fonts/gruppo.ttf");

        Log.i("TAG", "testing2");
        //Set title bar fonts
        ((TextView) findViewById(R.id.AppName)).setTypeface(tajamuka);
        ((TextView) findViewById(R.id.by)).setTypeface(tajamuka);
        ((TextView) findViewById(R.id.creator)).setTypeface(gruppo);


        Log.i("TAG", "testing3");

        //Set up click listeners for back and home button
        ImageButton BackButton = findViewById(R.id.backButton);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExperimentIVPlotter.this, LaboratoryPage.class);
                startActivity(intent);
            }
        });
        Log.i("TAG", "testing4");

        ImageButton HomeButton = findViewById(R.id.homeButton);
        HomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExperimentIVPlotter.this, HomePage.class);
                startActivity(intent);
            }
        });
        Log.i("TAG", "testing5");

        findViewById(R.id.GraphPlotFull).setVisibility(View.GONE);
        Log.i("TAG", "testing6");






        //Set up listener for switch
        Switch circuitSwitch = findViewById(R.id.CircuitSwitch);
        circuitSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isSwitchOn) {

                //Get image views to be used late
                final ImageView graphPlotRight = findViewById(R.id.GraphPlotRight);
                final ImageView graphPlotLeft = findViewById(R.id.GraphPlotLeft);
                final ImageView graphPlotFull = findViewById(R.id.GraphPlotFull);
                Log.i("TAG", "testing10");


                if(isSwitchOn) {

                    //Set up listener for voltage slider
                    final SeekBar voltageSlider = findViewById(R.id.VoltageSlider);
                    voltageSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int i, boolean bool) {
                            Log.i("TAG", "testing7");
                            //get voltage value from slider progress




                            int voltage = seekBar.getProgress() -5;

                            //Get text views to be used later
                            TextView voltageOutput = findViewById(R.id.VoltageOutput);
                            TextView currentOutput = findViewById(R.id.CurrentOutput);

                            Log.i("TAG", "testing8");


                            //Only allow slider to change by +/- 1

                                Log.i("TAG", "testing9");
                                //Set voltage display text
                                voltageOutput.setText("" + voltage + ".0");


                                //Replace changing graph with labeled graph
                                if ((voltage == 5 && mMaxVoltage != 5 && mMinVoltage == -5) || (voltage == -5 && mMinVoltage != -5 && mMaxVoltage == 5)) {
                                    Log.i("TAG", "testing11");
                                    graphPlotLeft.setVisibility(View.GONE);
                                    graphPlotRight.setVisibility(View.GONE);
                                    graphPlotFull.setVisibility(View.VISIBLE);
                                    Log.i("TAG", "testing12");
                                }

                                //Change graph if voltage goes beyond current max
                                if (voltage > mMaxVoltage) {
                                    Log.i("TAG", "testing13");
                                    mMaxVoltage = voltage;
                                    graphPlotRight.setBackgroundResource(getResources().getIdentifier("experiment_iv_plotter_graph_plot_max_" + (voltage), "drawable", "cambridge.materials.galliumnitride.app.ledlab"));
                                    Log.i("TAG", "testing14");
                                }

                                //Change graph if voltage goes beyond current min
                                if (voltage < mMinVoltage) {
                                    Log.i("TAG", "testing15");
                                    mMinVoltage = voltage;
                                    graphPlotLeft.setBackgroundResource(getResources().getIdentifier("experiment_iv_plotter_graph_plot_min_" + (-1 * voltage), "drawable", "cambridge.materials.galliumnitride.app.ledlab"));
                                    Log.i("TAG", "testing16");
                                }

                                //Change current value depending on voltage
                                if (voltage == -5)
                                    currentOutput.setText("-5.5");
                                else if (voltage == -4)
                                    currentOutput.setText("-0.45");
                                else if (voltage == -3)
                                    currentOutput.setText("-0.33");
                                else if (voltage == -2)
                                    currentOutput.setText("-0.20");
                                else if (voltage == -1)
                                    currentOutput.setText("-0.08");
                                else if (voltage == 0)
                                    currentOutput.setText("0.0");
                                else if (voltage == 1)
                                    currentOutput.setText("0.12");
                                else if (voltage == 2)
                                    currentOutput.setText("0.53");
                                else if (voltage == 3)
                                    currentOutput.setText("9.73");
                                else if (voltage == 4)
                                    currentOutput.setText("37.8");
                                else if (voltage == 5)
                                    currentOutput.setText("55.6");
                            }




                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {}

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {}
                    });


                    //Change circuit diagram to close switch
                    ((ImageView) findViewById(R.id.CircuitDiagram)).setImageResource(R.drawable.experiment_iv_plotter_circuit_closed);

                    //Move slider to last position it was in when switch was on

                } else {

                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);



                }
            }
        });















    }
}