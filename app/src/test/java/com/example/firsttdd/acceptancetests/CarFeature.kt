package com.example.firsttdd.acceptancetests

import com.example.firsttdd.Car
import com.example.firsttdd.Engine
import com.example.firsttdd.utils.MainCoroutineScopeRule
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceTimeBy
import org.junit.Rule
import org.junit.Test

class CarFeature {
    private var engine:Engine= Engine()
    private var car:Car=Car(engine ,6.0)

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineTestRule= MainCoroutineScopeRule()

    @Test
    fun carIsLoosingFuelWhenItsTurnOn(){
        car.turnOn()
        assertEquals(5.5, car.fuel)
    }

    @Test
    fun carIsTurningOnItsEngineIncreasesTheTemp(){
        car.turnOn()
        coroutineTestRule.advanceTimeBy(2000)
        assertEquals(25, car.engine.temperature)

        coroutineTestRule.advanceTimeBy(2000)
        assertEquals(45, car.engine.temperature)

        coroutineTestRule.advanceTimeBy(2000)
        assertEquals(95, car.engine.temperature)

        assertTrue(car.engine.isTurnedOn)
    }
}