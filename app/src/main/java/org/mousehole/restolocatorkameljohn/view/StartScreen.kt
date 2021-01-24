package org.mousehole.restolocatorkameljohn.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout
import org.mousehole.restolocatorkameljohn.R

class StartScreen : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_screen)
        val radius = findViewById<TextInputLayout>(R.id.textInputLayout)
        val searchbtn : Button = findViewById(R.id.search_btn)

        searchbtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("radius", radius.toString())
            startActivity(intent)
        }



    }
}