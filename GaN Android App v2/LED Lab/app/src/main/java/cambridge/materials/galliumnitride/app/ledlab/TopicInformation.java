package cambridge.materials.galliumnitride.app.ledlab;

import android.os.Parcel;
import android.os.Parcelable;

//Used to hold all the information about each topic
//The constructor requires that the size of the array of animations and texts must match the page count for each level, otherwise an exception will be thrown

public class TopicInformation implements Parcelable {
    private String mTopic;
    private int mIcon;
    private String[] mExpectations;
    private int[] mLevelPageCounts;
    private int[] mLevel1Animations;
    private int[] mLevel2Animations;
    private int[] mLevel3Animations;
    private String[] mLevel1Texts;
    private String[] mLevel2Texts;
    private String[] mLevel3Texts;

    public TopicInformation(String topic, int icon, String[] expectations, int[] levelPageCounts, int[] level1Animations, int[] level2Animations, int[] level3Animations, String[] level1Texts, String[] level2Texts, String[] level3Texts) {
        mTopic = topic;
        mIcon = icon;
        mExpectations = expectations;
        try{
            Exception e = new InvalidTopicInputException();
            if (levelPageCounts.length==3){
                mLevelPageCounts = levelPageCounts;

            } else {
                throw new InvalidTopicInputException("The list of page counts for the topic called " + mTopic + " does not contain 3 values (one for each class) like it must", e);
            }
            if (level1Animations.length==mLevelPageCounts[0] && level2Animations.length==mLevelPageCounts[1] && level3Animations.length==mLevelPageCounts[2]){
                mLevel1Animations = level1Animations;
                mLevel2Animations = level2Animations;
                mLevel3Animations = level3Animations;
            } else {
                throw new InvalidTopicInputException("The lengths of the lists of animations for the classes in the topic called " + mTopic + " do not match the page counts for this topic", e);
            }
            if (level1Texts.length==mLevelPageCounts[0] && level2Texts.length==mLevelPageCounts[1] && level3Texts.length==mLevelPageCounts[2]){
                mLevel1Texts = level1Texts;
                mLevel2Texts = level2Texts;
                mLevel3Texts = level3Texts;
            } else {
                throw new InvalidTopicInputException("The lengths of the lists of texts for the classes in the topic called " + mTopic + " do not match the page counts for this topic", e);
            }
        } catch (InvalidTopicInputException ex) {
            System.out.println("Invalid Topic Input Exception: " + ex.getMessage());
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(mTopic);
        out.writeInt(mIcon);
        out.writeStringArray(mExpectations);
        out.writeIntArray(mLevelPageCounts);
        out.writeIntArray(mLevel1Animations);
        out.writeIntArray(mLevel2Animations);
        out.writeIntArray(mLevel3Animations);
        out.writeStringArray(mLevel1Texts);
        out.writeStringArray(mLevel2Texts);
        out.writeStringArray(mLevel3Texts);
    }

    public static final Parcelable.Creator<TopicInformation> CREATOR = new Parcelable.Creator<TopicInformation>() {
        public TopicInformation createFromParcel(Parcel in) {
            return new TopicInformation(in);
        }

        public TopicInformation[] newArray(int size) {
            return new TopicInformation[size];
        }
    };

    private TopicInformation(Parcel in) {
        mTopic = in.readString();
        mIcon = in.readInt();
        mExpectations = in.createStringArray();
        mLevelPageCounts = in.createIntArray();
        mLevel1Animations = in.createIntArray();
        mLevel2Animations = in.createIntArray();
        mLevel3Animations = in.createIntArray();
        mLevel1Texts = in.createStringArray();
        mLevel2Texts = in.createStringArray();
        mLevel3Texts = in.createStringArray();
    }


    public String getTopic(){
        return mTopic;
    }

    public String[] getExpectations(){
        return mExpectations;
    }

    public int getPageCount(int level){
        return mLevelPageCounts[level-1];
    }

    public int getIcon(){
        return mIcon;
    }

    public int[] getAnimations(int level){
        if (level == 1){
            return mLevel1Animations;
        } else if (level == 2){
            return mLevel2Animations;
        } else if (level == 3) {
            return mLevel3Animations;
        } else {
            return null;
        }
    }

    public String[] getTexts(int level){
        if (level == 1){
            return mLevel1Texts;
        } else if (level == 2){
            return mLevel2Texts;
        } else if (level == 3) {
            return mLevel3Texts;
        } else {
            return null;
        }
    }
}
