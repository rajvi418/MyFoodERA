package com.example.myfoodera

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class FavoriteActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        recyclerView = findViewById(R.id.favoriteRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        loadFavorites()

        // Profile Click
        findViewById<ImageView>(R.id.profileIcon).setOnClickListener {

            startActivity(Intent(this, UserProfileActivity::class.java))

        }

        // Bottom Navigation
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNav.selectedItemId = R.id.nav_fav

        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {

                R.id.nav_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                    true
                }

                R.id.nav_fav -> true

                R.id.nav_cart -> {
                    startActivity(Intent(this, CartActivity::class.java))
                    finish()
                    true
                }

                R.id.nav_settings -> {
                    startActivity(Intent(this, SettingsActivity::class.java))
                    finish()
                    true
                }

                else -> false
            }
        }
    }

    private fun loadFavorites() {

        val favoriteNames = FavoriteManager.getFavorites(this)

        if (favoriteNames.isEmpty()) {
            Toast.makeText(this, "No Favorites Yet", Toast.LENGTH_SHORT).show()
        }

        // ✅ FIXED: price must be INT not String
        val favoriteDishes = favoriteNames.map {
            HomeActivity.Dish(
                name = it,
                price = 999,   // <-- INT value (example ₹999)
                imageRes = R.drawable.foodera_logo,
                description = "Saved favorite dish"
            )
        }

        recyclerView.adapter = FavoriteAdapter(favoriteDishes)
    }

    inner class FavoriteAdapter(private val dishList: List<HomeActivity.Dish>) :
        RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

        inner class ViewHolder(itemView: android.view.View) :
            RecyclerView.ViewHolder(itemView) {

            val dishName: android.widget.TextView =
                itemView.findViewById(R.id.dishName)

            val dishPrice: android.widget.TextView =
                itemView.findViewById(R.id.dishPrice)

            val dishImage: android.widget.ImageView =
                itemView.findViewById(R.id.dishImage)

            val btnAddToCart: android.widget.Button =
                itemView.findViewById(R.id.btnAddToCart)

            val quantityLayout: android.widget.LinearLayout =
                itemView.findViewById(R.id.quantityLayout)

            val btnPlus: android.widget.ImageButton =
                itemView.findViewById(R.id.btnPlus)

            val btnMinus: android.widget.ImageButton =
                itemView.findViewById(R.id.btnMinus)

            val txtQuantity: android.widget.TextView =
                itemView.findViewById(R.id.txtQuantity)
        }

        override fun onCreateViewHolder(
            parent: android.view.ViewGroup,
            viewType: Int
        ): ViewHolder {

            val view = layoutInflater.inflate(R.layout.item_dish, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            val dish = dishList[position]

            holder.dishName.text = dish.name
            holder.dishPrice.text = "₹${dish.price}"
            holder.dishImage.setImageResource(dish.imageRes)

            var quantity = 0

            // ADD BUTTON CLICK
            holder.btnAddToCart.setOnClickListener {

                quantity = 1
                holder.txtQuantity.text = quantity.toString()

                holder.btnAddToCart.visibility = android.view.View.GONE
                holder.quantityLayout.visibility = android.view.View.VISIBLE

                val cartItem = CartItem(
                    name = dish.name,
                    price = dish.price,
                    imageRes = dish.imageRes,
                    quantity = quantity
                )

                CartManager.addToCart(this@FavoriteActivity, cartItem)

                android.widget.Toast.makeText(
                    this@FavoriteActivity,
                    "${dish.name} added to cart",
                    android.widget.Toast.LENGTH_SHORT
                ).show()
            }

            // PLUS BUTTON
            holder.btnPlus.setOnClickListener {

                quantity++
                holder.txtQuantity.text = quantity.toString()

                val cartItem = CartItem(
                    name = dish.name,
                    price = dish.price,
                    imageRes = dish.imageRes,
                    quantity = 1
                )

                CartManager.addToCart(this@FavoriteActivity, cartItem)
            }

            // MINUS BUTTON
            holder.btnMinus.setOnClickListener {

                if (quantity > 1) {
                    quantity--
                    holder.txtQuantity.text = quantity.toString()
                } else {
                    quantity = 0
                    holder.quantityLayout.visibility = android.view.View.GONE
                    holder.btnAddToCart.visibility = android.view.View.VISIBLE
                }
            }
        }

        override fun getItemCount(): Int {
            return dishList.size
        }
    }
}