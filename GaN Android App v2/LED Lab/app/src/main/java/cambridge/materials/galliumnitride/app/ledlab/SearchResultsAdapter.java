package cambridge.materials.galliumnitride.app.ledlab;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

//Used to create a list of search results

class SearchResultsAdapter extends BaseAdapter {

    Context context;
    private ArrayList<SearchElement> mSearchResults;
    private LayoutInflater inflater = null;
    private float lastTouchedX;
    private float lastTouchedY;
    private boolean longPress = false;
    private PopupWindow popup;

    public SearchResultsAdapter(Context context, ArrayList<SearchElement> searchResults) {
        this.context = context;
        this.mSearchResults = searchResults;
        inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mSearchResults.size();
    }

    @Override
    public Object getItem(int position){return mSearchResults.get(position);}

    @Override
    public long getItemId(int position) {return position;}

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View vi = convertView;

        final SearchElement searchElement = mSearchResults.get(position);
        switch (searchElement.getType()) {
            case ("PLACEHOLDER"):
                //Inflate view for placeholders
                vi = inflater.inflate(R.layout.list_item_search_separator, null);
                ((TextView) vi.findViewById(R.id.SeparatorText)).setText(searchElement.getText());
                break;
            case ("PAGE"):
                //Inflate view for pages
                vi = inflater.inflate(R.layout.list_item_page, null);

                //Set icon, text and text colour
                vi.findViewById(R.id.PageIcon).setBackgroundResource(searchElement.getIcon());
                ((TextView) vi.findViewById(R.id.PageName)).setText(searchElement.getText());
                ((TextView) vi.findViewById(R.id.PageName)).setTextColor(ContextCompat.getColor(context, searchElement.getColour()));

                //Set on click listener to load class
                final Activity a = searchElement.getActivity();
                vi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent loadPageActivity = new Intent(parent.getContext(), a.getClass());
                        parent.getContext().startActivity(loadPageActivity);
                    }
                });
                break;
            case ("CLASS"):
                //Inflate view for class
                vi = inflater.inflate(R.layout.list_item_class, null);

                //Set text, icon and background
                final TextView topicNameText = vi.findViewById(R.id.TopicTextView);
                topicNameText.setText(searchElement.getTopicInformation().getTopic());
                vi.findViewById(R.id.mainContentsItem).setBackgroundResource(R.color.paleBlue);
                ImageView topicIcon = vi.findViewById(R.id.TopicIconView);
                topicIcon.setBackgroundResource(searchElement.getTopicInformation().getIcon());

                //Find buttons
                Button b1 = vi.findViewById(R.id.Stage1Button);
                Button b2 = vi.findViewById(R.id.Stage2Button);
                Button b3 = vi.findViewById(R.id.Stage3Button);

                //Set colour of button text depending on whether class has been completed
                SharedPreferences studentClasses = context.getSharedPreferences("Classes", Context.MODE_PRIVATE);
                if (studentClasses.getBoolean(searchElement.getTopicInformation().getTopic() + ".1", false)) {
                    b1.setTextColor(ContextCompat.getColor(context, R.color.royalBlue));
                } else {
                    b1.setTextColor(ContextCompat.getColor(context, R.color.black));
                }
                if (studentClasses.getBoolean(searchElement.getTopicInformation().getTopic() + ".2", false)) {
                    b2.setTextColor(ContextCompat.getColor(context, R.color.royalBlue));
                } else {
                    b2.setTextColor(ContextCompat.getColor(context, R.color.black));
                }
                if (studentClasses.getBoolean(searchElement.getTopicInformation().getTopic() + ".3", false)) {
                    b3.setTextColor(ContextCompat.getColor(context, R.color.royalBlue));
                } else {
                    b3.setTextColor(ContextCompat.getColor(context, R.color.black));
                }

                //Set on click listener for first button to open class when pressed
                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Load appropiate class when pressed
                        Intent loadClassActivity = new Intent(context, ClassPage.class);
                        loadClassActivity.putExtra("Topic", searchElement.getTopicInformation());
                        loadClassActivity.putExtra("Level", 1);
                        context.startActivity(loadClassActivity);

                        //Ensure class status is changed to complete
                        SharedPreferences studentClasses = view.getContext().getSharedPreferences("Classes", Context.MODE_PRIVATE);
                        if (!studentClasses.getBoolean(searchElement.getTopicInformation().getTopic() + ".1", false)) {
                            SharedPreferences.Editor editor = studentClasses.edit();
                            editor.putBoolean(searchElement.getTopicInformation().getTopic() + ".1", true);
                            editor.apply();
                            ((Button) view.findViewById(R.id.Stage1Button)).setTextColor(ContextCompat.getColor(view.getContext(), R.color.royalBlue));
                        }
                    }
                });

                //Set on click listener for second button to open class when pressed
                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Load appropiate class when pressed
                        Intent loadClassActivity = new Intent(context, ClassPage.class);
                        loadClassActivity.putExtra("Topic", searchElement.getTopicInformation());
                        loadClassActivity.putExtra("Level", 2);
                        context.startActivity(loadClassActivity);

                        //Ensure class status is changed to complete
                        SharedPreferences studentClasses = view.getContext().getSharedPreferences("Classes", Context.MODE_PRIVATE);
                        if (!studentClasses.getBoolean(searchElement.getTopicInformation().getTopic() + ".2", false)) {
                            SharedPreferences.Editor editor = studentClasses.edit();
                            editor.putBoolean(searchElement.getTopicInformation().getTopic() + ".2", true);
                            editor.apply();
                            ((Button) view.findViewById(R.id.Stage2Button)).setTextColor(ContextCompat.getColor(view.getContext(), R.color.royalBlue));
                        }
                    }
                });

                //Set on click listener for third button to open class when pressed
                b3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Load appropiate class when pressed
                        Intent loadClassActivity = new Intent(context, ClassPage.class);
                        loadClassActivity.putExtra("Topic", searchElement.getTopicInformation());
                        loadClassActivity.putExtra("Level", 3);
                        context.startActivity(loadClassActivity);

                        //Ensure class status is changed to complete
                        SharedPreferences studentClasses = view.getContext().getSharedPreferences("Classes", Context.MODE_PRIVATE);
                        if (!studentClasses.getBoolean(searchElement.getTopicInformation().getTopic() + ".3", false)) {
                            SharedPreferences.Editor editor = studentClasses.edit();
                            editor.putBoolean(searchElement.getTopicInformation().getTopic() + ".3", true);
                            editor.apply();
                            ((Button) view.findViewById(R.id.Stage3Button)).setTextColor(ContextCompat.getColor(view.getContext(), R.color.royalBlue));
                        }
                    }
                });

                //Open popup when text is long clicked
                topicNameText.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        if (!longPress) {
                            longPress = true;
                            popup = initiatePopupWindow(view, position);
                            return true;
                        } else {
                            return false;
                        }
                    }
                });

                //Record position of every touch on text and dismiss long click if it appropriate
                topicNameText.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent event) {
                        lastTouchedX = event.getX() + view.getX() + ((View) view.getParent().getParent().getParent()).getX() + 30;
                        lastTouchedY = event.getY() + ((View) view.getParent().getParent()).getY() + ((View) view.getParent().getParent().getParent()).getY() - 150;

                        if (longPress && event.getAction() == MotionEvent.ACTION_UP) {
                            popup.dismiss();
                            longPress = false;
                        } else if (longPress && event.getAction() == MotionEvent.ACTION_CANCEL) {
                            popup.dismiss();
                            longPress = false;
                        }
                        return false;
                    }
                });
                break;

            case ("GAME"):
                //Inflate games view
                break;

            case ("EXPERIMENT"):
                //Inflate experiment view
                vi = inflater.inflate(R.layout.list_item_search_experiment, null);

                //Set experiment icon
                vi.findViewById(R.id.ExperimentIcon).setBackgroundResource(searchElement.getIcon());
                ((TextView) vi.findViewById(R.id.ExperimentName)).setText(searchElement.getText());

                //Set click listener to load experiment activity when clicked
                final Activity b = searchElement.getActivity();
                vi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent loadPageActivity = new Intent(parent.getContext(), b.getClass());
                        parent.getContext().startActivity(loadPageActivity);
                    }
                });
                break;

        }
        return vi;
    }


    public PopupWindow initiatePopupWindow(View view, int position) {
        //Inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_class_expectations, null);
        TextView exp = popupView.findViewById(R.id.topicExpectations);
        exp.setText("");
        final SearchElement searchElement = mSearchResults.get(position);
        String[] topicExp = searchElement.getTopicInformation().getExpectations();
        if (topicExp.length==0){
            TextView des = popupView.findViewById(R.id.popupDescription);
            des.setText(R.string.NoExpectationsDescription);
            exp.setVisibility(View.GONE);
        }
        for (String e : topicExp){
            exp.append("\n -  " + e );
        }

        //Create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, false);

        //Show the popup window
        float x = lastTouchedX;
        float y = lastTouchedY;
        popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, (int) x, (int) y);
        return popupWindow;
    }
}