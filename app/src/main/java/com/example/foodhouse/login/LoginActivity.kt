package com.example.foodhouse.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import com.example.foodhouse.util.AppSharedPreference
import com.example.foodhouse.card.ListActivity
import com.example.foodhouse.databinding.ActivityLoginBinding
import com.example.foodhouse.register.RegisterActivity
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPref : AppSharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPref = AppSharedPreference(this)
        val isLogin = sharedPref.getValueBoolean("IsLogin",false)
        if (isLogin){
            val intent = Intent(this@LoginActivity,ListActivity::class.java)
            startActivity(intent)
            finish()
        }

        checkValidate()
        binding.login.setOnClickListener {
            val phoneNumber = binding.phone.text.toString()
            val password = binding.pwd.text.toString()
            if(phoneNumber.isNullOrEmpty()){
                binding.titPhone.error = "Enter Phone Number"
                binding.titPhone.isErrorEnabled=true
                return@setOnClickListener
            }
            if(password.isNullOrEmpty()){
                binding.titPwd.error="Enter Password"
                binding.titPwd.isErrorEnabled = true
                return@setOnClickListener
            }
            if(phoneNumber.length<8){
                binding.titPhone.error = "Enter Correct Phone Number"
                binding.titPhone.isErrorEnabled=true
                return@setOnClickListener
            }
            if(phoneNumber == "09795014119" && password == "abc123"){
                sharedPref.save("IsLogin",true)
                Toast.makeText(this@LoginActivity, "Welcome", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@LoginActivity,ListActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                Snackbar.make(binding.root,"Enter Correct Phone Number and Correct Password",
                    Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

        }

        binding.registerNow.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun checkValidate() {
        binding.phone.doAfterTextChanged { s->
            if(!s.isNullOrEmpty()){
                if(s.length >8){
                    binding.titPhone.isErrorEnabled = false
                }
            }
        }
        binding.pwd.doAfterTextChanged { s->
            if(!s.isNullOrEmpty()){
                binding.titPwd.isErrorEnabled = false
            }
        }
    }
}