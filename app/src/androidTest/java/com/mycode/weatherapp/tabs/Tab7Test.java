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

public class Tab7Test {

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

        Tab7 tab7 = new Tab7();
        testActivity.getSupportFragmentManager().beginTransaction().add(container.getId(), tab7).commitAllowingStateLoss();
        getInstrumentation().waitForIdleSync();
        View temp = tab7.getView().findViewById(R.id.temp);
        assertNotNull(temp);
        View humid = tab7.getView().findViewById(R.id.humid);
        assertNotNull(humid);
        View tempL = tab7.getView().findViewById(R.id.tempL);
        assertNotNull(tempL);
        View tempH = tab7.getView().findViewById(R.id.tempH);
        assertNotNull(tempH);
        View dewpoint = tab7.getView().findViewById(R.id.dewpoint);
        assertNotNull(dewpoint);
        View summary = tab7.getView().findViewById(R.id.summary);
        assertNotNull(summary);
        View precip = tab7.getView().findViewById(R.id.precipP);
        assertNotNull(precip);

    }

    @After
    public void tearDown() throws Exception {
        testActivity = null;
    }

}