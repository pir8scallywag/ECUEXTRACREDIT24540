package edu.ecu.cs.pirateplaces;

import android.view.*;

import org.hamcrest.*;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

public class ScenarioBaseTest1 {
   public static void clickListItem(Integer resourceId,  String text){
        onView(
                allOf( withId(R.id.pirate_places_recycler_view),childAtPosition(
                        withId(R.id.fragment_container),
                        0
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


            return parent instanceof  ViewGroup &&  parentMatcher.matches(parent)
                    && view.equals(((ViewGroup) parent).getChildAt(position));
         }

         @Override
         public void describeTo(Description description) {
              description.appendText("Details On Detail Page Comes With ID");
              parentMatcher.describeTo(description);
         }
      };
   }
}
