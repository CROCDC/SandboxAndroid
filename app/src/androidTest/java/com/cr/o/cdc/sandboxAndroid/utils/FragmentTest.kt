package com.cr.o.cdc.sandboxAndroid.utils

import com.cr.o.cdc.sharedtest.DisableAnimationsRule
import org.junit.Rule

@Suppress("UNCHECKED_CAST")
abstract class FragmentTest {

    @Rule
    @JvmField
    var disableAnimationsRule = DisableAnimationsRule()
}
