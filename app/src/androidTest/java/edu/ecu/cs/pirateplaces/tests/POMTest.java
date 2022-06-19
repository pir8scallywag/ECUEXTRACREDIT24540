package edu.ecu.cs.pirateplaces.tests;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import edu.ecu.cs.pirateplaces.PiratePlacesListActivity;
import edu.ecu.cs.pirateplaces.pageobjects.HomePageScenario1;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;



@LargeTest
@RunWith(AndroidJUnit4.class)
public class POMTest   {

   @Rule
   public ActivityTestRule<PiratePlacesListActivity> mActivityTestRule = new ActivityTestRule<>(PiratePlacesListActivity.class);


   @Test
   public void scenario1Test(){
      InstrumentationRegistry.getInstrumentation().getTargetContext();
      // Page Object Steps
      HomePageScenario1.clickListItem(HomePageScenario1.getDetailScreenByID(), "Detail Screen By ID");
   }

}




