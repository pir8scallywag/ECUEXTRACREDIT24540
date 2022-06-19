package edu.ecu.cs.pirateplaces;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;

import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;


import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static org.hamcrest.Matchers.allOf;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class ScenarioBaseTest2and3 {
   public static void clickListItem(Integer resourceId,  String text){
       onView(
               allOf( withId(R.id.pirate_places_recycler_view),childAtPosition(
                       withId(R.id.fragment_container),
                       0
                       ),
                       isDisplayed())).perform(click());


       onView(
               allOf(
                       withId(R.id.compare_names),
                       childAtPosition(
                               childAtPosition(
                                       withId(R.id.fragment_container),
                                       0
                               ),
                               5
                       ),
                       isDisplayed())).perform(click());
   }

   private static Matcher<View> childAtPosition(
        final  Matcher<View> parentMatcher, final int position
   ){
      return new TypeSafeMatcher<View>() {
         @Override
         protected boolean matchesSafely(View view) {
             ViewParent parent  = view.getParent();

             //Case for Scenario 2
             if((view instanceof TextView)){
                 String nameOnButton =  ((TextView) view).getText().toString();
                 String nameOnPosition = PirateBase.Companion.getPiratePlaceList().get(position-1).getName();

                 if(nameOnPosition.equals(nameOnButton)){
                     Log.e("TEST SCENARIO 2", "Names  Matches");
                 }else{
                     Log.e("TEST SCENARIO 2", "Names Does Not Matches");
                 }
             }

             //Case for Scenario 3
             if(view instanceof RecyclerView){

                 try {
                     runOnUiThread(new Runnable() {
                         public void run() {
                             Intents.init();
                             intended(hasComponent(PiratePlacesDetailActivity.class.getName()));
                         }
                     });
                 } catch (Throwable throwable) {
                     throwable.printStackTrace();
                 }


             }

            return parent instanceof  ViewGroup &&  parentMatcher.matches(parent)
                    && view.equals(((ViewGroup) parent).getChildAt(position));
         }

         @Override
         public void describeTo(Description description) {
              description.appendText("Compare Names On Details Screen On Click OF RecycleView");
              parentMatcher.describeTo(description);
         }
      };
   }



}
