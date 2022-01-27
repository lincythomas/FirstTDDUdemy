package com.example.firsttdd

import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class Engine(
    var temperature: Int = 0,
    var isTurnedOn: Boolean = false
) {

    suspend fun turnOn():Flow<Int> {
        isTurnedOn = true



        return flow {
            delay(2000)
            temperature = 25
            emit(temperature)

            delay(2000)
            temperature=45
            emit(temperature)

            delay(2000)
            temperature=95
            emit(temperature)

            Log.d("COURSE","Engine has been turned on")

        }



    }
}