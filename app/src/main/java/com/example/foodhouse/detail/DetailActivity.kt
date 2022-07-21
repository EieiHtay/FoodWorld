package com.example.foodhouse.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import coil.load

import com.example.foodhouse.R
import com.example.foodhouse.databinding.ActivityDetailBinding
import com.example.foodhouse.login.LoginActivity


class DetailActivity : AppCompatActivity() {
    private var name : String = ""
    private var price : String = ""
    private var img : Int = 0
    private var desc : String=""
    private var type : String=""

//    private var count : Int = 0
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        img = intent.getIntExtra("img").toString()
        img = intent.getIntExtra("foodImg",0)
        price = intent.getStringExtra("price").toString()
        name = intent.getStringExtra("name").toString()
        desc = intent.getStringExtra("desc").toString()
        type = intent.getStringExtra("type").toString()
//
        binding.foodName.text=name
        binding.foodType.text=type
        binding.foodPrice.text=price
        binding.foodDesc.text=desc
        binding.detailImg.setImageResource(img)
        Toast.makeText(this, "$img", Toast.LENGTH_SHORT).show()
      //  binding.detailImg.load(img)

        binding.order.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

//        findViewById<MaterialButton>(R.id.plus).setOnClickListener{
//            count++
//        }
//        findViewById<MaterialButton>(R.id.minus).setOnClickListener{
//            count--
//        }
//        binding.count.text=count.toString()

    }
}
