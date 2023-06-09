package com.app.fetchTestApp.utils

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

import java.util.stream.Stream
class DateValidatorTest {


    @ParameterizedTest(name = "#{index} - Run test with date = {0}")
    @MethodSource("validDateProvider")
    fun test_date_java_api_valid(date: String?) {
        assertTrue(parseDate(date).isNotEmpty())
    }

    @ParameterizedTest(name = "#{index} - Run test with date = {0}")
    @MethodSource("invalidDateProvider")
    fun test_date_java_api_invalid(date: String?) {
        assertFalse(parseDate(date).isNotEmpty())
    }

    companion object {
        @JvmStatic
        fun validDateProvider(): Stream<String> {
            return Stream.of(
                "2019-02-23 07:56:26.686128",
                "1998-09-30 12:01:00.00567",
                "1998-9-30 12:01:00.00567",
                "2020-09-1 12:01:00.00567",
                "2020-09-01 12:01:00.00567",
                "2020-9-1 12:01:00.00567",
                "2020-9-01 12:01:00.00567",
                "2020-2-29 12:01:00.00567",  // leap year
                "2020-2-28 12:01:00.00567",  // leap year
                "2019-2-28 12:01:00.00567",  // common year
                "2000-02-29 12:01:00.00567",  // 2000 is a leap year, % 400 == 0
                "1900-02-28 12:01:00.00567",  // 1900 is a common year
                "2020-07-31 12:01:00.00567",
                "2020-08-31 12:01:00.00567",
                "2020-06-30 12:01:00.00567",
                "1900-01-01 12:01:00.00567",
                "2099-12-31 12:01:00.00567"
            )
        }

        @JvmStatic
        fun invalidDateProvider(): Stream<String> {
            return Stream.of(
                "1998-09-31",  // invalid day, sep max 30
                "1998-11-31",  // invalid day, nov max 30
                "2008-02-2x",  // invalid day 2x
                "2008-0x-28",  // invalid month 0x
                "20xx-02-28",  // invalid year 20xx
                "20-11-02",  // invalid year 20, must be yyyy
                "2020/11/02",  // invalid date format, yyyy-mm-dd
                "2020-11-32",  // invalid day, 32
                "2020-13-30",  // invalid month 13
                "2020-A-20",  // invalid month A
                "2020-2-30",  // leap year, feb max 29
                "2019-2-29",  // common year, feb max 28
                "1900-02-29",  // 1900 is a common year, feb max 28
                "12012-04-05",  // support only 4 digits years
                " ",  // empty
                ""// empty
            )
        }
    }
}