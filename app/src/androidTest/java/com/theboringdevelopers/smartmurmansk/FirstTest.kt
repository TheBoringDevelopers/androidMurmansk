package com.theboringdevelopers.smartmurmansk

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.theboringdevelopers.smartmurmansk.activity.EnterActivity
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.endsWith
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class FirstTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(EnterActivity::class.java)

    @Test
    fun listGoesOverTheFold() {
        onView(withText("Начать")).check(matches(isDisplayed()))
        onView(withText("Начать")).perform(click())
        onView(allOf(withClassName(endsWith("EditText")))).perform(typeText("9672354111"))
    }
}