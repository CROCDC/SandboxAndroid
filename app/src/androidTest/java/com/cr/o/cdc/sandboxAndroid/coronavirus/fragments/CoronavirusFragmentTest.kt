package com.cr.o.cdc.sandboxAndroid.coronavirus.fragments

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.cr.o.cdc.networking.RetrofitResponse
import com.cr.o.cdc.sandboxAndroid.R
import com.cr.o.cdc.sandboxAndroid.coronavirus.model.CasesByCountry
import com.cr.o.cdc.sandboxAndroid.coronavirus.model.CountryStat
import com.cr.o.cdc.sandboxAndroid.coronavirus.repos.CoronavirusService
import com.cr.o.cdc.sandboxAndroid.di.AppModule
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class CoronavirusFragmentTest {


    @Before
    fun init() {
        AppModule.setCoronavirusService(object : CoronavirusService {
            override fun getCasesByCountry(): LiveData<RetrofitResponse<CasesByCountry>> =
                MutableLiveData(
                    RetrofitResponse.create(
                        Response.success(
                            CasesByCountry(
                                listOf(
                                    CountryStat("country1", "100", "100"),
                                    CountryStat("country2", "100", "100")
                                )
                            )
                        )
                    )
                )

        })
    }

    @Test
    fun testRecyclerView() {
        launchFragmentInContainer<CoronavirusFragment>()

        Thread.sleep(300)

        onView(withId(R.id.recycler)).check { view, _ ->
            assertTrue((view as RecyclerView).adapter?.itemCount != 0)
        }

    }
}