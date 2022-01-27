package com.example.firsttdd.unittests

import com.example.firsttdd.Car
import com.example.firsttdd.Engine
import com.example.firsttdd.utils.MainCoroutineScopeRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class CarShould {

    private val engine: Engine = mock()
    private val car = Car(engine, 5.0)

    init {
        runBlocking {
            whenever(engine.turnOn()).thenReturn(flow {
                delay(2000)
                emit(25)

                delay(2000)
                emit(45)

                delay(2000)
                emit(95)

            })
        }
    }

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineTestRule = MainCoroutineScopeRule()

    @Test
    fun loseFuelWhenItTurnsOn() = runTest {
        car.turnOn()
        assertEquals(4.5, car.fuel)
    }

    @Test
    fun turnOnItsEngine() = runTest {
        car.turnOn()
        verify(engine, times(1)).turnOn()
    }
}