package com.cr.o.cdc.sandboxAndroid.coronavirus.fragments

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.cr.o.cdc.networking.RetrofitResponse
import com.cr.o.cdc.networking.RetrofitSuccessResponse
import com.cr.o.cdc.sandboxAndroid.R
import com.cr.o.cdc.sandboxAndroid.coronavirus.model.CasesByCountry
import com.cr.o.cdc.sandboxAndroid.coronavirus.model.CountryStat
import com.cr.o.cdc.sandboxAndroid.coronavirus.repos.CoronavirusService
import com.cr.o.cdc.sandboxAndroid.di.AppModule
import com.cr.o.cdc.sharedtest.makeRandomInstance
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test

class CoronavirusFragmentTest {


    @Before
    fun init() {
        AppModule.setCoronavirusService(mockk<CoronavirusService>().apply {
            every { getCasesByCountry() } returns MutableLiveData<RetrofitResponse<CasesByCountry>>(
                RetrofitSuccessResponse(
                    CasesByCountry(
                        listOf(
                            CountryStat::class.makeRandomInstance(),
                            CountryStat::class.makeRandomInstance(),
                            CountryStat::class.makeRandomInstance(),
                            CountryStat::class.makeRandomInstance()
                        )
                    )
                )
            )
        })
    }

    @Test
    fun testRecyclerView() {
        launchFragmentInContainer<CoronavirusFragment>()

        onView(withId(R.id.recycler)).check { view, _ ->
            assertTrue((view as RecyclerView).adapter?.itemCount != 0)
        }

    }
}