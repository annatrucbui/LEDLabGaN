package cambridge.materials.galliumnitride.app.ledlab;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.style.RelativeSizeSpan;
import android.text.style.SubscriptSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;

//Used when colour tuning experiment is opened

public class ExperimentColourTuning extends AppCompatActivity {
    PopupWindow popup = new PopupWindow();

    private static double MIN_COMPOSITION = 0.0;
    private static double MAX_COMPOSITION = 0.3;
    private static double MIN_WIDTH = 1.0; //in nm
    private static double MAX_WIDTH = 3.0; //in nm
    private double mComposition = MIN_COMPOSITION + (MAX_COMPOSITION-MIN_COMPOSITION)/2;
    private double mWidth = MIN_WIDTH + (MAX_WIDTH-MIN_WIDTH)/2;

    private boolean mLabelsVisible = true;
    private int mWavelength = 0;
    String mColour = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experiment_colour_tuning);

        //Find fonts to be used later
        Typeface tajamuka = Typeface.createFromAsset(getAssets(), "fonts/tajamuka_script.ttf");
        Typeface gruppo = Typeface.createFromAsset(getAssets(), "fonts/gruppo.ttf");

        //Set title bar fonts
        ((TextView) findViewById(R.id.AppName)).setTypeface(tajamuka);
        ((TextView) findViewById(R.id.by)).setTypeface(tajamuka);
        ((TextView) findViewById(R.id.creator)).setTypeface(gruppo);
        ((TextView) findViewById(R.id.ColourTuningTitle)).setTypeface(tajamuka);

        //Set up click listeners for back and home button
        ImageButton BackButton = findViewById(R.id.backButton);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExperimentColourTuning.this, LaboratoryPage.class);
                startActivity(intent);
            }
        });

        ImageButton HomeButton = findViewById(R.id.homeButton);
        HomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExperimentColourTuning.this, HomePage.class);
                startActivity(intent);
            }
        });

        //Set up listener for composition slider
        SeekBar CompositionSlider = findViewById(R.id.CompositionSlider);
        CompositionSlider.setMax(120);
        CompositionSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
           @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mComposition = MIN_COMPOSITION + (seekBar.getProgress() * (MAX_COMPOSITION - MIN_COMPOSITION) / 120);
                update();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        CompositionSlider.setProgress(60);

        //Set up listener for width slider
        SeekBar WidthSlider = findViewById(R.id.WidthSlider);
        WidthSlider.setMax(120);
        WidthSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mWidth = MIN_WIDTH + (seekBar.getProgress() * (MAX_WIDTH - MIN_WIDTH) / 120.0);
                update();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        WidthSlider.setProgress(60);

        //Initiate blurs on micrograph
        final View rootView = getWindow().getDecorView().getRootView();
        rootView.getViewTreeObserver().addOnGlobalLayoutListener( new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //Avoids infinite loop
                removeOnGlobalLayoutListener(rootView, this);

                int totalWidth = findViewById(R.id.Micrograph).getWidth();

                //Set left blur width
                ImageView InGaNLeft = findViewById(R.id.LeftWellBlur);
                setWidth(InGaNLeft, (int) (totalWidth*0.06));

                //Set right blur width
                ImageView InGaNRight = findViewById(R.id.RightWellBlur);
                setWidth(InGaNRight, (int) (totalWidth*0.06));

                update();

                initiatePopupWindow(rootView);
            }
        });



        //Set composition text
        TextView compositionHeader = findViewById(R.id.CompositionHeader);
        compositionHeader.setText(compositionHeader.getText(), TextView.BufferType.SPANNABLE);
        Spannable s = (Spannable) compositionHeader.getText();
        s.setSpan(new SubscriptSpan(), 15, 16, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        s.setSpan(new SubscriptSpan(), 18, 23, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        s.setSpan(new RelativeSizeSpan(0.8f), 15, 16, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        s.setSpan(new RelativeSizeSpan(0.8f), 18, 23, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        compositionHeader.setText(s);


    }

    public void update(){
        double wellWidth = ((((mWidth-MIN_WIDTH)/(MAX_WIDTH-MIN_WIDTH))*0.6)+0.12) * findViewById(R.id.QuantumWell).getWidth();

        //Update InGaN opacity
        ImageView opacityLayer = findViewById(R.id.TopDarkLayer);
        opacityLayer.getBackground().setAlpha((int) ((1.0-mComposition)*510.0));

        if (mComposition!=0) {
            if (!mLabelsVisible){
                //Change labels for no InGaN present
                findViewById(R.id.InGaNLabel).setVisibility(View.VISIBLE);
                findViewById(R.id.GaNLabelLeft).setVisibility(View.VISIBLE);
                findViewById(R.id.GaNLabelRight).setVisibility(View.VISIBLE);
                findViewById(R.id.GaNLabelFull).setVisibility(View.GONE);
                mLabelsVisible = true;
            }
            //Update InGaN width
            ImageView micrographCenter = findViewById(R.id.QuantumWellMicrographCenter);
            setWidth(micrographCenter, (int) (wellWidth - 20));

            //Update InGaN label width
            TextView InGaNLabel = findViewById(R.id.InGaNLabel);
            setWidth(InGaNLabel, (int) (1.05 * wellWidth));

            //Update quantum well width
            ImageView quantumWellBottom = findViewById(R.id.QuantumWell3);
            setWidth(quantumWellBottom, (int) wellWidth);
        }
        if (mComposition==0){
            //Change labels for when InGaN is no longer absent
            findViewById(R.id.InGaNLabel).setVisibility(View.GONE);
            findViewById(R.id.GaNLabelLeft).setVisibility(View.GONE);
            findViewById(R.id.GaNLabelRight).setVisibility(View.GONE);
            findViewById(R.id.GaNLabelFull).setVisibility(View.VISIBLE);
            mLabelsVisible = false;
        }

        //Update quantum well depth
        int lineWidth = findViewById(R.id.QuantumWell2).getWidth();
        double wellHeight = (mComposition-MIN_COMPOSITION)/(MAX_COMPOSITION-MIN_COMPOSITION) * (findViewById(R.id.QuantumWell).getHeight()-lineWidth);
        ImageView quantumWellLeft = findViewById(R.id.QuantumWell2);
        ImageView quantumWellRight = findViewById(R.id.QuantumWell4);
        setHeight(quantumWellLeft, (int) (wellHeight+lineWidth));
        setHeight(quantumWellRight, (int) (wellHeight+lineWidth));

        //Update colour patch
        TextView colourPatch = findViewById(R.id.ColourPatch);
        calculateWavelength();
        int[] rgb = wavelengthToColour(mWavelength);
        colourPatch.setBackgroundColor(Color.rgb(rgb[0], rgb[1], rgb[2]));
        colourPatch.setText("Wavelength: " + mWavelength + " nm (" + mColour + ")");
    }

    //changes width of view
    public void setWidth(View view, int width){
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        lp.width = width;
        view.setLayoutParams(lp);
    }

    //changes height of view
    public void setHeight(View view, int height){
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        lp.height = height;
        view.setLayoutParams(lp);
    }

    //calculate wavelength from composition and well width
    public void calculateWavelength(){
        //constants
        final double GaN_BANDGAP = 3.49; //in eV
        final double InN_BANDGAP = 0.76; //in eV
        final double BOWING_FACTOR = 1.4; //in eV
        final double PLANCK_CONSTANT = 6.626E-34; //in Js
        final double SPEED_OF_LIGHT = 2.9986E8; //in m/s
        final double ELECTRON_CHARGE = 1.602E-19; //in C
        final double ELECTRON_EFFECTIVE_MASS = 0.2 * 9.109E-31; //in kg
        final double HOLE_EFFECTIVE_MASS = 0.8 * 9.109E-31; //in kg

        //Use linear interpolation with bowing factor calculate bandgap
        double bandgap = ((1.0-mComposition)*GaN_BANDGAP) + (mComposition*InN_BANDGAP) - (mComposition*(1.0-mComposition)*BOWING_FACTOR);

        //Initiate energies
        double electronQuantumEnergy;
        double holeQuantumEnergy;

        //electron charge converts to eV, and comp^1.5 converts from infinte potential well to finite potential well approximately
        electronQuantumEnergy = Math.pow(mComposition, 1.5) * (PLANCK_CONSTANT*PLANCK_CONSTANT)/(8.0*ELECTRON_EFFECTIVE_MASS*mWidth*mWidth*1E-18) / ELECTRON_CHARGE;
        holeQuantumEnergy = Math.pow(mComposition, 1.5) * (PLANCK_CONSTANT*PLANCK_CONSTANT)/(8.0*HOLE_EFFECTIVE_MASS*mWidth*mWidth*1E-18) / ELECTRON_CHARGE;

        double photonEnergy = bandgap + electronQuantumEnergy + holeQuantumEnergy; //in ev
        int photonWavelength = (int) ((PLANCK_CONSTANT * SPEED_OF_LIGHT *1E9)/(ELECTRON_CHARGE*photonEnergy)); //in nm

        //adjustment to improve accuracy and visuals
        photonWavelength = (photonWavelength*13/14)+25;
        mWavelength = photonWavelength;
    }

    //Calculate RGB value from wavelength
    public int[] wavelengthToColour(int wavelength){
        //initiate values
        double red;
        double green;
        double blue;
        double intensity = 240.0;

        //vary values of each colour depending on the wavelength
        if (330 <= wavelength && wavelength < 380){
            red = (0.2*(wavelength - 330) / (380.0 - 330.0))+0.7;
            green = (0.25*(380.0 - wavelength) / (380.0 - 330.0))+0.2;
            blue = (0.9*(wavelength - 330) / (380.0 - 330.0))+0.1;
            intensity = (green*30)+240.0;

            red *= (red/0.9);

            mColour="UV";
        } else if (380 <= wavelength && wavelength < 440){
            red = (0.5*(440.0 - wavelength) / (440.0 - 380.0))+0.4;
            green = 0.2;
            blue = 1.0;
            intensity = 240.0;
            if (wavelength<410) {
                mColour="violet";
            } else {
                mColour="blue";
            }
        } else if (440 <= wavelength && wavelength < 490){
            red = 0.0;
            green = (0.8*(wavelength - 440.0) / (490.0 - 440.0))+0.2;
            blue = 1.0;
            intensity = 240-(green*green*30);
            if (wavelength<485) {
                mColour="blue";
            } else {
                mColour="turquoise";
            }
        } else if (490 <= wavelength && wavelength < 510){
            red = 0.0;
            green = 1.0;
            blue = (510.0 - wavelength) / (510.0 - 490.0);
            intensity = 210-((1.0-blue)*(1.0-blue)*30);
            if (wavelength<495) {
                mColour="turquoise";
            } else {
                mColour="green";
            }
        } else if (510 <= wavelength && wavelength < 580){
            red = (wavelength - 510.0) / (580.0 - 510.0);
            green = 1.0;
            blue = 0.0;
        } else if (580 <= wavelength && wavelength < 650){
            red = 1.0;
            green = (650.0 - wavelength) / (650.0 - 580.0);
            blue = 0.0;
        } else if (650 <= wavelength && wavelength < 780){
            red = 1.0;
            green = 0.0;
            blue = 0.0;
        } else {
            red = 0.0;
            green = 0.0;
            blue = 0.0;
        }
        red = (intensity * Math.pow(red, 0.8));
        green = (intensity * Math.pow(green, 0.8));
        blue = (intensity * Math.pow(blue, 0.8));

        int[] rgb = {(int) red, (int) green, (int) blue};
        return rgb;

    }

    //removes global layout listener correctly depending on api
    @TargetApi(16)
    public static void removeOnGlobalLayoutListener(View v, ViewTreeObserver.OnGlobalLayoutListener listener){
        try {
            v.getViewTreeObserver().removeOnGlobalLayoutListener(listener);
        } catch (NoSuchMethodError x) {
            v.getViewTreeObserver().removeGlobalOnLayoutListener(listener);
        }
    }

    //Initiates popup to explain experiment
    public void initiatePopupWindow(final View view) {
        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        final View popupView = inflater.inflate(R.layout.popup_experiement_colour_tuning, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);
        popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, 0, 0);
        popup = popupWindow;


        ImageButton cancel = popupView.findViewById(R.id.CancelPopupButton);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popup.dismiss();
            }
        });
    }
}
