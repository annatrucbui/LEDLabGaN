package cambridge.materials.galliumnitride.app.ledlab;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

//Used when search page is opened

public class SearchPage extends AppCompatActivity {
    private int[] mResultCount = new int[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_search);

        //Fonts, to be used later
        Typeface tajamuka = Typeface.createFromAsset(getAssets(),  "fonts/tajamuka_script.ttf");
        Typeface gruppo = Typeface.createFromAsset(getAssets(),  "fonts/gruppo.ttf");

        //Set title bar fonts
        ((TextView)findViewById(R.id.AppName)).setTypeface(tajamuka);
        ((TextView)findViewById(R.id.by)).setTypeface(tajamuka);
        ((TextView)findViewById(R.id.creator)).setTypeface(gruppo);
        ((TextView) findViewById(R.id.SearchTitle)).setTypeface(tajamuka);

        //Set click listeners for back and home buttons
        ImageButton BackButton = findViewById(R.id.backButton);
        BackButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(SearchPage.this, HomePage.class);
                startActivity(intent);
            }
        });

        ImageButton HomeButton = findViewById(R.id.homeButton);
        HomeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(SearchPage.this, HomePage.class);
                startActivity(intent);
            }
        });

        //Set click listener for go button
        TextView goButton = findViewById(R.id.GoButton);
        goButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                EditText searchBox = findViewById(R.id.SearchBox);
                String search = searchBox.getText().toString();
                ArrayList<SearchElement> searchResults = search(search);

                if (search.equals("")){

                    //no search
                    findViewById(R.id.SearchResultsList).setVisibility(View.GONE);
                    findViewById(R.id.ListSpine).setVisibility(View.GONE);
                    findViewById(R.id.SearchResultsText).setVisibility(View.GONE);
                    findViewById(R.id.NoResultsBox).setVisibility(View.VISIBLE);
                    ((TextView)findViewById(R.id.NoResultsBox)).setText(getResources().getString(R.string.NoSearchInput));
                } else if (searchResults.size()==0){

                    //no results
                    findViewById(R.id.SearchResultsList).setVisibility(View.GONE);
                    findViewById(R.id.ListSpine).setVisibility(View.GONE);
                    findViewById(R.id.SearchResultsText).setVisibility(View.GONE);
                    findViewById(R.id.NoResultsBox).setVisibility(View.VISIBLE);
                    ((TextView)findViewById(R.id.NoResultsBox)).setText(getResources().getString(R.string.NoSearchOutput));
                } else {

                    //valid search with results
                    findViewById(R.id.NoResultsBox).setVisibility(View.GONE);
                    findViewById(R.id.SearchResultsList).setVisibility(View.VISIBLE);
                    findViewById(R.id.ListSpine).setVisibility(View.VISIBLE);

                    TextView searchResultsText = findViewById(R.id.SearchResultsText);
                    searchResultsText.setVisibility(View.VISIBLE);

                    //Set text of first part of text view with results summarised
                    int resultCount = mResultCount[0] + mResultCount[1] + mResultCount[2] + mResultCount[3];
                    searchResultsText.setText(getResources().getString(R.string.SearchOutputStart));
                    searchResultsText.append("" + resultCount);
                    if (resultCount==1){
                        searchResultsText.append(getResources().getString(R.string.Result));
                    } else {
                        searchResultsText.append(getResources().getString(R.string.Results));
                    }
                    //finds how many types of results are non-zero. A type is either page, class, game or experiment
                    int typeCount = mResultCount.length;
                    for (int r : mResultCount) {
                        if (r == 0) {
                            typeCount--;
                        }
                    }

                    //Adds list of how many results of each type are returned by search to the text view
                    String[] pluralList = {getResources().getString(R.string.Pages), getResources().getString(R.string.Classes), getResources().getString(R.string.Games), getResources().getString(R.string.Experiments)};
                    String[] singularList = {getResources().getString(R.string.Page), getResources().getString(R.string.Class), getResources().getString(R.string.Game), getResources().getString(R.string.Experiment)};
                    int j = 0;
                    for (int k = typeCount; k>0; k--){
                        if (k == 1 && typeCount!=1){
                            searchResultsText.append(getResources().getString(R.string.And));
                        }
                        while (mResultCount[j]==0){
                            j++;
                        }

                        if (k == 1 && mResultCount[j] == 1){
                            searchResultsText.append("" + mResultCount[j] + singularList[j] + ")");
                        } else if (k == 1){
                            searchResultsText.append("" + mResultCount[j] + pluralList[j] + ")");
                        } else if (mResultCount[j] == 1){
                            searchResultsText.append("" + mResultCount[j] + singularList[j] + " ");
                        } else if (k==2){
                            searchResultsText.append("" + mResultCount[j] + pluralList[j] + " ");
                        } else {
                            searchResultsText.append("" + mResultCount[j] + pluralList[j] + ", ");
                        }
                        j++;
                    }


                    //Set the adapter of the list view
                    ListView lv = findViewById(R.id.SearchResultsList);
                    lv.setAdapter(new SearchResultsAdapter(SearchPage.this, searchResults));
                }


            }
        });

        //Only show "NoResultsText" initially
        findViewById(R.id.SearchResultsList).setVisibility(View.GONE);
        findViewById(R.id.ListSpine).setVisibility(View.GONE);
        findViewById(R.id.SearchResultsText).setVisibility(View.GONE);
    }

    //called when go button is clicked, and returns list containing all search elements from the possible searches where
    // the search is found within a tag of the search element
    public ArrayList<SearchElement> search(String search){
        ArrayList<SearchElement> searchResults = new ArrayList<>();
        PossibleSearchesInitialiser psi = new PossibleSearchesInitialiser(this);
        ArrayList<SearchElement> possibleSearches = psi.getPossibleSearches();

        //Initialise placeholder markers and results count for each type
        int pagePlaceholder = 0;
        int classPlaceholder = 0;
        int gamePlaceholder = 0;
        int experimentPlaceholder = 0;
        mResultCount[0] = 0;
        mResultCount[1] = 0;
        mResultCount[2] = 0;
        mResultCount[3] = 0;


        //Go though all possible searches and extract appropriate search elements based on whether the search
        //is contained within one of the search elements tags
        for (int i = 0; i<possibleSearches.size(); i++){
            SearchElement searchItem = possibleSearches.get(i);
            if (searchItem.getType().equals("PLACEHOLDER")){
                //Find where placeholders are in the results list
                switch (searchItem.getText()){
                    case ("Related Pages"):
                        pagePlaceholder = searchResults.size();
                        break;
                    case ("Related Classes"):
                        classPlaceholder = searchResults.size();
                        break;
                    case ("Related Games"):
                        gamePlaceholder = searchResults.size();
                        break;
                    case ("Related Experiments"):
                        experimentPlaceholder = searchResults.size();
                        break;
                }

                //Add all placeholders to results for now
                searchResults.add(searchItem);
            }
            else {
                //Go through all tags and add search element to results if the search is contained in any tag
                String[] tags = searchItem.getTags();
                for (String tag : tags) {
                    if (tag.toLowerCase().contains(search.toLowerCase())) {
                        searchResults.add(searchItem);

                        //count how many of each type of result is present
                        switch (searchItem.getType()){
                            case("PAGE"):
                                mResultCount[0]++;
                                break;
                            case("CLASS"):
                                mResultCount[1]++;
                                break;
                            case("GAME"):
                                mResultCount[2]++;
                                break;
                            case("EXPERIMENT"):
                                mResultCount[3]++;
                                break;
                        }
                        break;
                    }
                }
            }

        }

        // Remove irrelevant placeholders, must be in reverse order of how they are listed
        if (mResultCount[3] == 0) {
            searchResults.remove(experimentPlaceholder);
        }
        if (mResultCount[2] == 0){
            searchResults.remove(gamePlaceholder);
        }
        if (mResultCount[1] == 0){
            searchResults.remove(classPlaceholder);
        }
        if (mResultCount[0] == 0){
            searchResults.remove(pagePlaceholder);
        }
        return searchResults;
    }
}
