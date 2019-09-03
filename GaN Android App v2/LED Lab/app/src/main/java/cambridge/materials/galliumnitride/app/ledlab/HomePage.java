package cambridge.materials.galliumnitride.app.ledlab;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class HomePage extends AppCompatActivity {
    static int lastTransition;
    int lastImage;
    int[] images = {R.drawable.gan, R.drawable.gan1, R.drawable.gan2, R.drawable.gan3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_home);


        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        int totalWidth = size.x;
        int totalHeight = size.y;

        //Set title bar fonts
        TextView appName = findViewById(R.id.AppName);
        Typeface tajamuka = Typeface.createFromAsset(getAssets(),  "fonts/tajamuka_script.ttf");
        appName.setTypeface(tajamuka);


        TextView by = findViewById(R.id.by);
        by.setTypeface(tajamuka);


        TextView creator = findViewById(R.id.creator);
        Typeface gruppo = Typeface.createFromAsset(getAssets(),  "fonts/gruppo.ttf");
        creator.setTypeface(gruppo);

        String screenSize = getScreenSize();
        switch(screenSize) {
            case "320":
                appName.setTextSize(32);
                by.setTextSize(10);
                creator.setTextSize(14);
                break;
            case "400":
                appName.setTextSize(38);
                by.setTextSize(12);
                creator.setTextSize(16);
                break;
            case "480":
                appName.setTextSize(52);
                by.setTextSize(18);
                creator.setTextSize(20);
                break;
            case "720":
                appName.setTextSize(65);
                by.setTextSize(26);
                creator.setTextSize(28);
                break;
        }



        //Set up functionality for classroom button
        final ImageButton classroomButton = findViewById(R.id.ClassroomButton);
        classroomButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent loadClassroomActivity = new Intent(HomePage.this, ClassroomPage.class);
                HomePage.this.startActivity(loadClassroomActivity);
            }
        });

        //Set up functionality for quiz button
        final ImageButton gamesButton = findViewById(R.id.GamesButton);
        gamesButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.youtube.com/channel/UClpTu_hm2DUPIg_-cmXs8ow");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        //Set up functionality for search button
        final ImageButton searchButton = findViewById(R.id.SearchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent loadSearchActivity = new Intent(HomePage.this, SearchPage.class);
                HomePage.this.startActivity(loadSearchActivity);
            }
        });

        //Set up functionality for laboratory button
        final ImageButton laboratoryButton = findViewById(R.id.LaboratoryButton);
        laboratoryButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent loadLaboratoryActivity = new Intent(HomePage.this, LaboratoryPage.class);
                HomePage.this.startActivity(loadLaboratoryActivity);
            }
        });

        //Set up functionality for website button
        ImageButton websiteButton = findViewById(R.id.WebsiteButton);
        websiteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Uri uri = Uri.parse("http://www.gan.msm.cam.ac.uk");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        //Set up functionality for youtube button
        ImageButton youtubeButton = findViewById(R.id.YoutubeButton);
        youtubeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.youtube.com/channel/UClpTu_hm2DUPIg_-cmXs8ow");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        //Set up functionality for pinterest button
        ImageButton pinterestButton = findViewById(R.id.PinterestButton);
        pinterestButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.pinterest.co.uk/msmgan");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        //Set up functionality for linked in button
        ImageButton linkedInButton = findViewById(R.id.LinkedInButton);
        linkedInButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.linkedin.com/company/cambridge-centre-for-gallium-nitride");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        //Set up functionality for contact us button
        final ImageButton contactUsButton = findViewById(R.id.ContactUsButton);
        contactUsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent loadContactUsActivity = new Intent(HomePage.this, ContactUsPage.class);
                HomePage.this.startActivity(loadContactUsActivity);
            }
        });

        //Set up functionality for animated image view
        final ImageView liveButton = findViewById(R.id.FeaturedPagesButton);
        final Handler imageSwitcherHandler;
        imageSwitcherHandler = new Handler(Looper.getMainLooper());
        imageSwitcherHandler.post(new Runnable() {
            public void run() {
                Random rand = new Random();
                int n = rand.nextInt(images.length);
                while (lastImage == images[n]){
                    Random r = new Random();
                    n = r.nextInt(images.length);
                }
                ImageButtonAnimatedChange(liveButton.getContext(), liveButton, images[n]);
                lastImage = images[n];

                imageSwitcherHandler.postDelayed(this, 15000);
            }
        });

        //Set up classroom icon and make classroom icon move when pressed
        final ImageView classroomIcon = findViewById(R.id.ClassroomIcon);
        setBackgroundInProportion(classroomIcon, R.drawable.ic_button_classroom, totalWidth*2/5, totalHeight/8);
        classroomButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    buttonPress(classroomIcon);
                    classroomButton.setBackgroundResource(R.drawable.button_classroom_pressed);
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    buttonUnpress(classroomIcon);
                    classroomButton.setBackgroundResource(R.drawable.button_classroom);
                }
                return false;
            }
        });


        //Set up games icon and make games icon move when pressed
        final ImageView gamesIcon = findViewById(R.id.GamesIcon);
        setBackgroundInProportion(gamesIcon, R.drawable.ic_button_video, totalWidth/6, totalHeight/10);
        gamesButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    buttonPress(gamesIcon);
                    gamesButton.setBackgroundResource(R.drawable.button_games_pressed);
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    buttonUnpress(gamesIcon);
                    gamesButton.setBackgroundResource(R.drawable.button_games);
                }
                return false;
            }
        });

        //Set up search icon and make search icon move when pressed
        final ImageView searchIcon = findViewById(R.id.SearchIcon);
        setBackgroundInProportion(searchIcon, R.drawable.ic_button_search, totalWidth/6, totalHeight/10);
        searchButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    buttonPress(searchIcon);
                    searchButton.setBackgroundResource(R.drawable.button_search_pressed);
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    buttonUnpress(searchIcon);
                    searchButton.setBackgroundResource(R.drawable.button_search);
                }
                return false;
            }
        });

        //Set up laboratory icon and make laboratory icon move when pressed
        final ImageView laboratoryIcon = findViewById(R.id.LaboratoryIcon);
        setBackgroundInProportion(laboratoryIcon, R.drawable.ic_button_laboratory, totalWidth*2/5, totalHeight/8);
        laboratoryButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    buttonPress(laboratoryIcon);
                    laboratoryButton.setBackgroundResource(R.drawable.button_laboratory_pressed);
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    buttonUnpress(laboratoryIcon);
                    laboratoryButton.setBackgroundResource(R.drawable.button_laboratory);
                }
                return false;
            }
        });

        //Set up contact us icon and make contact us icon move when pressed
        final ImageView contactUsIcon = findViewById(R.id.ContactUsIcon);
        setBackgroundInProportion(contactUsIcon, R.drawable.ic_button_contact_us, totalWidth*2/5, totalHeight/12);
        contactUsButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    buttonPress(contactUsIcon);
                    contactUsButton.setBackgroundResource(R.drawable.button_contact_us_pressed);
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    buttonUnpress(contactUsIcon);
                    contactUsButton.setBackgroundResource(R.drawable.button_contact_us);
                }
                return false;
            }
        });
    }

    //Method that changes the image in the animated image view
    public static void ImageButtonAnimatedChange(Context c, final ImageView view, final int new_image) {
        //Generates random number to determine whether transition is up, down, left or right
        Random rand = new Random();
        int n = rand.nextInt(4);
        while (lastTransition == n){
            Random r = new Random();
            n = r.nextInt(4);
        }

        //Initialise in and out animations
        final Animation anim_in;
        final Animation anim_out;

        //Set animations
        if (n == 0) {
            anim_in = AnimationUtils.loadAnimation(c, R.anim.left_to_right_in);
            anim_out = AnimationUtils.loadAnimation(c, R.anim.left_to_right_out);
            lastTransition = 0;
        } else if (n == 1) {
            anim_in = AnimationUtils.loadAnimation(c, R.anim.right_to_left_in);
            anim_out  = AnimationUtils.loadAnimation(c, R.anim.right_to_left_out);
            lastTransition = 1;
        } else if (n == 2) {
            anim_in = AnimationUtils.loadAnimation(c, R.anim.top_to_bottom_in);
            anim_out  = AnimationUtils.loadAnimation(c, R.anim.top_to_bottom_out);
            lastTransition = 2;
        } else {
            anim_in = AnimationUtils.loadAnimation(c, R.anim.bottom_to_top_in);
            anim_out  = AnimationUtils.loadAnimation(c, R.anim.bottom_to_top_out);
            lastTransition = 3;
        }

        //Implement animations when background of image view is changed
        anim_out.setAnimationListener(new Animation.AnimationListener()
        {
            @Override public void onAnimationStart(Animation animation) {}
            @Override public void onAnimationRepeat(Animation animation) {}
            @Override public void onAnimationEnd(Animation animation)
            {
                view.setImageResource(new_image);
                anim_in.setAnimationListener(new Animation.AnimationListener() {
                    @Override public void onAnimationStart(Animation animation) {}
                    @Override public void onAnimationRepeat(Animation animation) {}
                    @Override public void onAnimationEnd(Animation animation) {}
                });
                view.startAnimation(anim_in);
            }
        });
        view.startAnimation(anim_out);
    }

    //Sets an image view fit within given dimensions in proportion to raw image
    public ViewGroup.LayoutParams setBackgroundInProportion(ImageView view, int background, int width, int height){
        BitmapDrawable bd = (BitmapDrawable) ContextCompat.getDrawable(view.getContext(), background);
        double drawableHeight = bd.getBitmap().getHeight();
        double drawableWidth = bd.getBitmap().getWidth();
        double heightRatio = height/drawableHeight;
        double widthRatio = width/drawableWidth;


        ViewGroup.LayoutParams lp = view.getLayoutParams();
        if (heightRatio<widthRatio){
            lp.height = height;
            lp.width = (int) (height * drawableWidth/drawableHeight);
        } else {
            lp.width = width;
            lp.height = (int) (width * drawableHeight/drawableWidth);
        }

        view.setBackgroundResource(background);
        view.setLayoutParams(lp);
        return lp;
    }

    //Change margins to move view when pressed
    public void buttonPress(ImageView view){
        Resources r = getResources();
        int shift = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, r.getDisplayMetrics());

        ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        mlp.setMargins(mlp.leftMargin, mlp.topMargin+shift, mlp.rightMargin+shift, mlp.bottomMargin);
        view.requestLayout();
    }

    //Change margins to move view when unpressed
    public void buttonUnpress(ImageView view){
        Resources r = getResources();
        int shift = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, r.getDisplayMetrics());

        ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        mlp.setMargins(mlp.leftMargin, mlp.topMargin-shift, mlp.rightMargin-shift, mlp.bottomMargin);
        view.requestLayout();
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


