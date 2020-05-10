package com.cr.o.cdc.sandboxAndroid.autoafip.repos

import com.cr.o.cdc.sandboxAndroid.autoafip.model.Profile
import org.junit.Test
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.NoSuchAlgorithmException

class Login {


    @Test
    fun keys() {
        val keys = buildKeys()!!
        //TODO encode
        val profile = Profile("20419160270", keys.public, keys.private)

        print(profile)

    }


    private fun buildKeys(): KeyPair? {
        return try {
            val keyGen =
                KeyPairGenerator.getInstance("RSA")
            keyGen.initialize(2048)
            keyGen.genKeyPair()
        } catch (e: NoSuchAlgorithmException) {
            throw Exception(e.message)
        }
    }

}