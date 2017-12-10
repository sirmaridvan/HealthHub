package halmob.healthhub;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import halmob.healthhub.Models.MedicalAnalysis;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Created by RIDVAN SIRMA on 12/11/2017.
 */
public class MedicalAnalysisActivityTest {
    @Rule
    public ActivityTestRule<MedicalAnalysisActivity> mActivityTestRule = new ActivityTestRule<>(MedicalAnalysisActivity.class);
    private MedicalAnalysisActivity mActivity=null;
    @Before
    public void setUp() throws Exception {
        mActivity=mActivityTestRule.getActivity();
    }
    Instrumentation.ActivityMonitor monitoraddAnalysisActivity = getInstrumentation().addMonitor(AddAnalysisActivity.class.getName(),null,false);
    @SmallTest
    public void testaddAnalysis_2(){
        assertNotNull(mActivity.findViewById(R.id.addAnalysis_2));
    }
    @SmallTest
    public void testmedicalAnalysisActivity(Activity medicalAnalysisActivity){
        assertNotNull(medicalAnalysisActivity);
    }
    @Test
    public void testLaunchOfStepCounterActivityOnButtonClickWithId2(){
        testaddAnalysis_2();
        onView(withId(R.id.addAnalysis_2)).perform(click());
        Activity medicalAnalysisActivity = getInstrumentation().waitForMonitorWithTimeout(monitoraddAnalysisActivity,5000);
        testmedicalAnalysisActivity(medicalAnalysisActivity);
        medicalAnalysisActivity.finish();
    }
    @SmallTest
    public void testaddAnalysis_1(){
        assertNotNull(mActivity.findViewById(R.id.addAnalysis_1));
    }
    @Test
    public void testLaunchOfAddAnalysisActivityOnButtonClickwithId1(){
        testaddAnalysis_1();
        onView(withId(R.id.addAnalysis_1)).perform(click());
        Activity medicalAnalysisActivity = getInstrumentation().waitForMonitorWithTimeout(monitoraddAnalysisActivity,5000);
        assertNotNull(medicalAnalysisActivity);
        medicalAnalysisActivity.finish();
    }

    Instrumentation.ActivityMonitor monitorMedicalAnalysisReportActivity = getInstrumentation().addMonitor(MedicalAnalysisReportsActivity.class.getName(),null,false);

    @SmallTest
    public void testshowAnalysis_2(){
        assertNotNull(mActivity.findViewById(R.id.showAnalysis_2));
    }
    @SmallTest
    public void testmedicalAnalysisReportActivity(Activity medicalAnalysisReportActivity){
        assertNotNull(medicalAnalysisReportActivity);
    }
    @Test
    public void testLaunchOfActivityOnButtonClickWithId2(){
        testshowAnalysis_2();
        onView(withId(R.id.showAnalysis_2)).perform(click());
        Activity medicalAnalysisReportActivity = getInstrumentation().waitForMonitorWithTimeout(monitorMedicalAnalysisReportActivity,5000);
        testmedicalAnalysisReportActivity(medicalAnalysisReportActivity);
        medicalAnalysisReportActivity.finish();
    }
    @SmallTest
    public void testshowAnalysis_1(){
        assertNotNull(mActivity.findViewById(R.id.showAnalysis_1));
    }
    @Test
    public void testLaunchOfActivityOnButtonClickWithId1(){
        assertNotNull(mActivity.findViewById(R.id.showAnalysis_1));
        onView(withId(R.id.showAnalysis_1)).perform(click());
        Activity medicalAnalysisReportActivity = getInstrumentation().waitForMonitorWithTimeout(monitorMedicalAnalysisReportActivity,5000);
        assertNotNull(medicalAnalysisReportActivity);
        medicalAnalysisReportActivity.finish();
    }

    @After
    public void tearDown() throws Exception {
        mActivity=null;
    }

}