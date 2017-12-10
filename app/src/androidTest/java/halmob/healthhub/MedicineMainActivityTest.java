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
public class MedicineMainActivityTest {
    @Rule
    public ActivityTestRule<MedicineMainActivity> mActivityTestRule = new ActivityTestRule<>(MedicineMainActivity.class);
    private MedicineMainActivity mActivity=null;
    @Before
    public void setUp() throws Exception {
        mActivity=mActivityTestRule.getActivity();
    }
    Instrumentation.ActivityMonitor monitorForMedicineActivity = getInstrumentation().addMonitor(MedicineActivity.class.getName(),null,false);
    @SmallTest
    public void testAddButton(){
        assertNotNull(mActivity.findViewById(R.id.addButton));
    }
    @SmallTest
    public void testmedicineActivity(Activity medicineActivity){
        assertNotNull(medicineActivity);
    }
    @Test
    public void testLaunchOfMedicineActivityOnButtonClick(){
        testAddButton();
        onView(withId(R.id.addButton)).perform(click());
        Activity medicineActivity = getInstrumentation().waitForMonitorWithTimeout(monitorForMedicineActivity,5000);
        testmedicineActivity(medicineActivity);
        medicineActivity.finish();
    }

    Instrumentation.ActivityMonitor monitorForMedicineListActivity = getInstrumentation().addMonitor(MedicineListActivity.class.getName(),null,false);
    @SmallTest
    public void testListButton(){
        assertNotNull(mActivity.findViewById(R.id.listButton));
    }
    @SmallTest
    public void testmedicineListActivity(Activity medicineListActivity){
        assertNotNull(medicineListActivity);
    }
    @Test
    public void testLaunchOfMedicineListActivityOnButtonClick(){
        testListButton();
        onView(withId(R.id.listButton)).perform(click());
        Activity medicineListActivity = getInstrumentation().waitForMonitorWithTimeout(monitorForMedicineListActivity,5000);
        testmedicineListActivity(medicineListActivity);
        medicineListActivity.finish();
    }

    Instrumentation.ActivityMonitor monitorForAddProspectusActivity = getInstrumentation().addMonitor(AddProspectusActivity.class.getName(),null,false);
    @SmallTest
    public void testProspectusAddButton(){
        assertNotNull(mActivity.findViewById(R.id.prospectusAddButton));
    }
    @SmallTest
    public void testaddProspectusActivity(Activity addProspectusActivity){
        assertNotNull(addProspectusActivity);
    }
    @Test
    public void testLaunchOfAddProspectusActivityOnButtonClick(){
        testProspectusAddButton();
        onView(withId(R.id.prospectusAddButton)).perform(click());
        Activity addProspectusActivity = getInstrumentation().waitForMonitorWithTimeout(monitorForAddProspectusActivity,5000);
        testaddProspectusActivity(addProspectusActivity);
        addProspectusActivity.finish();
    }


    Instrumentation.ActivityMonitor monitorForListProspecturActivity = getInstrumentation().addMonitor(ListProspectusActivity.class.getName(),null,false);
    @SmallTest
    public void testProspectusInfoButton(){
        assertNotNull(mActivity.findViewById(R.id.prospectusInfoButton));
    }
    @SmallTest
    public void testlistProspectusActivity(Activity listProspectusActivity){
        assertNotNull(listProspectusActivity);
    }
    @Test
    public void testLaunchOfListProspectusActivityOnButtonClick(){
        testProspectusAddButton();
        onView(withId(R.id.prospectusInfoButton)).perform(click());
        Activity listProspectusActivity = getInstrumentation().waitForMonitorWithTimeout(monitorForListProspecturActivity,5000);
        testlistProspectusActivity(listProspectusActivity);
        listProspectusActivity.finish();
    }

    @After
    public void tearDown() throws Exception {
        mActivity=null;
    }

}