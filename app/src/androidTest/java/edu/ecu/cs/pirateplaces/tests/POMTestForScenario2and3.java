package edu.ecu.cs.pirateplaces.tests;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.ecu.cs.pirateplaces.PiratePlacesListActivity;

import edu.ecu.cs.pirateplaces.pageobjects.HomePageScenario2and3;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class POMTestForScenario2and3 {

   @Rule
   public ActivityTestRule<PiratePlacesListActivity> mActivityTestRule = new ActivityTestRule<>(PiratePlacesListActivity.class);


   @Test
   public void scenario2Test(){
      InstrumentationRegistry.getInstrumentation().getTargetContext();
      // Page Object Steps
      //Method for Scenario 2
      HomePageScenario2and3.clickListItem(HomePageScenario2and3.getDetailScreenByID(), "Scenario 2 and 3 Tested");


   }

}




