package com.cr.o.cdc.sandboxAndroid.utils

import org.junit.Rule

@Suppress("UNCHECKED_CAST")
abstract class FragmentTest {

    @Rule
    @JvmField
    var disableAnimationsRule = DisableAnimationsRule()

    val fakeNavController = FakeNavController()

}
