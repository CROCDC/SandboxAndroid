package com.cr.o.cdc.sandboxAndroid.buitresenal.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cr.o.cdc.sandboxAndroid.R

class BuitreSenalHomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        startActivity(
            Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://api.instagram.com/oauth/authorize")
                    .buildUpon()
                    .apply {
                        appendQueryParameter(
                            "client_id",
                            "486452559260516"
                        )
                        appendQueryParameter(
                            "redirect_uri",
                            "https://buitresenal.000webhostapp.com/success.html"
                        )
                        appendQueryParameter(
                            "scope",
                            "user_profile,user_media"
                        )
                        appendQueryParameter(
                            "response_type",
                            "code"
                        )
                    }
                    .build()
            }
        )

        return inflater.inflate(R.layout.fragment_buitre_senal_home, container, false)
    }
}
