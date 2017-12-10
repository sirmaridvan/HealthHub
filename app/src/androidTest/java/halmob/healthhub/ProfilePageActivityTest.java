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
public class ProfilePageActivityTest {
    @Rule
    public ActivityTestRule<ProfilePageActivity> mActivityTestRule = new ActivityTestRule<>(ProfilePageActivity.class);
    private ProfilePageActivity mActivity=null;
    @Before
    public void setUp() throws Exception {
        mActivity=mActivityTestRule.getActivity();
    }
    /*@Test
    public void testProfilePhotoImageView(){
        assertNotNull(mActivity.findViewById(R.id.profile_photo));
    }
    @Test
    public void testFollowButton(){
        assertNotNull(mActivity.findViewById(R.id.followButton));
    }
    @Test
    public void testCommentButton(){
        assertNotNull(mActivity.findViewById(R.id.button_comment));
    }
    @Test
    public void testDetailButton(){
        assertNotNull(mActivity.findViewById(R.id.buton_detail));
    }
    @Test
    public void testEmailTextView(){
        assertNotNull(mActivity.findViewById(R.id.email));
    }
    @Test
    public void testNameTextView(){
        assertNotNull(mActivity.findViewById(R.id.name));
    }*/

    @After
    public void tearDown() throws Exception {
        mActivity=null;
    }

}