package cambridge.materials.galliumnitride.app.ledlab;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

//Used when the classroom page is opened

public class ClassroomPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_classroom);

        //Fonts, to be used later
        Typeface tajamuka = Typeface.createFromAsset(getAssets(),  "fonts/tajamuka_script.ttf");
        Typeface gruppo = Typeface.createFromAsset(getAssets(),  "fonts/gruppo.ttf");
        Typeface pajamapants = Typeface.createFromAsset(getAssets(),  "fonts/pajamapants.ttf");


        //Set title bar fonts
        ((TextView)findViewById(R.id.AppName)).setTypeface(tajamuka);
        ((TextView)findViewById(R.id.by)).setTypeface(tajamuka);
        ((TextView)findViewById(R.id.creator)).setTypeface(gruppo);
        ((TextView) findViewById(R.id.ClassroomTitle)).setTypeface(pajamapants);

        //Set page description text and fonts
        TextView description = findViewById(R.id.ClassroomDescription);
        description.setTypeface(pajamapants);
        int[] ids = {R.id.dash1, R.id.dash2, R.id.dash3, R.id.ClassroomContentsExplanation1, R.id.ClassroomContentsExplanation2, R.id.ClassroomContentsExplanation3};
        for (int i = 0; i<6; i++) {
            TextView descriptionList = findViewById(ids[i]);
            descriptionList.setTypeface(pajamapants);
        }

        //Set Listeners on back/home buttons
        ImageButton BackButton = findViewById(R.id.backButton);
        BackButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ClassroomPage.this, HomePage.class);
                startActivity(intent);
            }
        });

        ImageButton HomeButton = findViewById(R.id.homeButton);
        HomeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ClassroomPage.this, HomePage.class);
                startActivity(intent);
            }
        });

        //Initialise and set up class list
        TopicInformationInitialiser tii = new TopicInformationInitialiser(this);
        ArrayList<TopicInformation> topics = tii.getTopics();
        final ListView lv = findViewById(R.id.ClassList);
        lv.setAdapter(new ClassListAdapter(this, topics));
    }
}
