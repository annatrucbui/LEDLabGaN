package cambridge.materials.galliumnitride.app.ledlab;


import android.content.Context;
import java.util.ArrayList;

//Used to initialise all the topics used for classes

public class TopicInformationInitialiser {
    private ArrayList<TopicInformation> mTopics = new ArrayList<>();

    public TopicInformationInitialiser(Context c){

        //Create list of topics in order of how the classes appear in the list
        String[] InsideLedExpectations = {};
        int[] InsideLedLevelPageCounts = {5, 5, 5};
        int[] InsideLed1Animations = {R.drawable.animation_inside_an_led_bulb_1_1, R.drawable.animation_inside_an_led_bulb_1_2, R.drawable.animation_inside_an_led_bulb_1_3, R.drawable.animation_inside_an_led_bulb_1_4, R.drawable.animation_inside_an_led_bulb_1_5};
        int[] InsideLed2Animations = {R.drawable.animation_inside_an_led_bulb_2_1, R.drawable.animation_inside_an_led_bulb_2_2, R.drawable.animation_inside_an_led_bulb_2_3, R.drawable.animation_inside_an_led_bulb_2_4, R.drawable.animation_inside_an_led_bulb_2_5};
        int[] InsideLed3Animations = {R.drawable.animation_inside_an_led_bulb_3_1, R.drawable.animation_inside_an_led_bulb_3_2, R.drawable.animation_inside_an_led_bulb_3_3, R.drawable.animation_inside_an_led_bulb_3_4, R.drawable.animation_inside_an_led_bulb_3_5};
        String[] InsideLed1Texts = {c.getResources().getString(R.string.ClassText_InsideLed1_Page1), c.getResources().getString(R.string.ClassText_InsideLed1_Page2), c.getResources().getString(R.string.ClassText_InsideLed1_Page3), c.getResources().getString(R.string.ClassText_InsideLed1_Page4), c.getResources().getString(R.string.ClassText_InsideLed1_Page5)};
        String[] InsideLed2Texts = {c.getResources().getString(R.string.ClassText_InsideLed2_Page1), c.getResources().getString(R.string.ClassText_InsideLed2_Page2), c.getResources().getString(R.string.ClassText_InsideLed2_Page3), c.getResources().getString(R.string.ClassText_InsideLed2_Page4), c.getResources().getString(R.string.ClassText_InsideLed2_Page5)};
        String[] InsideLed3Texts = {c.getResources().getString(R.string.ClassText_InsideLed3_Page1), c.getResources().getString(R.string.ClassText_InsideLed3_Page2), c.getResources().getString(R.string.ClassText_InsideLed3_Page3), c.getResources().getString(R.string.ClassText_InsideLed3_Page4), c.getResources().getString(R.string.ClassText_InsideLed3_Page5)};
        TopicInformation InsideLed = new TopicInformation("Inside An LED Bulb", R.drawable.ic_class_inside_an_led_icon, InsideLedExpectations, InsideLedLevelPageCounts, InsideLed1Animations, InsideLed2Animations, InsideLed3Animations, InsideLed1Texts, InsideLed2Texts, InsideLed3Texts);
        mTopics.add(InsideLed);

        String[] LedApplicationsExpectations = {};
        int[] LedApplicationsLevelPageCounts = {6, 4, 5};
        int[] LedApplications1Animations = {R.drawable.animation_led_applications_1_1, R.drawable.animation_led_applications_1_2, R.drawable.animation_led_applications_1_3, R.drawable.animation_led_applications_1_4, R.drawable.animation_led_applications_1_5, R.drawable.animation_led_applications_1_6};
        int[] LedApplications2Animations = {R.drawable.animation_led_applications_2_1, R.drawable.animation_led_applications_2_2, R.drawable.animation_led_applications_2_3, R.drawable.animation_led_applications_2_4};
        int[] LedApplications3Animations = {R.drawable.animation_led_applications_3_1, R.drawable.animation_led_applications_3_2, R.drawable.animation_led_applications_3_3, R.drawable.animation_led_applications_3_4, R.drawable.animation_led_applications_3_5};
        String[] LedApplications1Texts = {c.getResources().getString(R.string.ClassText_LedApplications1_Page1), c.getResources().getString(R.string.ClassText_LedApplications1_Page2), c.getResources().getString(R.string.ClassText_LedApplications1_Page3), c.getResources().getString(R.string.ClassText_LedApplications1_Page4), c.getResources().getString(R.string.ClassText_LedApplications1_Page5), c.getResources().getString(R.string.ClassText_LedApplications1_Page6)};
        String[] LedApplications2Texts = {c.getResources().getString(R.string.ClassText_LedApplications2_Page1), c.getResources().getString(R.string.ClassText_LedApplications2_Page2), c.getResources().getString(R.string.ClassText_LedApplications2_Page3), c.getResources().getString(R.string.ClassText_LedApplications2_Page4)};
        String[] LedApplications3Texts = {c.getResources().getString(R.string.ClassText_LedApplications3_Page1), c.getResources().getString(R.string.ClassText_LedApplications3_Page2), c.getResources().getString(R.string.ClassText_LedApplications3_Page3), c.getResources().getString(R.string.ClassText_LedApplications3_Page4), c.getResources().getString(R.string.ClassText_LedApplications3_Page5)};
        TopicInformation LedApplications = new TopicInformation("Applications for LEDs", R.drawable.ic_class_led_applications_icon, LedApplicationsExpectations, LedApplicationsLevelPageCounts, LedApplications1Animations, LedApplications2Animations, LedApplications3Animations, LedApplications1Texts, LedApplications2Texts, LedApplications3Texts);
        mTopics.add(LedApplications);

        String[] ConductionExpectations = {};
        int[] ConductionLevelPageCounts = {6, 8, 6};
        int[] Conduction1Animations = {R.drawable.animation_conduction_1_1, R.drawable.animation_conduction_1_2, R.drawable.animation_conduction_1_3, R.drawable.animation_conduction_1_4, R.drawable.animation_conduction_1_5, R.drawable.animation_conduction_1_6};
        int[] Conduction2Animations = {R.drawable.animation_conduction_2_1, R.drawable.animation_conduction_2_2, R.drawable.animation_conduction_2_3, R.drawable.animation_conduction_2_4, R.drawable.animation_conduction_2_5, R.drawable.animation_conduction_2_5, R.drawable.animation_conduction_2_6, R.drawable.animation_conduction_2_7};
        int[] Conduction3Animations = {R.drawable.animation_conduction_3_1, R.drawable.animation_conduction_3_2, R.drawable.animation_conduction_3_3, R.drawable.animation_conduction_3_4, R.drawable.animation_conduction_3_5, R.drawable.animation_conduction_3_6};
        String[] Conduction1Texts = {c.getResources().getString(R.string.ClassText_Conduction1_Page1), c.getResources().getString(R.string.ClassText_Conduction1_Page2), c.getResources().getString(R.string.ClassText_Conduction1_Page3), c.getResources().getString(R.string.ClassText_Conduction1_Page4), c.getResources().getString(R.string.ClassText_Conduction1_Page5), c.getResources().getString(R.string.ClassText_Conduction1_Page6)};
        String[] Conduction2Texts = {c.getResources().getString(R.string.ClassText_Conduction2_Page1), c.getResources().getString(R.string.ClassText_Conduction2_Page2), c.getResources().getString(R.string.ClassText_Conduction2_Page3), c.getResources().getString(R.string.ClassText_Conduction2_Page3), c.getResources().getString(R.string.ClassText_Conduction2_Page3), c.getResources().getString(R.string.ClassText_Conduction2_Page4), c.getResources().getString(R.string.ClassText_Conduction2_Page4), c.getResources().getString(R.string.ClassText_Conduction2_Page4)};
        String[] Conduction3Texts = {c.getResources().getString(R.string.ClassText_Conduction3_Page1), c.getResources().getString(R.string.ClassText_Conduction3_Page2), c.getResources().getString(R.string.ClassText_Conduction3_Page3), c.getResources().getString(R.string.ClassText_Conduction3_Page4), c.getResources().getString(R.string.ClassText_Conduction3_Page5), c.getResources().getString(R.string.ClassText_Conduction3_Page6)};
        TopicInformation Conduction = new TopicInformation("Conduction", R.drawable.ic_class_conduction, ConductionExpectations, ConductionLevelPageCounts, Conduction1Animations, Conduction2Animations, Conduction3Animations, Conduction1Texts, Conduction2Texts, Conduction3Texts);
        mTopics.add(Conduction);



        String[] DopingExpectations = {"Conduction"};
        int[] DopingLevelPageCounts = {6, 6, 7};
        int[] Doping1Animations = {R.drawable.animation_doping_1_1, R.drawable.animation_doping_1_2, R.drawable.animation_doping_1_3, R.drawable.animation_doping_1_4, R.drawable.animation_doping_1_5, R.drawable.animation_doping_1_6};
        int[] Doping2Animations = {R.drawable.animation_doping_2_1, R.drawable.animation_doping_2_2, R.drawable.animation_doping_2_3, R.drawable.animation_doping_2_4, R.drawable.animation_doping_2_5, R.drawable.animation_doping_2_6};
        int[] Doping3Animations = {R.drawable.animation_doping_3_1, R.drawable.animation_doping_3_2, R.drawable.animation_doping_3_3, R.drawable.animation_doping_3_4, R.drawable.animation_doping_3_5, R.drawable.animation_doping_3_6, R.drawable.animation_doping_3_7};
        String[] Doping1Texts = {c.getResources().getString(R.string.ClassText_Doping1_Page1), c.getResources().getString(R.string.ClassText_Doping1_Page2), c.getResources().getString(R.string.ClassText_Doping1_Page3), c.getResources().getString(R.string.ClassText_Doping1_Page4), c.getResources().getString(R.string.ClassText_Doping1_Page5), c.getResources().getString(R.string.ClassText_Doping1_Page6)};
        String[] Doping2Texts = {c.getResources().getString(R.string.ClassText_Doping2_Page1), c.getResources().getString(R.string.ClassText_Doping2_Page2), c.getResources().getString(R.string.ClassText_Doping2_Page3), c.getResources().getString(R.string.ClassText_Doping2_Page4), c.getResources().getString(R.string.ClassText_Doping2_Page5), c.getResources().getString(R.string.ClassText_Doping2_Page6)};
        String[] Doping3Texts = {c.getResources().getString(R.string.ClassText_Doping3_Page1), c.getResources().getString(R.string.ClassText_Doping3_Page2), c.getResources().getString(R.string.ClassText_Doping3_Page3), c.getResources().getString(R.string.ClassText_Doping3_Page4), c.getResources().getString(R.string.ClassText_Doping3_Page5), c.getResources().getString(R.string.ClassText_Doping3_Page6), c.getResources().getString(R.string.ClassText_Doping3_Page7)};
        TopicInformation Doping = new TopicInformation("Doping", R.drawable.ic_class_doping, DopingExpectations, DopingLevelPageCounts, Doping1Animations, Doping2Animations, Doping3Animations, Doping1Texts, Doping2Texts, Doping3Texts);
        mTopics.add(Doping);



        String[] pnJunctionExpectations = {"Conduction", "Doping"};
        int[] pnJunctionLevelPageCounts = {5, 4, 8};
        int[] pnJunction1Animations = {R.drawable.animation_pnjunction_1_1, R.drawable.animation_pnjunction_1_2, R.drawable.animation_pnjunction_1_3, R.drawable.animation_pnjunction_1_4, R.drawable.animation_pnjunction_1_5};
        int[] pnJunction2Animations = {R.drawable.animation_pnjunction_2_1, R.drawable.animation_pnjunction_2_2, R.drawable.animation_pnjunction_2_3, R.drawable.animation_pnjunction_2_4};
        int[] pnJunction3Animations = {R.drawable.animation_pnjunction_3_1, R.drawable.animation_pnjunction_3_2, R.drawable.animation_pnjunction_3_3, R.drawable.animation_pnjunction_3_4, R.drawable.animation_pnjunction_3_5, R.drawable.animation_pnjunction_3_6, R.drawable.animation_pnjunction_3_7,R.drawable.animation_pnjunction_3_8 };
        String[] pnJunction1Texts = {c.getResources().getString(R.string.ClassText_pnjunction1_Page1), c.getResources().getString(R.string.ClassText_pnjunction1_Page2), c.getResources().getString(R.string.ClassText_pnjunction1_Page3), c.getResources().getString(R.string.ClassText_pnjunction1_Page4), c.getResources().getString(R.string.ClassText_pnjunction1_Page5)};
        String[] pnJunction2Texts = {c.getResources().getString(R.string.ClassText_pnjunction2_Page1), c.getResources().getString(R.string.ClassText_pnjunction2_Page2), c.getResources().getString(R.string.ClassText_pnjunction2_Page3), c.getResources().getString(R.string.ClassText_pnjunction2_Page4)};
        String[] pnJunction3Texts = {c.getResources().getString(R.string.ClassText_pnjunction3_Page1), c.getResources().getString(R.string.ClassText_pnjunction3_Page2), c.getResources().getString(R.string.ClassText_pnjunction3_Page3), c.getResources().getString(R.string.ClassText_pnjunction3_Page4), c.getResources().getString(R.string.ClassText_pnjunction3_Page5), c.getResources().getString(R.string.ClassText_pnjunction3_Page6), c.getResources().getString(R.string.ClassText_pnjunction3_Page7), c.getResources().getString(R.string.ClassText_pnjunction3_Page8) };
        TopicInformation pnJunction = new TopicInformation("p-n Junction", R.drawable.ic_class_pn_junction, pnJunctionExpectations, pnJunctionLevelPageCounts, pnJunction1Animations, pnJunction2Animations, pnJunction3Animations, pnJunction1Texts, pnJunction2Texts, pnJunction3Texts);
        mTopics.add(pnJunction);

        String[] quantumWellsExpectations = {"Doping", "p-n Junction"};
        int[] quantumWellsLevelPageCounts = {6, 5, 4};
        int[] quantumWells1Animations = {R.drawable.animation_quantumwells_1_1, R.drawable.animation_quantumwells_1_2, R.drawable.animation_quantumwells_1_3, R.drawable.animation_quantumwells_1_4, R.drawable.animation_quantumwells_1_5, R.drawable.animation_quantumwells_1_6};
        int[] quantumWells2Animations = {R.drawable.animation_quantumwells_2_1, R.drawable.animation_quantumwells_2_2, R.drawable.animation_quantumwells_2_3, R.drawable.animation_quantumwells_2_4, R.drawable.animation_quantumwells_2_5};
        int[] quantumWells3Animations = {R.drawable.animation_quantumwells_3_1, R.drawable.animation_quantumwells_3_2, R.drawable.animation_quantumwells_3_3, R.drawable.animation_quantumwells_3_4};
        String[] quantumWells1Texts = {c.getResources().getString(R.string.ClassText_quantumwells1_Page1), c.getResources().getString(R.string.ClassText_quantumwells1_Page2), c.getResources().getString(R.string.ClassText_quantumwells1_Page3), c.getResources().getString(R.string.ClassText_quantumwells1_Page4), c.getResources().getString(R.string.ClassText_quantumwells1_Page5), c.getResources().getString(R.string.ClassText_quantumwells1_Page5)};
        String[] quantumWells2Texts = {c.getResources().getString(R.string.ClassText_quantumwells2_Page1), c.getResources().getString(R.string.ClassText_quantumwells2_Page2), c.getResources().getString(R.string.ClassText_quantumwells2_Page3), c.getResources().getString(R.string.ClassText_quantumwells2_Page4), c.getResources().getString(R.string.ClassText_quantumwells2_Page4)};
        String[] quantumWells3Texts = {c.getResources().getString(R.string.ClassText_quantumwells3_Page1), c.getResources().getString(R.string.ClassText_quantumwells3_Page2), c.getResources().getString(R.string.ClassText_quantumwells3_Page3), c.getResources().getString(R.string.ClassText_quantumwells3_Page4)  };
        TopicInformation quantumWells = new TopicInformation("Quantum Wells", R.drawable.ic_class_quantum_wells, quantumWellsExpectations, quantumWellsLevelPageCounts, quantumWells1Animations, quantumWells2Animations, quantumWells3Animations, quantumWells1Texts, quantumWells2Texts, quantumWells3Texts);
        mTopics.add(quantumWells);








    }

    //Getter
    public ArrayList<TopicInformation> getTopics(){
        return mTopics;
    }
}
