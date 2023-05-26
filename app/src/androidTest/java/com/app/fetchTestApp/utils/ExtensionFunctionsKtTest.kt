package com.app.fetchTestApp.utils

import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Test

import org.junit.Assert.*

class ExtensionFunctionsKtTest {
    private val dataToCheck =
        "{\"messages\":{\"error_messages\":{\"internet\":\"CheckyourinternetandTryagain.\",\"internal\":\"SomeInternalErrorHasOccurred.\",\"file_format\":\"Thisfileformatnotsupported.\",\"no_result\":\"NoResultFound\"}},\"listing_screen\":{\"header_title\":\"Header\"}}"

    @Test
    fun loadJSONFromAssetsFalseCase() {
        val result: String =
            InstrumentationRegistry.getInstrumentation().targetContext.loadJSONFromAssets("AppAndroidEn.json")
        assertEquals(dataToCheck, result.replace("\\s".toRegex(), ""))
    }

    @Test
    fun loadJSONFromAssetsTrueCase() {
        val result: String =
            InstrumentationRegistry.getInstrumentation().targetContext.loadJSONFromAssets("AppAndroidEn.json")
        assertNotEquals("", result)
    }

    @Test
    fun capitalWord(){
        val testString :String = "we are testing you"
        assertEquals("We Are Testing You",testString.capitalizeWords())
    }
    @Test
    fun capitalWordFalse(){
        val testString :String = "we are testing you"
        assertNotEquals("We Are testing You",testString.capitalizeWords())
    }
}