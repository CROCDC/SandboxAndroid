package com.cr.o.cdc.sandboxAndroid.sandbox.random

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.platform.app.InstrumentationRegistry
import com.cr.o.cdc.sandboxAndroid.MainActivity
import com.cr.o.cdc.sandboxAndroid.coronavirus.di.CoronavirusModule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import junit.framework.TestCase.assertTrue
import kotlinx.android.parcel.Parcelize
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(CoronavirusModule::class)
class ParcelingTest {

    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @Rule
    @JvmField
    val activityTestRule = IntentsTestRule(MainActivity::class.java, true, false)

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun parcelingWithAnnotation() {
        var crash = false
        try {
            activityTestRule.launchActivity(Intent(context, MainActivity::class.java).apply {
                putExtra(
                    "ParcelingClassTestWithAnnotation",
                    ParcelingClassTestWithAnnotation(
                        Pair("name", Bundle().apply {
                            putExtra("ID", "ID")
                        })
                    )
                )
            })
        } catch (e: Exception) {
            crash = true
        }
        assertTrue(crash)
    }

    @Test
    fun parcelingWithoutAnnotation() {
        activityTestRule.launchActivity(Intent(context, MainActivity::class.java).apply {
            putExtra(
                "ParcelingClassTest",
                ParcelingClassTest(
                    Pair("name", Bundle().apply {
                        putExtra("ID", "ID")
                    })
                )
            )
        })
    }

    @Test
    fun parcelingWithAnnotationWithoutBundle() {
        activityTestRule.launchActivity(Intent(context, MainActivity::class.java).apply {
            putExtra(
                "ParcelingClassTestWithAnnotation",
                ParcelingClassTestWithAnnotationWithoutBundle(
                    Pair("name", "name2")
                )
            )
        })
    }
}

@Parcelize
data class ParcelingClassTestWithAnnotation(
    val pair1: Pair<String, Bundle?>? = null,
    val pair2: Pair<String, Bundle?>? = null
) : Parcelable

@Parcelize
data class ParcelingClassTestWithAnnotationWithoutBundle(
    val pair1: Pair<String, String?>? = null,
    val pair2: Pair<String, String?>? = null
) : Parcelable

data class ParcelingClassTest(
    val pair1: Pair<String, Bundle?>? = null,
    val pair2: Pair<String, Bundle?>? = null
) : Parcelable {

    override fun describeContents() = 0

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        pair1?.let {
            parcel.writeString(it.first)
            parcel.writeBundle(it.second)
        }
        pair2?.let {
            parcel.writeString(it.first)
            parcel.writeBundle(it.second)
        }
    }

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readBundle(Bundle::class.java.classLoader),
        parcel.readString(),
        parcel.readBundle(Bundle::class.java.classLoader)
    )

    constructor(
        name1: String? = null,
        bundle1: Bundle? = Bundle(),
        name2: String? = null,
        bundle2: Bundle? = Bundle()
    ) : this(name1?.let { Pair(it, bundle1) },
        name2?.let { Pair(it, bundle2) })

    companion object CREATOR : Parcelable.Creator<ParcelingClassTest> {
        override fun createFromParcel(parcel: Parcel): ParcelingClassTest =
            ParcelingClassTest(parcel)

        override fun newArray(size: Int): Array<ParcelingClassTest?> = arrayOfNulls(size)
    }
}
