package cambridge.materials.galliumnitride.app.ledlab;


import android.content.Context;

import java.util.ArrayList;

//Used to create list results that could be in a search results list if the search is contained in a tag from that search item

public class PossibleSearchesInitialiser {
    private ArrayList<SearchElement> mPossibleSearches = new ArrayList<>();

    public PossibleSearchesInitialiser(Context c){
        //Add search items in order of how they will appear in search results

        //--- Every search item must have a type which is either PLACEHOLDER, PAGE, CLASS, GAME or EXPERIMENT
        //--- Placeholders, pages and experiments also have a string which is the dispay text in the search result list for the search item
        //--- Pages, classes, games and experiments have an array of strings that are tags that find the search item if the exact search
        //    (not case sensitive, but space sensitive) is contained in any tag

        //Add pages in order with header
        SearchElement pageSeparator = new SearchElement("PLACEHOLDER", "Related Pages");
        mPossibleSearches.add(pageSeparator);

        String[] homePageTags = {"home page", "homepage"};
        SearchElement homePageSearch = new SearchElement("PAGE", homePageTags, "Home Page", R.drawable.ic_search_home, new HomePage(), R.color.blueCharcoal);
        mPossibleSearches.add(homePageSearch);

        String[] classroomTags = {"classroom page", "classroompage"};
        SearchElement classroomSearch = new SearchElement("PAGE", classroomTags, "Classroom", R.drawable.ic_search_classroom, new ClassroomPage(), R.color.blue);
        mPossibleSearches.add(classroomSearch);

        String[] gamesTags = {"games page", "gamespage"};
        SearchElement gamesSearch = new SearchElement("PAGE", gamesTags, "Games Page", R.drawable.ic_search_games, new QuizPage(), R.color.darkGreen);
        mPossibleSearches.add(gamesSearch);

        String[] searchTags = {"search page", "searchpage"};
        SearchElement searchSearch = new SearchElement("PAGE", searchTags, "Search Page", R.drawable.ic_search_search, new SearchPage(), R.color.orange);
        mPossibleSearches.add(searchSearch);

        String[] laboratoryTags = {"laboratory page", "laboratorypage"};
        SearchElement laboratorySearch = new SearchElement("PAGE", laboratoryTags, "Laboratory", R.drawable.ic_search_laboratory, new LaboratoryPage(), R.color.neonPink);
        mPossibleSearches.add(laboratorySearch);

        String[] contactUsTags = {"contact us page", "contactus page", "contact uspage", "contactuspage"};
        SearchElement contactUsSearch = new SearchElement("PAGE", contactUsTags, "Contact Us Page", R.drawable.ic_search_contact_us, new ContactUsPage(), R.color.neonPurple);
        mPossibleSearches.add(contactUsSearch);

        //Add classes in order with header
        SearchElement classSeparator = new SearchElement("PLACEHOLDER", "Related Classes");
        mPossibleSearches.add(classSeparator);

        TopicInformationInitialiser tii = new TopicInformationInitialiser(c);
        ArrayList<TopicInformation> topics = tii.getTopics();

        for (TopicInformation t : topics){
            String[] topicTags = new String[1 + t.getPageCount(1) + t.getPageCount(2) + t.getPageCount(3)];
            topicTags[0] = t.getTopic();
            for (int i = 0; i<t.getPageCount(1); i++){
                topicTags[i+1] = t.getTexts(1)[i];
            }
            for (int j = 0 ; j < t.getPageCount(2) ; j++){
                topicTags[j + t.getPageCount(1) + 1] = t.getTexts(2)[j];
            }
            for (int k = 0; k < t.getPageCount(3); k++){
                topicTags[k+ t.getPageCount(2) + t.getPageCount(1) + 1] = t.getTexts(3)[k];
            }
            SearchElement s = new SearchElement("CLASS", topicTags, t);
            mPossibleSearches.add(s);
        }

        //Add games in order with header
        SearchElement gameSeparator = new SearchElement("PLACEHOLDER", "Related Games");
        mPossibleSearches.add(gameSeparator);


        //Add experiments in order with header
        SearchElement experimentSeparator = new SearchElement("PLACEHOLDER", "Related Experiments");
        mPossibleSearches.add(experimentSeparator);

        String[] colourTuningExperimentTags = {"colour tuning experiment", "colourtuning experiment", "colour tuningexperiment", "colourtuningexperiment", "colour tuner", "colourtuner"};
        SearchElement colourTuningExperimentSearch = new SearchElement("EXPERIMENT", colourTuningExperimentTags, "Colour Tuning", R.drawable.ic_search_colour_tuning, new ExperimentColourTuning());
        mPossibleSearches.add(colourTuningExperimentSearch);

        String[] IVPlotterExperimentTags = {"i-v plotter experiment", "i-vplotter experiment", "i-v plotterexperiment", "i-vplotterexperiment", "iv plotter experiment", "ivplotter experiment", "iv plotterexperiment", "ivplotterexperiment", "i-v plotter", "i-vplotter", "iv plotter", "ivplotter"};
        SearchElement IVPlotterExperimentSearch = new SearchElement("EXPERIMENT", IVPlotterExperimentTags, "I-V Plotter", R.drawable.ic_search_iv_plotter, new ExperimentIVPlotter());
        mPossibleSearches.add(IVPlotterExperimentSearch);

        String[] LEDSwitchExperimentTags = {"led switch experiment", "ledswitch experiment", "led switchexperiment", "ledswitchexperiment", "led switch", "ledswitch"};
        SearchElement LEDSwitchExperimentSearch = new SearchElement("EXPERIMENT", LEDSwitchExperimentTags, "LED SWitch", R.drawable.ic_search_led_switch, new ExperimentLEDSwitch());
        mPossibleSearches.add(LEDSwitchExperimentSearch);

    }

    public ArrayList<SearchElement> getPossibleSearches(){
        return mPossibleSearches;
    }
}
