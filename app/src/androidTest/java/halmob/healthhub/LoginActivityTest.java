package halmob.healthhub;

import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by RIDVAN SIRMA on 12/11/2017.
 */
public class LoginActivityTest {
    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);
    private LoginActivity mActivity=null;
    @Before
    public void setUp() throws Exception {
        mActivity=mActivityTestRule.getActivity();
    }
    @Test
    public void testlogin_button(){
        assertNotNull(mActivity.findViewById(R.id.login_button));
    }
    @Test
    public void testsignInButton(){
        assertNotNull(mActivity.findViewById(R.id.signInButton));
    }

    @After
    public void tearDown() throws Exception {
        mActivity=null;
    }

}