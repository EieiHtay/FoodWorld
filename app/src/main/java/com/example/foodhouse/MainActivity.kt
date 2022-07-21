package com.example.foodhouse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.foodhouse.card.ListActivity
import com.example.foodhouse.login.LoginActivity
import com.example.foodhouse.register.RegisterActivity
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<MaterialButton>(R.id.fmenu).setOnClickListener{
            val intent = Intent (this,ListActivity::class.java)
            startActivity(intent)

//            Toast.makeText(this@MainActivity,"Welcome",Toast.LENGTH_SHORT).show()

        }
    }
}