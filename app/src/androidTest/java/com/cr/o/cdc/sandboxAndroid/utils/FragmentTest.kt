package com.cr.o.cdc.sandboxAndroid.utils

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import androidx.annotation.StyleRes
import androidx.core.util.Preconditions
import androidx.fragment.app.Fragment
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import com.cr.o.cdc.sandboxAndroid.HiltActivity
import com.cr.o.cdc.sandboxAndroid.R
import org.junit.Rule

@Suppress("UNCHECKED_CAST")
abstract class FragmentTest {

    @Rule
    @JvmField
    var disableAnimationsRule = DisableAnimationsRule()

    val fakeNavController = FakeNavController()

    inline fun <reified T : Fragment> launchFragmentInHiltContainer(
        fragmentArgs: Bundle? = null,
        crossinline action: Fragment.() -> Unit = {}
    ) {
        val startActivityIntent = Intent.makeMainActivity(
            ComponentName(
                ApplicationProvider.getApplicationContext(),
                HiltActivity::class.java
            )
        )

        ActivityScenario.launch<HiltActivity>(startActivityIntent).onActivity { activity ->
            val fragment: Fragment = activity.supportFragmentManager.fragmentFactory.instantiate(
                Preconditions.checkNotNull(T::class.java.classLoader),
                T::class.java.name
            )
            fragment.arguments = fragmentArgs
            activity.supportFragmentManager
                .beginTransaction()
                .add(android.R.id.content, fragment, "")
                .commitNow()

            fragment.action()
        }
    }
}
