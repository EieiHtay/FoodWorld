package com.example.foodhouse.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.PhoneNumberUtils
import androidx.core.widget.doAfterTextChanged
import com.example.foodhouse.util.AppSharedPreference
import com.example.foodhouse.card.ListActivity
import com.example.foodhouse.databinding.ActivityRegisterBinding
import com.example.foodhouse.login.LoginActivity

import com.google.android.material.snackbar.Snackbar
import com.mhs.mhs_tutorial_login.util.PatternUtils

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding

    // research how to work lazy keyword
    private val sharedPref : AppSharedPreference by lazy {
        AppSharedPreference(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkValidate()
        onClick()

    }

    private fun onClick(){
        binding.register.setOnClickListener {
            if(binding.titConfirm.isErrorEnabled || binding.titPwd.isErrorEnabled || binding.titPhone.isErrorEnabled
                || binding.name.text.toString().isNullOrBlank() || !binding.chkAgree.isChecked
                || binding.phone.text.isNullOrBlank() || binding.pwd.text.isNullOrBlank()
                || binding.pwd.text.isNullOrBlank()){
                Snackbar.make(it,"Fix Correct Data",Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else {
                sharedPref.save("IsLogin",true)
                val intent = Intent(this, ListActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun checkValidate() {
        binding.register.setOnClickListener {
            binding.phone.doAfterTextChanged { s->
                if(!s.isNullOrEmpty()) {
                    if (PhoneNumberUtils.isGlobalPhoneNumber(s.toString())){
                        binding.titPhone.isErrorEnabled = false
                    }else{
                        binding.titPhone.error = "Enter Correct Number"
                        return@doAfterTextChanged
                    }
                }
            }
            binding.pwd.doAfterTextChanged { s->
                if(!s.isNullOrEmpty()){
                    val password = PatternUtils.PASSWORD_PATTERN.matcher(s)
                    if(!password.matches()){
                        val error = checkError(s.toString())
                        binding.titPwd.error = error
                        return@doAfterTextChanged
                    }else{
                        binding.titPwd.isErrorEnabled =false
                    }
                }
            }
            binding.confirm.doAfterTextChanged { s->
                if(binding.pwd.text.toString().isNullOrBlank()){
                    binding.titConfirm.error = "Enter Password First"
                    return@doAfterTextChanged
                }else{
                    if(binding.pwd.text.toString() == s.toString()){
                        binding.titConfirm.isErrorEnabled = false
                    }else{
                        binding.titConfirm.error = "Password Not Match"
                        return@doAfterTextChanged
                    }
                }
            }
        }

        binding.signin.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    private fun checkError(password: String):String {
        return when {
            /* Rule 1 */
            !password.contains(Regex("[A-Z]")) -> "Password must contain one capital letter"
            /* Rule 2 */
            !password.contains(Regex("[0-9]")) -> "Password must contain one digit"
            /* Rule 3, not counting space as special character */
            !password.contains(Regex("[^a-zA-Z0-9 ]")) -> "Password must contain one special character"

            !password.contains(Regex("\\S+\$"))-> "Password not allowed to add space"
            else -> "Password is too short."
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}