package cambridge.materials.galliumnitride.app.ledlab;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

//The activity used when a class is opened

public class ClassPage extends AppCompatActivity {
    private ClassPageManager mClassPageManager;
    TopicInformation mTopic;
    int mLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);

        //Extract the topic and level of the class and create a Class Page Manager for this class
        mTopic = getIntent().getParcelableExtra("Topic");
        mLevel = getIntent().getIntExtra("Level", 0);
        mClassPageManager = new ClassPageManager(mTopic, mLevel);

        //Fonts, to be used later
        Typeface tajamuka = Typeface.createFromAsset(getAssets(),  "fonts/tajamuka_script.ttf");
        Typeface gruppo = Typeface.createFromAsset(getAssets(),  "fonts/gruppo.ttf");

        //Set title bar fonts
        ((TextView)findViewById(R.id.AppName)).setTypeface(tajamuka);
        ((TextView)findViewById(R.id.by)).setTypeface(tajamuka);
        ((TextView)findViewById(R.id.creator)).setTypeface(gruppo);

        //Set class name text and font
        TextView topicTitle = findViewById(R.id.TopicTitle);
        String title = ((TopicInformation)getIntent().getParcelableExtra("Topic")).getTopic();
        topicTitle.setText((title + " "));
        for (int i = 0; i<getIntent().getIntExtra("Level", 0); i++){
            topicTitle.append("I");
        }
        topicTitle.setTypeface(tajamuka);
        topicTitle.setTextColor(ContextCompat.getColor(this, R.color.indigo));

        //Set click listeners for back and home buttons
        ImageButton BackButton = findViewById(R.id.backButton);
        BackButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ClassPage.this, ClassroomPage.class);
                startActivity(intent);
            }
        });

        ImageButton HomeButton = findViewById(R.id.homeButton);
        HomeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ClassPage.this, HomePage.class);
                startActivity(intent);
            }
        });


        //Set next page listener on animation
        final ImageView ani = findViewById(R.id.PageAnimation);
        ani.setOnTouchListener(new View.OnTouchListener() {
            private int CLICK_ACTION_THRESHOLD = 200;
            private float startX;
            private float startY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getX();
                        startY = event.getY();
                        return true;
                    case MotionEvent.ACTION_UP:
                        float endX = event.getX();
                        float endY = event.getY();
                        if (isAClick(startX, endX, startY, endY)) {
                            if (mClassPageManager.getCurrentPage() < mClassPageManager.getPageCount()) {
                                mClassPageManager.changePage(findViewById(R.id.ClassPage), mClassPageManager.getCurrentPage()+1);
                            }
                        }
                        return true;
                }
                return false;
            }

            private boolean isAClick(float startX, float endX, float startY, float endY) {
                float differenceX = Math.abs(startX - endX);
                float differenceY = Math.abs(startY - endY);
                return !(differenceX > CLICK_ACTION_THRESHOLD/* =5 */ || differenceY > CLICK_ACTION_THRESHOLD);
            }
        });

        //Set listener for previous page button
        ImageButton previousClassButton = findViewById(R.id.PreviousPageButton);
        previousClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClassPageManager.changePage(findViewById(R.id.ClassPage), mClassPageManager.getCurrentPage()-1);
            }
        });

        //Set listener for class complete buttons
        ImageButton classCompleteButton = findViewById(R.id.ClassCompleteButton);
        classCompleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loadStudentsActivity = new Intent(ClassPage.this, ClassroomPage.class);
                startActivity(loadStudentsActivity);
            }
        });

        //Ensures animation is approximately square
        ani.getViewTreeObserver().addOnGlobalLayoutListener( new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //Avoids infinite loop
                removeOnGlobalLayoutListener(ani, this);

                int width = ani.getWidth();
                int height = ani.getHeight();

                if (width>height*0.85 && width < 1.15*height){
                    ani.setScaleType(ImageView.ScaleType.FIT_XY);
                }

                //Set animation and text for first page
                TextView pageText = findViewById(R.id.PageText);
                int animationID = mTopic.getAnimations(mLevel)[mClassPageManager.getCurrentPage()-1];
                ani.setImageResource(animationID);
                String text = mTopic.getTexts(mLevel)[mClassPageManager.getCurrentPage()-1];
                pageText.setText(text);
            }
        });
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
}
