package cambridge.materials.galliumnitride.app.ledlab;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ContactUsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_contact_us);

        //Fonts, to be used later
        Typeface tajamuka = Typeface.createFromAsset(getAssets(),  "fonts/tajamuka_script.ttf");
        Typeface gruppo = Typeface.createFromAsset(getAssets(),  "fonts/gruppo.ttf");

        //Set title bar fonts
        ((TextView)findViewById(R.id.AppName)).setTypeface(tajamuka);
        ((TextView)findViewById(R.id.by)).setTypeface(tajamuka);
        ((TextView)findViewById(R.id.creator)).setTypeface(gruppo);
        ((TextView) findViewById(R.id.ContactUsTitle)).setTypeface(tajamuka);

        //Set click listeners for back and home buttons
        ImageButton BackButton = findViewById(R.id.backButton);
        BackButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ContactUsPage.this, HomePage.class);
                startActivity(intent);
            }
        });

        ImageButton HomeButton = findViewById(R.id.homeButton);
        HomeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ContactUsPage.this, HomePage.class);
                startActivity(intent);
            }
        });

        //Set click listeners for website links
        TextView msmWebButton = findViewById(R.id.MsmWebsiteInfo);
        msmWebButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://www.msm.cam.ac.uk/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        TextView ganWebButton = findViewById(R.id.GanWebsiteInfo);
        ganWebButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://www.gan.msm.cam.ac.uk/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        //Set click listeners for social media buttons
        ImageButton youtubeButton = findViewById(R.id.YoutubeButton);
        youtubeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.youtube.com/channel/UClpTu_hm2DUPIg_-cmXs8ow");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        ImageButton pinterestButton = findViewById(R.id.PinterestButton);
        pinterestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.pinterest.co.uk/msmgan");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        ImageButton linkedInButton = findViewById(R.id.LinkedInButton);
        linkedInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.linkedin.com/company/cambridge-centre-for-gallium-nitride");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        //Make appropriate text bold
        TextView telephoneHeader = findViewById(R.id.TelephoneHeader);
        telephoneHeader.setText(R.string.ContactUsTelephoneHeader, TextView.BufferType.SPANNABLE);
        Spannable s1 = (Spannable) telephoneHeader.getText();
        s1.setSpan(new android.text.style.StyleSpan(Typeface.BOLD), 0, 10, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        TextView emailInfo = findViewById(R.id.EmailInfo);
        emailInfo.setText(R.string.ContactUsEmailInfo, TextView.BufferType.SPANNABLE);
        Spannable s2 = (Spannable) emailInfo.getText();
        s2.setSpan(new android.text.style.StyleSpan(Typeface.BOLD_ITALIC), 75, 100, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

}
