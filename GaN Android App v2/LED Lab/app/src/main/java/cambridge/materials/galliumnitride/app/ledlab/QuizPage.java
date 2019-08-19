package cambridge.materials.galliumnitride.app.ledlab;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class QuizPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_page);

        //Fonts, to be used later
        Typeface tajamuka = Typeface.createFromAsset(getAssets(),  "fonts/tajamuka_script.ttf");
        Typeface gruppo = Typeface.createFromAsset(getAssets(),  "fonts/gruppo.ttf");
        Typeface pajamapants = Typeface.createFromAsset(getAssets(),  "fonts/pajamapants.ttf");

        //Set title bar fonts
        ((TextView)findViewById(R.id.AppName)).setTypeface(tajamuka);
        ((TextView)findViewById(R.id.by)).setTypeface(tajamuka);
        ((TextView)findViewById(R.id.creator)).setTypeface(gruppo);
        ((TextView) findViewById(R.id.QuizTitle)).setTypeface(pajamapants);
        ((TextView) findViewById(R.id.QuizDescription)).setTypeface(pajamapants);


        ImageButton BackButton = findViewById(R.id.backButton);
        BackButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(QuizPage.this, HomePage.class);
                startActivity(intent);
            }
        });

        ImageButton HomeButton = findViewById(R.id.homeButton);
        HomeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(QuizPage.this, HomePage.class);
                startActivity(intent);
            }
        });





    }
}
