package cambridge.materials.galliumnitride.app.ledlab;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Spannable;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

//Used when LED switch experiment is opened

public class ExperimentLEDSwitch extends AppCompatActivity {
    String[] BulbTypes = {"Select Bulb Type", "CFL (Compact Fluorescent Lamp)", "Halogen Spotlight", " Traditional Incandescent"};
    Integer[] BulbImages = {0, R.drawable.experiment_led_switch_cflbulb, R.drawable.experiment_led_switch_halogenspotlight, R.drawable.experiment_led_switch_incandescentbulb};

    private double dailyTimeOn = 5; //in hrs
    private double electricityCost = 12.564; //in p/kWh

    //Bulb power in W
    final private static double CFLWattage = 14;
    final private static double HalogenWattage = 45;
    final private static double IncandescentWattage = 60;
    final private static double LEDWattage = 8;

    //Cost per bulb in £
    final private static double IncandescentCost = 0.90;
    final private static double HalogenCost = 1.30;
    final private static double CFLCost = 5.50;
    final private static double LEDCost = 4.50;

    // Bulb Lifetimes in hrs
    final private static double IncandescentLifetime = 1000;
    final private static double HalogenLifetime = 1000;
    final private static double CFLLifetime = 10000;
    final private static double LEDLifetime = 30000;

    private int currBulbPos = 0;
    private int bulbNumber = 1;
    private double bulbWattage = 1; //in W
    private double bulbCostPerHour = 0;
    private double dailyTime = 5; //in hrs

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experiment_led_switch);

        //Fonts, to be used later
        Typeface tajamuka = Typeface.createFromAsset(getAssets(), "fonts/tajamuka_script.ttf");
        Typeface gruppo = Typeface.createFromAsset(getAssets(), "fonts/gruppo.ttf");
        final Typeface mako = Typeface.createFromAsset(getAssets(), "fonts/mako.ttf");

        //Set title bar fonts
        ((TextView) findViewById(R.id.AppName)).setTypeface(tajamuka);
        ((TextView) findViewById(R.id.by)).setTypeface(tajamuka);
        ((TextView) findViewById(R.id.creator)).setTypeface(gruppo);
        ((TextView) findViewById(R.id.LEDSwitchTitle)).setTypeface(tajamuka);

        //Set up click listeners for back and home button
        ImageButton BackButton = findViewById(R.id.backButton);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExperimentLEDSwitch.this, LaboratoryPage.class);
                startActivity(intent);
            }
        });

        ImageButton HomeButton = findViewById(R.id.homeButton);
        HomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExperimentLEDSwitch.this, HomePage.class);
                startActivity(intent);
            }
        });

        //Initiate view (equivalent to onCreatView)
        final View rootView = getWindow().getDecorView().getRootView();
        rootView.getViewTreeObserver().addOnGlobalLayoutListener( new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //Avoids infinite loop
                removeOnGlobalLayoutListener(rootView, this);

                //Set font for text for current bulb
                TextView currBulbText = rootView.findViewById(R.id.CurrentBulbText);
                currBulbText.setTypeface(mako, Typeface.BOLD);

                //Set up spinner with correct adapter for current bulb input
                ArrayAdapter adapter = new ImageTextAdapter(rootView.getContext(), R.layout.list_item_bulb_option, BulbTypes);
                adapter.setDropDownViewResource(R.layout.list_item_bulb_option);
                Spinner currBulb = rootView.findViewById(R.id.CurrentBulbOption);
                currBulb.setAdapter(adapter);
                currBulb.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        currBulbPos = position;
                        updateResult();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {}
                });

                //Set font for text for new bulb
                TextView newBulbText = rootView.findViewById(R.id.NewBulbText);
                newBulbText.setTypeface(mako, Typeface.BOLD);

                //Set font for text for number of bulbs
                TextView bulbNumberText = rootView.findViewById(R.id.BulbNumberText);
                bulbNumberText.setTypeface(mako, Typeface.BOLD);

                //Set up input box for number of bulbs with default value and listener
                EditText bulbNumberInput = rootView.findViewById(R.id.BulbNumberInput);
                bulbNumberInput.setText("");
                bulbNumberInput.append("1");
                bulbNumberInput.addTextChangedListener(new TextWatcher() {
                    public void afterTextChanged(Editable s) {}
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        bulbNumber = s.toString().equals("") ? 0 : Integer.valueOf(""+s);
                        updateResult();
                    }
                });

                //Find text boxes for use later
                TextView results1 = findViewById(R.id.CurrentTextView);
                TextView results2 = findViewById(R.id.WithLEDTextView);
                TextView results3 = findViewById(R.id.SavingsTextView);

                //Set the font of the results panel
                Typeface teen = Typeface.createFromAsset(rootView.getContext().getAssets(),  "fonts/teen.ttf");
                results1.setTypeface(teen);
                results2.setTypeface(teen);
                results3.setTypeface(teen);
            }
        });
    }

    //method to check that all input values are valid
    public boolean isInputValid(){
        return (currBulbPos!=0 && bulbNumber>0 && electricityCost>0 && bulbWattage>0 && dailyTimeOn>0);
    }

    //Removes layout listener correctly depending on api
    @TargetApi(16)
    public static void removeOnGlobalLayoutListener(View v, ViewTreeObserver.OnGlobalLayoutListener listener){
        try {
            v.getViewTreeObserver().removeOnGlobalLayoutListener(listener);
        } catch (NoSuchMethodError x) {
            v.getViewTreeObserver().removeGlobalOnLayoutListener(listener);
        }
    }

    //Adapter for spinner to pick bulb type
    public class ImageTextAdapter extends ArrayAdapter<String> {
        Context context;

        //Makes first item in spinner not selectable
        @Override
        public boolean isEnabled(int position) {
            return (position!=0);
        }

        public ImageTextAdapter(Context c, int textViewResourceId, String[] objects) {
            super(c, textViewResourceId, objects);
            context = c;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, parent);
        }

        public View getCustomView(int position, ViewGroup parent) {
            View spinnerItem = LayoutInflater.from(this.getContext()).inflate(R.layout.list_item_bulb_option, parent, false);

            //Set text for the spinner item
            TextView label = spinnerItem.findViewById(R.id.BulbName);
            label.setText(BulbTypes[position]);

            //Set image for spinner item
            ImageView icon = spinnerItem.findViewById(R.id.BulbImage);
            icon.setBackgroundResource(BulbImages[position]);

            //Makes the first item in spinner the default 'select a bulb type'
            if (position==0) {
                Context c = spinnerItem.getContext();
                icon.setVisibility(View.GONE);
                label.setBackgroundColor(ContextCompat.getColor(c, R.color.lightGrey));
                label.setTypeface(null, Typeface.BOLD);
                String screenSize = getScreenSize();
                switch (screenSize) {
                    case ("320"):
                        label.setTextSize(12);
                        break;
                    case ("400"):
                        label.setTextSize(16);
                        break;
                    case ("480"):
                        label.setTextSize(22);
                        break;
                    case("720"):
                        label.setTextSize(28);
                        break;
                }

                label.setTextColor(ContextCompat.getColor(context, R.color.deepRed));
            }

            return spinnerItem;
        }
    }

    public void updateResult(){
        if (isInputValid()){
            TextView results1 = findViewById(R.id.CurrentTextView);
            TextView results2 = findViewById(R.id.WithLEDTextView);
            TextView results3 = findViewById(R.id.SavingsTextView);


            //Update bulb wattage value with the one correponding to chosen bulb
            if (currBulbPos==1){
                bulbWattage = CFLWattage;
                bulbCostPerHour = CFLCost/CFLLifetime;
            } else if (currBulbPos==2) {
                bulbWattage = HalogenWattage;
                bulbCostPerHour = HalogenCost/HalogenLifetime;
            } else if (currBulbPos==3) {
                bulbWattage = IncandescentWattage;
                bulbCostPerHour = IncandescentCost/IncandescentLifetime;
            }

            //Calculate usage, cost and savings values
            double currUsage = bulbWattage*bulbNumber*dailyTimeOn*0.36525;
            double currCost = currUsage*electricityCost/100;
            double newUsage = LEDWattage*bulbNumber*dailyTimeOn*0.36525;
            double newCost = newUsage*electricityCost/100;

            double percentSaving = 100-(100*newUsage/currUsage);
            double costSaving = currCost-newCost;


            //Calculate payback time
            double paybackTime = 12*LEDCost/((((bulbWattage-LEDWattage) * electricityCost/100000) + bulbCostPerHour)*dailyTime*365.25);
            int yrs = (int) paybackTime/12;
            int months = ((int) paybackTime)%12;



            //Set text in result Fragment with new values
            results1.setText("");
            results1.append(getString(R.string.CurrentUse));
            results1.append(" "+((int) currUsage));
            results1.append("kWh per year");
            results1.append("" + getString(R.string.CurrentCost));
            results1.append(""+((int) currCost));
            results1.append(" per year");

            results2.setText("");
            results2.append("" + getString(R.string.LEDUse));
            results2.append(" "+((int) newUsage));
            results2.append("kWh per year");
            results2.append("" + getString(R.string.LEDCost));
            results2.append(""+((int) newCost));
            results2.append(" per year");

            results3.setText("");
            results3.append("" + getString(R.string.Savings));
            results3.append(" "+ ((int) percentSaving));
            results3.append("% or £" + ((int) costSaving));
            results3.append(" per year");
            results3.append("" + getString(R.string.PaybackTime));
            if (yrs!=0){
                results3.append(" " + yrs + " yrs and");
            }
            results3.append(" " + months + " months");

            //Make important parts of text bold and colourful
            Spannable s1 = (Spannable) results1.getText();
            s1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, 16, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            s1.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.deepRed)), 16, String.valueOf(((int)currUsage)).length() + 20, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            s1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), String.valueOf(((int)currUsage)).length() + 28, String.valueOf(((int)currUsage)).length() + 43, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            s1.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.deepRed)), String.valueOf(((int)currUsage)).length() + 43, String.valueOf(((int)currUsage)).length() + 46 + String.valueOf(((int)currCost)).length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            Spannable s2 = (Spannable) results2.getText();
            s2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, 18, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            s2.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.deepRed)), 18, String.valueOf(((int)newUsage)).length() + 22, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            s2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), String.valueOf(((int)newUsage)).length() + 31, String.valueOf(((int)newUsage)).length() + 49, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            s2.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.deepRed)), String.valueOf(((int)newUsage)).length() + 48, String.valueOf(((int)newUsage)).length() + 50 + String.valueOf(((int)newCost)).length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            Spannable s3 = (Spannable) results3.getText();
            s3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, 9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            s3.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.deepRed)), 9, String.valueOf(((int)percentSaving)).length() + 11, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            s3.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.deepRed)), String.valueOf(((int)percentSaving)).length() + 14, String.valueOf(((int)percentSaving)).length() + 16 + String.valueOf(((int) costSaving)).length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            s3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), String.valueOf(((int)percentSaving)).length() + 24 + String.valueOf(((int) costSaving)).length(), String.valueOf(((int)percentSaving)).length() + 40 + String.valueOf(((int) costSaving)).length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            s3.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.deepRed)), String.valueOf(((int)percentSaving)).length() + 40 + String.valueOf(((int) costSaving)).length(), results3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        } else {
            //If input is invalid, display error message
            TextView results1 = findViewById(R.id.CurrentTextView);
            TextView results2 = findViewById(R.id.WithLEDTextView);
            TextView results3 = findViewById(R.id.SavingsTextView);
            results1.setText("");
            results2.setText(R.string.InvalidInputMessage);
            results3.setText("");

        }
    }


    //Method to get screen size category
    public String getScreenSize(){
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        String result;
        if (dpWidth<400){
            result = "320";
        } else if (dpWidth<480){
            result = "400";
        } else if (dpWidth<720){
            result = "480";
        } else {
            result = "720";
        }

        return result;
    }
}
