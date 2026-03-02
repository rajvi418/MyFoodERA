package com.example.myfoodera

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout

class HomeActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var dishRecyclerView: RecyclerView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Profile click
        findViewById<ImageView>(R.id.profileIcon).setOnClickListener {
            Toast.makeText(this, "Profile Clicked", Toast.LENGTH_SHORT).show()
        }

        // Bottom Navigation
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNav.selectedItemId = R.id.nav_home

        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {

                R.id.nav_home -> true

                R.id.nav_fav -> {
                    startActivity(Intent(this, FavoriteActivity::class.java))
                    true
                }

                R.id.nav_cart -> {
                    startActivity(Intent(this, CartActivity::class.java))
                    true
                }

                R.id.nav_settings -> {
                    startActivity(Intent(this, SettingsActivity::class.java))
                    true
                }

                else -> false
            }
        }

        // Tabs
        tabLayout = findViewById(R.id.tabLayout)
        val tabs = listOf("Indian", "Italian", "Chinese", "Mexican", "Japanese", "Dessert")
        tabs.forEach { tabLayout.addTab(tabLayout.newTab().setText(it)) }

        // RecyclerView
        dishRecyclerView = findViewById(R.id.dishRecyclerView)
        dishRecyclerView.layoutManager = LinearLayoutManager(this)

        loadDishesForTab(0)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.position?.let { loadDishesForTab(it) }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    // Dish Model
    data class Dish(
        val name: String,
        val price: String,
        val imageRes: Int,
        val description: String
    )

    private fun loadDishesForTab(tabIndex: Int) {
        val dishes = mutableListOf<Dish>()

        for (i in 1..6) {
            dishes.add(
                Dish(
                    name = "Dish ${tabIndex + 1}-$i",
                    price = "$${5 + i}.99",
                    imageRes = R.drawable.foodera_logo,
                    description = "This is Dish ${tabIndex + 1}-$i description."
                )
            )
        }

        dishRecyclerView.adapter = DishAdapter(dishes)
    }

    // Adapter
    inner class DishAdapter(private val dishList: List<Dish>) :
        RecyclerView.Adapter<DishAdapter.DishViewHolder>() {

        inner class DishViewHolder(itemView: android.view.View) :
            RecyclerView.ViewHolder(itemView) {

            val dishImage: ImageView = itemView.findViewById(R.id.dishImage)
            val dishName: android.widget.TextView = itemView.findViewById(R.id.dishName)
            val dishPrice: android.widget.TextView = itemView.findViewById(R.id.dishPrice)
            val favBtn: ImageView = itemView.findViewById(R.id.favBtn)
            val addToCartBtn: android.widget.Button = itemView.findViewById(R.id.addToCartBtn)
        }

        override fun onCreateViewHolder(
            parent: android.view.ViewGroup,
            viewType: Int
        ): DishViewHolder {

            val view = layoutInflater.inflate(R.layout.item_dish, parent, false)
            return DishViewHolder(view)
        }

        override fun onBindViewHolder(holder: DishViewHolder, position: Int) {

            val dish = dishList[position]

            holder.dishName.text = dish.name
            holder.dishPrice.text = dish.price
            holder.dishImage.setImageResource(dish.imageRes)

            // ❤️ SET ICON STATE
            if (FavoriteManager.isFavorite(this@HomeActivity, dish.name)) {
                holder.favBtn.setImageResource(R.drawable.ic_fav_filled)
            } else {
                holder.favBtn.setImageResource(R.drawable.ic_fav_outline)
            }

            // ❤️ CLICK LOGIC
            holder.favBtn.setOnClickListener {

                if (FavoriteManager.isFavorite(this@HomeActivity, dish.name)) {

                    FavoriteManager.removeFavorite(this@HomeActivity, dish.name)
                    holder.favBtn.setImageResource(R.drawable.ic_fav_outline)
                    Toast.makeText(this@HomeActivity, "Removed from Favorites", Toast.LENGTH_SHORT).show()

                } else {

                    FavoriteManager.addFavorite(this@HomeActivity, dish.name)
                    holder.favBtn.setImageResource(R.drawable.ic_fav_filled)
                    Toast.makeText(this@HomeActivity, "Added to Favorites", Toast.LENGTH_SHORT).show()
                }
            }

            holder.addToCartBtn.setOnClickListener {
                Toast.makeText(
                    this@HomeActivity,
                    "${dish.name} added to Cart",
                    Toast.LENGTH_SHORT
                ).show()
            }

            holder.dishImage.setOnClickListener {
                AlertDialog.Builder(this@HomeActivity)
                    .setTitle(dish.name)
                    .setMessage(dish.description)
                    .setPositiveButton("OK", null)
                    .show()
            }
        }

        override fun getItemCount(): Int {
            return dishList.size
        }
    }
}