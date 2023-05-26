package com.app.fetchTestApp.viewmodels

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

@ExperimentalCoroutinesApi
class MainActivityViewModelTest {

    lateinit var mViewModel: MainActivityViewModel

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @Before
    fun setup() {
        mViewModel = spy(MainActivityViewModel())
    }

    @Test
    fun isLoaderVisible() {
        mViewModel.getData()
        coroutinesTestRule.testDispatcher.runBlockingTest {
            verify(mViewModel, atLeastOnce()).toggleLoader(true)
        }
    }



}