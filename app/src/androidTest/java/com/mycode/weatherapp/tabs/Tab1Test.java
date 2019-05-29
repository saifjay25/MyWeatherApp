package com.mycode.weatherapp.tabs;

import android.app.Activity;
import android.view.View;
import android.widget.RelativeLayout;

import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Assert;

import androidx.fragment.app.Fragment;
import androidx.test.rule.ActivityTestRule;

import com.mycode.weatherapp.R;
import com.mycode.weatherapp.tabs.Tab1;
import com.mycode.weatherapp.tabs.TestActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static junit.framework.TestCase.assertNotNull;

public class Tab1Test {

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

        Tab1 tab1 = new Tab1();
        testActivity.getSupportFragmentManager().beginTransaction().add(container.getId(), tab1).commitAllowingStateLoss();
        getInstrumentation().waitForIdleSync();
        View temp = tab1.getView().findViewById(R.id.temp);
        assertNotNull(temp);
        View humid = tab1.getView().findViewById(R.id.humid);
        assertNotNull(humid);
        View tempL = tab1.getView().findViewById(R.id.tempL);
        assertNotNull(tempL);
        View tempH = tab1.getView().findViewById(R.id.tempH);
        assertNotNull(tempH);
        View dewpoint = tab1.getView().findViewById(R.id.dewpoint);
        assertNotNull(dewpoint);
        View summary = tab1.getView().findViewById(R.id.summary);
        assertNotNull(summary);
        View precip = tab1.getView().findViewById(R.id.precipP);
        assertNotNull(precip);

    }

    @After
    public void tearDown() throws Exception {
        testActivity = null;
    }
}