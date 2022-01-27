package com.example.firsttdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firsttdd.playlist.PlayListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        /*    val engine=Engine()
            val car=Car(engine, 50.0)
            car.turnOn()*/
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, PlayListFragment.newInstance())
                .commit()
        }
    }
}