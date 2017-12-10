package halmob.healthhub;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.widget.Button;

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
 * Created by RIDVAN SIRMA on 12/11/2017.
 */
public class StepCounterActivityTest {
    @Rule
    public ActivityTestRule<StepCounterActivity> mActivityTestRule = new ActivityTestRule<>(StepCounterActivity.class);
    private StepCounterActivity mActivity=null;
    @Before
    public void setUp() throws Exception {
        mActivity=mActivityTestRule.getActivity();
    }
    @Test
    public void testStartButton(){
        assertNotNull(mActivity.findViewById(R.id.start_button));
    }
    @Test
    public void testimageView2ImageView(){
        assertNotNull(mActivity.findViewById(R.id.imageView2));
    }
    @Test
    public void testCountTextView(){
        assertNotNull(mActivity.findViewById(R.id.count));
    }

    @After
    public void tearDown() throws Exception {
        mActivity=null;
    }

}