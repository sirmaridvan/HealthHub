package halmob.healthhub;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.filters.SmallTest;
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
 * Created by RIDVAN SIRMA on 12/11/2017.
 */
public class Sports_ActivityTest {
    @Rule
    public ActivityTestRule<Sports_Activity> mActivityTestRule = new ActivityTestRule<>(Sports_Activity.class);
    private Sports_Activity mActivity=null;
    @Before
    public void setUp() throws Exception {
        mActivity=mActivityTestRule.getActivity();
    }

    Instrumentation.ActivityMonitor monitorListSportRecordActivity = getInstrumentation().addMonitor(ListSportRecordActivity.class.getName(),null,false);

    @SmallTest
    public void testSportRecordButton(){
        assertNotNull(mActivity.findViewById(R.id.sportRecord_Button));
    }
    @SmallTest
    public void testListSportActivity(Activity listSportRecordActivity){
        assertNotNull(listSportRecordActivity);
    }
    @Test
    public void testLaunchOfListSportRecordActivityOnButtonClick(){
        testSportRecordButton();
        onView(withId(R.id.sportRecord_Button)).perform(click());
        Activity listSportRecordActivity = getInstrumentation().waitForMonitorWithTimeout(monitorListSportRecordActivity,5000);
        testListSportActivity(listSportRecordActivity);
        listSportRecordActivity.finish();
    }

    Instrumentation.ActivityMonitor monitorAddNewSportRecordActivity = getInstrumentation().addMonitor(addNewSportRecordActivity.class.getName(),null,false);
    @SmallTest
    public void testAddSportRecordButton(){
        assertNotNull(mActivity.findViewById(R.id.addSportRecord));
    }
    @SmallTest
    public void testAddNewSportRecordActivity(Activity addNewSportRecordActivity){
        assertNotNull(addNewSportRecordActivity);
    }
    @Test
    public void testLaunchOfAddNewSportRecordActivityOnButtonClick(){
        testAddSportRecordButton();
        onView(withId(R.id.addSportRecord)).perform(click());
        Activity addNewSportRecordActivity = getInstrumentation().waitForMonitorWithTimeout(monitorAddNewSportRecordActivity,5000);
        testAddNewSportRecordActivity(addNewSportRecordActivity);
        addNewSportRecordActivity.finish();
    }


    Instrumentation.ActivityMonitor monitorAddNewSportProgramActivity = getInstrumentation().addMonitor(addNewSportProgram.class.getName(),null,false);
    @SmallTest
    public void testCreateSportProgramButton(){
        assertNotNull(mActivity.findViewById(R.id.createSportProgram));
    }
    @SmallTest
    public void testAddNewSportProgramActivity(Activity addNewSportProgramActivity){
        assertNotNull(addNewSportProgramActivity);
    }
    @Test
    public void testLaunchOfAddNewSportProgramActivityOnButtonClick(){
        testCreateSportProgramButton();
        onView(withId(R.id.createSportProgram)).perform(click());
        Activity addNewSportProgramActivity = getInstrumentation().waitForMonitorWithTimeout(monitorAddNewSportProgramActivity,5000);
        testAddNewSportProgramActivity(addNewSportProgramActivity);
        addNewSportProgramActivity.finish();
    }

    Instrumentation.ActivityMonitor monitorListSportProgramActivity = getInstrumentation().addMonitor(ListSportProgramActivity.class.getName(),null,false);
    @SmallTest
    public void testShowSportProgramButton(){
        assertNotNull(mActivity.findViewById(R.id.showSportProgram));
    }
    @SmallTest
    public void testListSportProgramActivity(Activity listSportProgramActivity){
        assertNotNull(listSportProgramActivity);
    }
    @Test
    public void testLaunchOfListSportProgramActivityOnButtonClick(){
        testShowSportProgramButton();
        onView(withId(R.id.showSportProgram)).perform(click());
        Activity listSportProgramActivity = getInstrumentation().waitForMonitorWithTimeout(monitorListSportProgramActivity,5000);
        testListSportProgramActivity(listSportProgramActivity);
        listSportProgramActivity.finish();
    }

    @After
    public void tearDown() throws Exception {
        mActivity=null;
    }

}