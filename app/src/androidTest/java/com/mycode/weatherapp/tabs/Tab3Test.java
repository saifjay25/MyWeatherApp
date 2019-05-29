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

public class Tab3Test {
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

        Tab3 tab3 = new Tab3();
        testActivity.getSupportFragmentManager().beginTransaction().add(container.getId(), tab3).commitAllowingStateLoss();
        getInstrumentation().waitForIdleSync();
        View temp = tab3.getView().findViewById(R.id.temp);
        assertNotNull(temp);
        View humid = tab3.getView().findViewById(R.id.humid);
        assertNotNull(humid);
        View tempL = tab3.getView().findViewById(R.id.tempL);
        assertNotNull(tempL);
        View tempH = tab3.getView().findViewById(R.id.tempH);
        assertNotNull(tempH);
        View dewpoint = tab3.getView().findViewById(R.id.dewpoint);
        assertNotNull(dewpoint);
        View summary = tab3.getView().findViewById(R.id.summary);
        assertNotNull(summary);
        View precip = tab3.getView().findViewById(R.id.precipP);
        assertNotNull(precip);

    }

    @After
    public void tearDown() throws Exception {
        testActivity = null;
    }


}