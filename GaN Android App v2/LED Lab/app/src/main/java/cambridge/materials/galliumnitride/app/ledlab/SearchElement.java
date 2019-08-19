package cambridge.materials.galliumnitride.app.ledlab;

import android.app.Activity;

//Contains all the information required for each search element in a search list

public class SearchElement {
    private String[] mTags;
    private String mType;
    private Activity mActivity;
    private int mIcon;
    private String mText;
    private TopicInformation mTopic;
    private int mColour;


    //For pages
    public SearchElement (String type, String[] tags, String text, int icon, Activity activity, int colour){
        mType = type;
        mTags = tags;
        mText = text;
        mIcon = icon;
        mActivity = activity;
        mColour = colour;
    }

    //For classes
    public SearchElement (String type, String[] tags, TopicInformation topic){
        mType = type;
        mTags = tags;
        mTopic = topic;
    }

    //For experiments
    public SearchElement (String type, String[] tags, String text, int icon, Activity activity){
        mType = type;
        mTags = tags;
        mText = text;
        mIcon = icon;
        mActivity = activity;
    }

    public SearchElement (String type, String text){
        mType = type;
        mText = text;
    }

    //Getters
    public String getType(){
        return mType;
    }
    public String[] getTags(){
        return mTags;
    }
    public Activity getActivity(){
        return mActivity;
    }
    public int getIcon(){
        return mIcon;
    }
    public String getText(){
        return mText;
    }
    public TopicInformation getTopicInformation(){
        return mTopic;
    }
    public int getColour(){ return mColour; }
}
