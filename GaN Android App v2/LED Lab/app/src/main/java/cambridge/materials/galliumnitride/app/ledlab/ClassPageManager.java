package cambridge.materials.galliumnitride.app.ledlab;

import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

//Used to organise pages within a class and change page given a page number

public class ClassPageManager {
    private int mLevel;
    private int mPageCount;
    private int mCurrentPage; //1 to mPageCount
    private TopicInformation mTopic;


    public ClassPageManager(TopicInformation topic, int level) {
        mTopic = topic;
        mLevel = level;
        mPageCount = mTopic.getPageCount(mLevel);
        mCurrentPage = 1;
    }

    public int getPageCount(){
        return mPageCount;
    }

    public int getCurrentPage(){
        return mCurrentPage;
    }

    public void changePage(View classPage, int page){

        //Get views, class texts and class animations
        int[] animations = mTopic.getAnimations(mLevel);
        String[] texts = mTopic.getTexts(mLevel);
        ImageView ani = classPage.findViewById(R.id.PageAnimation);
        TextView classText = classPage.findViewById(R.id.PageText);

        //Set animation and text for new page
        ani.setImageResource(animations[page-1]);
        classText.setText(Html.fromHtml(texts[page-1]));

        //Change visibility of previous page button
        if(page == 1){
            classPage.findViewById(R.id.PreviousPageButton).setVisibility(View.GONE);
        } else if (mCurrentPage==1 && page == 2){
            classPage.findViewById(R.id.PreviousPageButton).setVisibility(View.VISIBLE);
        }

        //Change visibility of next page button
        if (page == mPageCount){
            classPage.findViewById(R.id.NextPageButton).setVisibility(View.GONE);
        } else if (page == mPageCount-1 && mCurrentPage==mPageCount){
            classPage.findViewById(R.id.NextPageButton).setVisibility(View.VISIBLE);
        }

        //Change visibility of class complete button
        if (page == mPageCount){
            classPage.findViewById(R.id.ClassCompleteButton).setVisibility(View.VISIBLE);
        } else if (page == mPageCount-1 && mCurrentPage==mPageCount){
            classPage.findViewById(R.id.ClassCompleteButton).setVisibility(View.GONE);
        }

        mCurrentPage = page;
    }
}
