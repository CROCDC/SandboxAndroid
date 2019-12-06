package com.cr.o.cdc.sandboxAndroid

import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.android.parcel.Parcelize
import org.junit.Rule
import org.junit.Test

class ParcelizeTest {

    val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Rule
    @JvmField
    val activityTestRule = IntentsTestRule(MainActivity::class.java, true, false)

    @Test
    fun parcelizeWithAnnotation() {
        activityTestRule.launchActivity(Intent(context, MainActivity::class.java).apply {
            putExtra(
                "ParcelizeClassTestWithAnnotation", ParcelizeClassTestWithAnnotation(
                    Pair("name", Bundle().apply {
                        putExtra("ID", "ID")
                    })
                )
            )
        })
    }

    @Test
    fun parcelizeWithoutAnnotation() {
        activityTestRule.launchActivity(Intent(context, MainActivity::class.java).apply {
            putExtra(
                "ParcelizeClassTest", ParcelizeClassTest(
                    Pair("name", Bundle().apply {
                        putExtra("ID", "ID")
                    })
                )
            )
        })
    }

    @Test
    fun parcelizeWithAnnotationWithoutBundle() {
        activityTestRule.launchActivity(Intent(context, MainActivity::class.java).apply {
            putExtra(
                "ParcelizeClassTestWithAnnotation", ParcelizeClassTestWithAnnotationWithoutBundle(
                    Pair("name", "name2")
                )
            )
        })
    }
}

@Parcelize
data class ParcelizeClassTestWithAnnotation(
    val pair1: Pair<String, Bundle?>? = null,
    val pair2: Pair<String, Bundle?>? = null
) : Parcelable {

    constructor(
        name1: String? = null,
        bundle1: Bundle? = Bundle(),
        name2: String? = null,
        bundle2: Bundle? = Bundle()
    ) : this(name1?.let { Pair(it, bundle1) },
        name2?.let { Pair(it, bundle2) })
}

@Parcelize
data class ParcelizeClassTestWithAnnotationWithoutBundle(
    val pair1: Pair<String, String?>? = null,
    val pair2: Pair<String, String?>? = null
) : Parcelable {

    constructor(
        name1: String? = null,
        bundle1: String? = null,
        name2: String? = null,
        bundle2: String? = null
    ) : this(name1?.let { Pair(it, bundle1) },
        name2?.let { Pair(it, bundle2) })
}

data class ParcelizeClassTest(
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

    companion object CREATOR : Parcelable.Creator<ParcelizeClassTest> {
        override fun createFromParcel(parcel: Parcel): ParcelizeClassTest =
            ParcelizeClassTest(parcel)

        override fun newArray(size: Int): Array<ParcelizeClassTest?> = arrayOfNulls(size)
    }
}
