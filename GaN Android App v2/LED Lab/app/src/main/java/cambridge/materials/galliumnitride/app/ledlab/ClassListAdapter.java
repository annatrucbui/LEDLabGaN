package cambridge.materials.galliumnitride.app.ledlab;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

//Used to create list of classes in classroom

class ClassListAdapter extends BaseAdapter {

    Context context;
    private ArrayList<TopicInformation> mTopics;
    private float lastTouchedX;
    private float lastTouchedY;
    private boolean longPress = false;
    private static PopupWindow popup;

    private static LayoutInflater inflater = null;

    public ClassListAdapter(Context context, ArrayList<TopicInformation> topics) {
        this.context = context;
        this.mTopics = topics;
        inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        int i = mTopics.size();
        return i;
    }

    @Override
    public Object getItem(int position){return mTopics.get(position);}

    @Override
    public long getItemId(int position) {return position;}

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.list_item_class, null);

        //Get views to use later
        final TextView topicNameText = vi.findViewById(R.id.TopicTextView);
        ImageView topicIcon = vi.findViewById(R.id.TopicIconView);
        Button b1 = vi.findViewById(R.id.Stage1Button);
        Button b2 = vi.findViewById(R.id.Stage2Button);
        Button b3 = vi.findViewById(R.id.Stage3Button);

        //Set icon and text
        topicNameText.setText(mTopics.get(position).getTopic());
        topicIcon.setBackgroundResource(mTopics.get(position).getIcon());

        //Set colour of each button text
        SharedPreferences studentClasses = parent.getContext().getSharedPreferences("Classes", Context.MODE_PRIVATE);
        if (studentClasses.getBoolean(mTopics.get(position).getTopic() +".1", false)){
            b1.setTextColor(ContextCompat.getColor(parent.getContext(), R.color.royalBlue));
        } else {
            b1.setTextColor(ContextCompat.getColor(parent.getContext(), R.color.black));
        }
        if (studentClasses.getBoolean(mTopics.get(position).getTopic() +".2", false)){
            b2.setTextColor(ContextCompat.getColor(parent.getContext(), R.color.royalBlue));
        } else {
            b2.setTextColor(ContextCompat.getColor(parent.getContext(), R.color.black));
        }
        if (studentClasses.getBoolean(mTopics.get(position).getTopic() +".3", false)){
            b3.setTextColor(ContextCompat.getColor(parent.getContext(), R.color.royalBlue));
        } else {
            b3.setTextColor(ContextCompat.getColor(parent.getContext(), R.color.black));
        }

        //Set listener for first button on each topic
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loadClassActivity = new Intent(context, ClassPage.class);
                loadClassActivity.putExtra("Topic", mTopics.get(position));
                loadClassActivity.putExtra("Level", 1);
                context.startActivity(loadClassActivity);
                SharedPreferences studentClasses = view.getContext().getSharedPreferences("Classes", Context.MODE_PRIVATE);
                if (!studentClasses.getBoolean(mTopics.get(position).getTopic() + ".1", false)){
                    SharedPreferences.Editor editor = studentClasses.edit();
                    editor.putBoolean(mTopics.get(position).getTopic() + ".1", true);
                    editor.apply();
                    ((Button)view.findViewById(R.id.Stage1Button)).setTextColor(ContextCompat.getColor(view.getContext(), R.color.royalBlue));
                }
            }
        });

        //Set listener for second button on each topic
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loadClassActivity = new Intent(context, ClassPage.class);
                loadClassActivity.putExtra("Topic", mTopics.get(position));
                loadClassActivity.putExtra("Level", 2);
                context.startActivity(loadClassActivity);
                SharedPreferences studentClasses = view.getContext().getSharedPreferences("Classes", Context.MODE_PRIVATE);
                if (!studentClasses.getBoolean(mTopics.get(position).getTopic() + ".2", false)){
                    SharedPreferences.Editor editor = studentClasses.edit();
                    editor.putBoolean(mTopics.get(position).getTopic() + ".2", true);
                    editor.apply();
                    ((Button)view.findViewById(R.id.Stage2Button)).setTextColor(ContextCompat.getColor(view.getContext(), R.color.royalBlue));
                }

            }
        });

        //Set listener for third button on each topic
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loadClassActivity = new Intent(context, ClassPage.class);
                loadClassActivity.putExtra("Topic", mTopics.get(position));
                loadClassActivity.putExtra("Level", 3);
                context.startActivity(loadClassActivity);
                SharedPreferences studentClasses = view.getContext().getSharedPreferences("Classes", Context.MODE_PRIVATE);
                if (!studentClasses.getBoolean(mTopics.get(position).getTopic() + ".3", false)){
                    SharedPreferences.Editor editor = studentClasses.edit();
                    editor.putBoolean(mTopics.get(position).getTopic() + ".3", true);
                    editor.apply();
                    ((Button)view.findViewById(R.id.Stage3Button)).setTextColor(ContextCompat.getColor(view.getContext(), R.color.royalBlue));
                }
            }
        });

        //Set long click listener for text view to initiate expectations popup
        topicNameText.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (!longPress){
                    longPress = true;
                    popup = initiatePopupWindow(view, position);

                    return true;
                } else {
                    return false;
                }
            }
        });

        //Record the position of each touch and dismiss popup if it is the end of a long press
        topicNameText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                lastTouchedX = event.getX() + view.getX() + ((View) view.getParent()).getX() + ((View) view.getParent().getParent()).getX() + ((View) view.getParent().getParent().getParent()).getX() + ((View) view.getParent().getParent().getParent().getParent()).getX() + ((View) view.getParent().getParent().getParent().getParent().getParent()).getX();
                lastTouchedY = event.getY() + ((View) view.getParent().getParent()).getY() + ((View) view.getParent().getParent().getParent()).getY() + ((View) view.getParent().getParent().getParent().getParent()).getY()+65;

                if (longPress && event.getAction()==MotionEvent.ACTION_UP){
                    popup.dismiss();
                    longPress=false;
                } else if (longPress && event.getAction()==MotionEvent.ACTION_CANCEL){
                    popup.dismiss();
                    longPress=false;
                }
                return false;
            }
        });

        //Add 'currently unavailable' foreground to unused topics
        final ImageView iv = vi.findViewById(R.id.ForegroundImage);
        iv.getViewTreeObserver().addOnGlobalLayoutListener( new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                removeOnGlobalLayoutListener(iv, this);
                if (position > 4){
                    iv.setVisibility(View.VISIBLE);
                    iv.setBackgroundResource(R.drawable.ic_class_none);
                    ViewGroup.LayoutParams lp = iv.getLayoutParams();
                    lp.height = topicNameText.getHeight();
                    iv.setLayoutParams(lp);
                } else {
                    iv.setVisibility(View.GONE);
                }
            }
        });
        return vi;
    }

    //Initiates popup that says what the expectations are fo each topic
    public PopupWindow initiatePopupWindow(final View view, int position) {
        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        final View popupView = inflater.inflate(R.layout.popup_class_expectations, null);
        TextView exp = popupView.findViewById(R.id.topicExpectations);
        exp.setText("");
        String[] topicExp = mTopics.get(position).getExpectations();
        if (topicExp.length==0){
            TextView des = popupView.findViewById(R.id.popupDescription);
            des.setText(R.string.NoExpectationsDescription);
            exp.setVisibility(View.GONE);
        }
        for (String e : topicExp){
            exp.append("\n -  " + e );
        }

        popupView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                popupView.removeOnLayoutChangeListener(this);
                int x = (int) lastTouchedX+40;
                int y = (int) lastTouchedY-popupView.getHeight()-40;
                popup.dismiss();
                popup.showAtLocation(view, Gravity.NO_GRAVITY, x, y);
            }
        });

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, false);
        popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, 0, 0);
        return popupWindow;
    }

    //Removes global layout listener depending on api
    @TargetApi(16)
    public static void removeOnGlobalLayoutListener(View v, ViewTreeObserver.OnGlobalLayoutListener listener){
        try {
            v.getViewTreeObserver().removeOnGlobalLayoutListener(listener);
        } catch (NoSuchMethodError x) {
            v.getViewTreeObserver().removeGlobalOnLayoutListener(listener);
        }
    }
}