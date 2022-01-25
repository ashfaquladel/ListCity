
package com.example.simpleparadox.listycity;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import android.app.Activity;
import android.widget.EditText;
import android.widget.ListView;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class secondTest {
    private Solo solo_MainActivity, solo_mysecondActivity;
    @Rule
    public ActivityTestRule<MainActivity> rule_MainActivity =
            new ActivityTestRule<>(MainActivity.class, true, true);
    public ActivityTestRule<mysecondActivity> rule_mysecondActivity =
            new ActivityTestRule<>(mysecondActivity.class, true, true);

    @Before
    public void setUp() throws Exception {
        solo_MainActivity = new Solo(InstrumentationRegistry.getInstrumentation(), rule_MainActivity.getActivity());
        solo_mysecondActivity = new Solo(InstrumentationRegistry.getInstrumentation(), rule_mysecondActivity.getActivity());
    }

    @Test
    public void start() throws Exception {
        Activity activity_MainActivity = rule_MainActivity.getActivity();
        Activity activity_mysecondActivity = rule_mysecondActivity.getActivity();
    }



    @Test
    public void testing() {
        solo_MainActivity.assertCurrentActivity("this is not MainActivity", MainActivity.class);
        // adding some data
        solo_MainActivity.clickOnButton("ADD CITY");
        solo_MainActivity.enterText((EditText) solo_MainActivity.getView(R.id.editText_name), "1707094");
        solo_MainActivity.clickOnButton("confirm");
        solo_MainActivity.waitForText("Edmonton", 1, 2000);

        solo_MainActivity.clickOnButton("ADD CITY");
        solo_MainActivity.enterText((EditText) solo_MainActivity.getView(R.id.editText_name), "adel");
        solo_MainActivity.clickOnButton("confirm");
        solo_MainActivity.waitForText("Edmonton", 1, 2000);

        // Get MainActivity to access its variables and methods
        MainActivity activity = (MainActivity) solo_MainActivity.getCurrentActivity();
        final ListView cityList = activity.cityList; // Get the listview
        String city = (String) cityList.getItemAtPosition(0);
        assertEquals("1707094", city);


        // this should send to mysecondActivity activity
        solo_MainActivity.clickInList(0);


        // checking if i'm in mysecondActivity activity
        solo_mysecondActivity.assertCurrentActivity("this is not mysecondActivity", mysecondActivity.class);

        solo_mysecondActivity.clickOnButton("BACK");

        solo_MainActivity.assertCurrentActivity("this is not MainActivity", MainActivity.class);

        // clearing all the data
        solo_MainActivity.clickOnButton("ClEAR ALL");
    }














    /**
     * Close activity after each test
     *
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        solo_MainActivity.finishOpenedActivities();
    }
}