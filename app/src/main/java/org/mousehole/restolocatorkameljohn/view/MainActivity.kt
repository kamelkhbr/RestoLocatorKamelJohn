package org.mousehole.restolocatorkameljohn.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.Toolbar
import org.mousehole.restolocatorkameljohn.R

class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Creating the menu file for the main acitvity
        toolbar = findViewById(R.id.main_tool_bar)
        setSupportActionBar(toolbar)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

}