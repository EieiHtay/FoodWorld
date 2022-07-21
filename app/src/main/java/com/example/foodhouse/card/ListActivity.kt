package com.example.foodhouse.card

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodhouse.ItemClickInterface
import com.example.foodhouse.R
import com.example.foodhouse.card.adapter.FoodAdapter
import com.example.foodhouse.databinding.ActivityListBinding
import com.example.foodhouse.detail.DetailActivity
import com.example.foodhouse.login.LoginActivity
import com.example.foodhouse.util.TempData
import com.example.foodhouse.model.Food

class ListActivity : AppCompatActivity(), ItemClickInterface {
    private lateinit var binding: ActivityListBinding
    private lateinit var foodAdapter: FoodAdapter

    private var foodData : Food = Food()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id : Int = foodData.id ?: 0
        foodAdapter = FoodAdapter(TempData.foodList,this)

        binding.sampleEditText.doAfterTextChanged {
            foodAdapter.filter.filter(it.toString()) //M

        }
//        binding.fList.apply {
//            setHasFixedSize(true)
//            layoutManager = GridLayoutManager(this@ListActivity,1)
//            adapter = foodAdapter
//        }

        binding.fList.also {
            it.setHasFixedSize(true)
            it.layoutManager=LinearLayoutManager(this)
            it.adapter=foodAdapter
        }

        binding.root.setOnClickListener{
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.item_menu,menu)
        val searchItem : MenuItem ?= menu?.findItem(R.id.app_bar_search)
        val searchView : SearchView = searchItem?.actionView as SearchView
        searchView.queryHint = "Search something"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query : String?):Boolean{
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClick(position:Int){
        val food = TempData.foodList[position]
        Log.i("GETDATA", "bind: $food")
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("name",food.name)
        intent.putExtra("price",food.price)
        intent.putExtra("type",food.ftype)
        intent.putExtra("desc",food.desc)
        intent.putExtra("foodImg",food.foodImg)
        startActivity(intent)
    }


}
