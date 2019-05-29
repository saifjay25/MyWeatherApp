package com.mycode.weatherapp.tabs;

import android.view.View;
import android.widget.RelativeLayout;

import androidx.test.rule.ActivityTestRule;

import com.mycode.weatherapp.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.*;

public class Tab6Test {

    @Rule
    public ActivityTestRule<TestActivity> activityTestRule = new ActivityTestRule<>(TestActivity.class);

    private TestActivity testActivity = null;

    @Before
    public void setUp() throws Exception {
        testActivity = activityTestRule.getActivity();
    }

    @Test
    public void testLaunch(){
        RelativeLayout container = testActivity.findViewById(R.id.test);
        assertNotNull(container);

        Tab6 tab6 = new Tab6();
        testActivity.getSupportFragmentManager().beginTransaction().add(container.getId(), tab6).commitAllowingStateLoss();
        getInstrumentation().waitForIdleSync();
        View temp = tab6.getView().findViewById(R.id.temp);
        assertNotNull(temp);
        View humid = tab6.getView().findViewById(R.id.humid);
        assertNotNull(humid);
        View tempL = tab6.getView().findViewById(R.id.tempL);
        assertNotNull(tempL);
        View tempH = tab6.getView().findViewById(R.id.tempH);
        assertNotNull(tempH);
        View dewpoint = tab6.getView().findViewById(R.id.dewpoint);
        assertNotNull(dewpoint);
        View summary = tab6.getView().findViewById(R.id.summary);
        assertNotNull(summary);
        View precip = tab6.getView().findViewById(R.id.precipP);
        assertNotNull(precip);

    }

    @After
    public void tearDown() throws Exception {
        testActivity = null;
    }

}