package halmob.healthhub;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Created by RIDVAN SIRMA on 12/10/2017.
 */
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    private MainActivity mActivity=null;
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(StepCounterActivity.class.getName(),null,false);
    @Before
    public void setUp() throws Exception {
        mActivity=mActivityTestRule.getActivity();
    }

    @Test
    public void testLaunchOfStepCounterActivityOnButtonClick(){
        assertNotNull(mActivity.findViewById(R.id.step_counter));
        onView(withId(R.id.step_counter)).perform(click());
        Activity stepCounterActivity = getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
        assertNotNull(stepCounterActivity);
        stepCounterActivity.finish();
    }

    @After
    public void tearDown() throws Exception {
        mActivity=null;
    }

}