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
 * Created by RIDVAN SIRMA on 12/10/2017.
 */
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    private MainActivity mActivity=null;
    @Before
    public void setUp() throws Exception {
        mActivity=mActivityTestRule.getActivity();
    }
    Instrumentation.ActivityMonitor monitorForStepCounterActivity = getInstrumentation().addMonitor(StepCounterActivity.class.getName(),null,false);
    @SmallTest
    public void teststep_counter(){
        assertNotNull(mActivity.findViewById(R.id.step_counter));
    }
    @SmallTest
    public void teststepCounterActivity(Activity stepCounterActivity){
        assertNotNull(stepCounterActivity);
    }
    @Test
    public void testLaunchOfStepCounterActivityOnButtonClick(){
        teststep_counter();
        onView(withId(R.id.step_counter)).perform(click());
        Activity stepCounterActivity = getInstrumentation().waitForMonitorWithTimeout(monitorForStepCounterActivity,5000);
        teststepCounterActivity(stepCounterActivity);
        stepCounterActivity.finish();
    }
    Instrumentation.ActivityMonitor monitorForDiabActivity = getInstrumentation().addMonitor(DiabActivity.class.getName(),null,false);
    @SmallTest
    public void testdiabetes_button(){
        assertNotNull(mActivity.findViewById(R.id.diabetes_button));
    }
    @SmallTest
    public void testdiabActivity(Activity diabActivity){
        assertNotNull(diabActivity);
    }
    @Test
    public void testLaunchOfMedicineActivityOnButtonClick(){
        testdiabetes_button();
        onView(withId(R.id.diabetes_button)).perform(click());
        Activity diabActivity = getInstrumentation().waitForMonitorWithTimeout(monitorForDiabActivity,5000);
        assertNotNull(diabActivity);
        diabActivity.finish();
    }

    Instrumentation.ActivityMonitor monitorSportsActivity = getInstrumentation().addMonitor(Sports_Activity.class.getName(),null,false);
    @SmallTest
    public void testsportsPage_button(){
        assertNotNull(mActivity.findViewById(R.id.sportsPage_button));
    }
    @SmallTest
    public void testsportsActivity(Activity sportsActivity) {
        assertNotNull(sportsActivity);
    }
    @Test
    public void testLaunchOfSportsActivityOnButtonClick(){
        testsportsPage_button();
        onView(withId(R.id.sportsPage_button)).perform(click());
        Activity sportsActivity = getInstrumentation().waitForMonitorWithTimeout(monitorSportsActivity,5000);
        testsportsActivity(sportsActivity);
        sportsActivity.finish();
    }

    Instrumentation.ActivityMonitor monitorMedicineMainActivity = getInstrumentation().addMonitor(MedicineMainActivity.class.getName(),null,false);
    @SmallTest
    public void testtrack_medicine(){
        assertNotNull(mActivity.findViewById(R.id.track_medicine));
    }
    @SmallTest
    public void testmedicineMainActivity(Activity medicineMainActivity) {
        assertNotNull(medicineMainActivity);
    }
    @Test
    public void testLaunchOfMedicineMainActivityOnButtonClick(){
        testtrack_medicine();
        onView(withId(R.id.track_medicine)).perform(click());
        Activity medicineMainActivity = getInstrumentation().waitForMonitorWithTimeout(monitorMedicineMainActivity,5000);
        testmedicineMainActivity(medicineMainActivity);
        medicineMainActivity.finish();
    }

    Instrumentation.ActivityMonitor monitorProfilePageActivity = getInstrumentation().addMonitor(ProfilePageActivity.class.getName(),null,false);
    @SmallTest
    public void testprofilePage_button(){
        assertNotNull(mActivity.findViewById(R.id.profilePage_button));
    }
    @SmallTest
    public void testmprofilePageActivity(Activity mprofilePageActivity) {
        assertNotNull(mprofilePageActivity);
    }
    @Test
    public void testLaunchOfProfilePageActivityOnButtonClick(){
        testprofilePage_button();
        onView(withId(R.id.profilePage_button)).perform(click());
        Activity mprofilePageActivity = getInstrumentation().waitForMonitorWithTimeout(monitorProfilePageActivity,5000);
        testmprofilePageActivity(mprofilePageActivity);
        mprofilePageActivity.finish();
    }

    Instrumentation.ActivityMonitor monitorMedicalAnalysisActivity = getInstrumentation().addMonitor(MedicalAnalysisActivity.class.getName(),null,false);
    @SmallTest
    public void testmedical_analysis_page(){
        assertNotNull(mActivity.findViewById(R.id.medical_analysis_page));
    }
    @SmallTest
    public void testmedicalAnalysisActivity(Activity medicalAnalysisActivity) {
        assertNotNull(medicalAnalysisActivity);
    }
    @Test
    public void testLaunchOfMedicalAnalysisActivityOnButtonClick(){
        testmedical_analysis_page();
        onView(withId(R.id.medical_analysis_page)).perform(click());
        Activity medicalAnalysisActivity = getInstrumentation().waitForMonitorWithTimeout(monitorMedicalAnalysisActivity,5000);
        testmedicalAnalysisActivity(medicalAnalysisActivity);
        medicalAnalysisActivity.finish();
    }


    Instrumentation.ActivityMonitor monitorHeartRateMonitorActivity = getInstrumentation().addMonitor(HeartRateMonitorActivity.class.getName(),null,false);
    @SmallTest
    public void testheart_rate_button(){
        assertNotNull(mActivity.findViewById(R.id.heart_rate_button));
    }
    @SmallTest
    public void testheartRateActivity(Activity heartRateActivity) {
        assertNotNull(heartRateActivity);
    }
    @Test
    public void testLaunchOfHeartRateActivityOnButtonClick(){
        testheart_rate_button();
        onView(withId(R.id.heart_rate_button)).perform(click());
        Activity heartRateActivity = getInstrumentation().waitForMonitorWithTimeout(monitorHeartRateMonitorActivity,5000);
        testheartRateActivity(heartRateActivity);
        heartRateActivity.finish();
    }
    @After
    public void tearDown() throws Exception {
        mActivity=null;
    }

}