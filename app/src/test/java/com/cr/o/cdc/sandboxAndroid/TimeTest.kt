package com.cr.o.cdc.sandboxAndroid

import junit.framework.TestCase.assertTrue
import org.junit.Test


class TimeTest {

    @Test
    fun inInterval__8_0_0__9_0_0() {
        assertTrue(
            inInterval(
                ScheduleInterval(
                    Time(8, 0, 0),
                    Time(9, 0, 0)
                ),
                ScheduleDefined(
                    "__ScheduleDefined",
                    listOf(
                        ScheduleInterval(
                            Time(8, 0, 0),
                            Time(9, 0, 0)
                        )
                    )
                )
            )
        )
    }

    @Test
    fun inInterval__23_0_0__23_59_59() {
        assertTrue(
            inInterval(
                ScheduleInterval(
                    Time(23, 0, 0),
                    Time(24, 0, 0)
                ),
                ScheduleDefined(
                    "__ScheduleDefined",
                    listOf(
                        ScheduleInterval(
                            Time(23, 0, 0),
                            Time(23, 59, 59)
                        )
                    )
                )
            )
        )
    }
}

private fun inInterval(range: ScheduleInterval, scheduleDefined: ScheduleDefined?) =
    scheduleDefined?.intervals?.find {
        range.startsAt >= it.startsAt && range.endsAt <= it.endsAt
    } != null

data class Time(val hour: Int, val minute: Int, val second: Int) : Comparable<Time> {

    override fun compareTo(other: Time): Int = when {
        hour != other.hour -> hour - other.hour
        minute != other.minute -> minute - other.minute
        else -> second - other.second
    }

    override fun toString(): String = "$hour:$minute:$second"

    companion object {
        const val COLS = "{hour,minute,second}"
    }
}

data class ScheduleDefined(val __typename: String, val intervals: List<ScheduleInterval>?) {
    companion object {
        const val COLS = "{__typename ... on ScheduleDefined{intervals${ScheduleInterval.COLS}}}"
    }
}

data class ScheduleInterval(val startsAt: Time, val endsAt: Time) {
    companion object {
        const val COLS = "{endsAt${Time.COLS}startsAt${Time.COLS}}"
    }
}