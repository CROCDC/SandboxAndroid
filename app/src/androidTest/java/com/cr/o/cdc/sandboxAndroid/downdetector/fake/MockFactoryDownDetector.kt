package com.cr.o.cdc.sandboxAndroid.downdetector.fake

import com.cr.o.cdc.sandboxAndroid.downdetector.db.model.Site

object MockFactoryDownDetector {

    fun getSite(): Site = Site(
        "address",
        "name",
        true,
        20,
        0,
        null,
        null,
        null,
        null
    )
}
