package com.example.foodhouse.card.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.foodhouse.R
import com.example.foodhouse.ItemClickInterface
import com.example.foodhouse.databinding.ItemListBinding
import com.example.foodhouse.detail.DetailActivity
import com.example.foodhouse.login.LoginActivity
import com.example.foodhouse.model.Food
import java.util.*
import kotlin.collections.ArrayList
import android.content.Intent as Intent1

class FoodAdapter(
    private val foodList: ArrayList<Food>,
    private val listener: ItemClickInterface
):RecyclerView.Adapter<FoodAdapter.FoodHolder>(),Filterable {

    private var foodSearchList= ArrayList<Food>(foodList)
    inner class FoodHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(food: Food) {
            binding.foodName.text = food.name
            binding.foodType.text = food.ftype
            binding.foodPrice.text = food.price
            binding.foodDesc.text = food.desc

            binding.imgItem.load(food.foodImg) {
                crossfade(true)
                placeholder(R.drawable.origin)
                transformations(CircleCropTransformation())
            }
            binding.order.setOnClickListener {
                val intent = Intent1(itemView.context, LoginActivity::class.java)
                itemView.context.startActivity(intent)
            }

            binding.root.setOnClickListener {
                Log.i("ADAPTER", "bind: $adapterPosition")
                listener.onItemClick(bindingAdapterPosition)

//                val intent = Intent1(itemView.context, DetailActivity::class.java)
//                intent.putExtra("name", food.name)
//                intent.putExtra("price", food.price)
//                intent.putExtra("type", food.ftype)
//                intent.putExtra("desc", food.desc)
//                intent.putExtra("foodImg", food.foodImg)
//                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodHolder {
        return FoodHolder(
            ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: FoodHolder, position: Int) {
        holder.bind(foodList[position])
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    override fun getFilter(): Filter {
        return exampleFilter
    }


    private val exampleFilter: Filter = object : Filter() {
        //M
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filteredList: MutableList<Food> = ArrayList()
            if (constraint.isEmpty()) {
                filteredList.addAll(foodSearchList)
            } else {
                val filterPattern =
                    constraint.toString().lowercase(Locale.getDefault()).trim { it <= ' ' }
                //Mg
                for (item in foodSearchList) {
                    if (item.name.lowercase().contains(filterPattern) ||
                        item.id.toString().contains(filterPattern)) {
                        filteredList.add(item)
                        //2
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            foodList.clear()
            foodList.addAll(results.values as Collection<Food>)//2
            notifyDataSetChanged()
        }
    }
}