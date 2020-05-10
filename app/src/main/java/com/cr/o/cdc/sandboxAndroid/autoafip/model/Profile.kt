package com.cr.o.cdc.sandboxAndroid.autoafip.model

import java.security.PrivateKey
import java.security.PublicKey

data class Profile(val cuit: String, val publicKey: PublicKey, val privateKey: PrivateKey)