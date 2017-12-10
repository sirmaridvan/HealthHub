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
public class WriteCommentActivityTest {
    @Rule
    public ActivityTestRule<WriteCommentActivity> mActivityTestRule = new ActivityTestRule<>(WriteCommentActivity.class);
    private WriteCommentActivity mActivity=null;
    @Before
    public void setUp() throws Exception {
        mActivity=mActivityTestRule.getActivity();
    }
    @Test
    public void testsend_comment(){
        assertNotNull(mActivity.findViewById(R.id.send_comment));
    }
    @Test
    public void testedittext_comment(){
        assertNotNull(mActivity.findViewById(R.id.edittext_comment));
    }

    @After
    public void tearDown() throws Exception {
        mActivity=null;
    }

}