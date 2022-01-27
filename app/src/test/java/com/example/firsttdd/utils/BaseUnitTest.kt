package com.example.firsttdd.utils

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule

open class BaseUnitTest {
    @get:Rule
    var coroutineTestRule = MainCoroutineScopeRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule() // allows the execution of
                                // livedata to happen instantily


}