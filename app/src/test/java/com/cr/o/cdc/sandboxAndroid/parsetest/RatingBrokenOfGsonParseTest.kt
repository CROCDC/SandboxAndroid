package com.cr.o.cdc.sandboxAndroid.parsetest

import com.cr.o.cdc.sharedtest.Rating
import com.cr.o.cdc.sharedtest.RatingBrokenOfGson
import com.cr.o.cdc.sharedtest.getStars
import junit.framework.TestCase.assertTrue
import org.junit.Test

class RatingBrokenOfGsonParseTest {

    @Test
    fun parseRatingBrokenWithSimpleWSParser() {
        val rating = SimpleWSParser(RatingBrokenOfGson::class.java, "rating").parserResponse(
            RatingBrokenOfGson.json
        )

        assertTrue(rating?.stars == 0.0F)
    }

    @Test
    fun parseRatingWithSimpleWSParser() {
        val rating = SimpleWSParser(Rating::class.java, "rating").parserResponse(
            RatingBrokenOfGson.json
        )

        assertTrue(rating?.getStars() == 5F)
    }
}
