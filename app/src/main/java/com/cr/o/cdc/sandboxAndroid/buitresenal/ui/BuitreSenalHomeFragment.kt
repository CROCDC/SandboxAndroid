package com.cr.o.cdc.sandboxAndroid.buitresenal.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cr.o.cdc.sandboxAndroid.R
import com.cr.o.cdc.sandboxAndroid.buitresenal.util.InstagramApiInfo

class BuitreSenalHomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        startActivity(
            Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("${InstagramApiInfo.API_URL}/oauth/authorize")
                    .buildUpon()
                    .apply {
                        appendQueryParameter("client_id", InstagramApiInfo.CLIENT_ID)
                        appendQueryParameter(
                            "redirect_uri", InstagramApiInfo.REDIRECT_URI
                        )
                        appendQueryParameter("scope", InstagramApiInfo.SCOPE)
                        appendQueryParameter("response_type", InstagramApiInfo.RESPONSE_TYPE)
                    }
                    .build()
            }
        )

        return inflater.inflate(R.layout.fragment_buitre_senal_home, container, false)
    }
}
