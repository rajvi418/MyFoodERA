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
    private lateinit var adapter: FavoriteAdapter
    private var favoriteDishes = mutableListOf<HomeActivity.Dish>()

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

        // Map favorite names to actual dishes from HomeActivity
        val allDishes = getAllDishes() // Helper function below
        favoriteDishes = allDishes.filter { favoriteNames.contains(it.name) }.toMutableList()

        adapter = FavoriteAdapter(favoriteDishes)
        recyclerView.adapter = adapter
    }

    // Helper: returns all dishes from HomeActivity tabs
    private fun getAllDishes(): List<HomeActivity.Dish> {
        return listOf(
            // Indian
            HomeActivity.Dish(
                "Samosa",
                600,
                R.drawable.samosa,
                "All-purpose flour, potatoes, peas, spices, oil for frying"
            ),
            HomeActivity.Dish(
                "Pav Bhaji",
                200,
                R.drawable.pav_bhaji,
                "Mixed vegetables, onion, tomato, pav bhaji masala, butter, pav bread"
            ),
            HomeActivity.Dish(
                "Masala Dosa",
                200,
                R.drawable.dosa,
                "Rice & urad dal for batter, potatoes, onion, spices, oil"
            ),
            HomeActivity.Dish(
                "Veg Pulao",
                350,
                R.drawable.veg_pulao,
                "Rice, mixed vegetables, onion, ginger, garlic, spices, oil/ghee"
            ),
            HomeActivity.Dish(
                "Paneer Butter Masala",
                350,
                R.drawable.paneer_butter_masala,
                "Paneer, tomato, onion, garlic, butter/cream, spices"
            ),
            HomeActivity.Dish(
                "Chole Bhature",
                350,
                R.drawable.chole_bhature,
                "Chickpeas, onion, garlic, ginger, spices, flour, yeast, yogurt, oil"
            ),
            HomeActivity.Dish(
                "Gulab Jamun",
                350,
                R.drawable.gulab_jamun,
                "Khoya, flour, sugar, water, cardamom, ghee/oil"
            ),
            // Italian
            HomeActivity.Dish(
                "Pasta alla Norma",
                350,
                R.drawable.pasta_alla_norma,
                "Pasta, eggplant, tomato sauce, garlic, olive oil, ricotta, basil"
            ),
            HomeActivity.Dish(
                "Pizza Margherita",
                500,
                R.drawable.pizza_margherita,
                "Pizza dough, tomato sauce, mozzarella, olive oil, basil, salt"
            ),
            HomeActivity.Dish(
                "Parmigiana di Melanzane",
                450,
                R.drawable.parmigiana_di_melanzane,
                "Eggplant, tomato sauce, mozzarella, parmesan, olive oil, basil"
            ),
            HomeActivity.Dish(
                "Cacio e Pepe",
                220,
                R.drawable.cacio_e_pepe,
                "Pasta, pecorino romano, black pepper"
            ),
            HomeActivity.Dish(
                "Pasta e Ceci",
                220,
                R.drawable.pasta_e_ceci,
                "Pasta, chickpeas, garlic, olive oil, rosemary/thyme, salt & pepper"
            ),
            HomeActivity.Dish(
                "Caprese Salad",
                220,
                R.drawable.caprese_salad,
                "Tomatoes, mozzarella, basil, olive oil, balsamic vinegar, salt & pepper"
            ),
            // Chinese
            HomeActivity.Dish(
                "Buddha's Delight",
                220,
                R.drawable.luohan_zhai,
                "Cabbage, carrots, mushrooms, tofu, snow peas, soy sauce"
            ),
            HomeActivity.Dish(
                "Fried Vegetables",
                220,
                R.drawable.stir_fried_vegetables_in_garlic_sauce,
                "Broccoli, carrots, bell peppers, snow peas, garlic, soy sauce"
            ),
            HomeActivity.Dish(
                "Mapo Tofu",
                220,
                R.drawable.mapo_tofu,
                "Tofu, optional pork/mushrooms, doubanjiang, garlic, ginger, green onions, soy sauce"
            ),
            HomeActivity.Dish(
                "Gravy Manchurian",
                220,
                R.drawable.gravy_manchurian,
                "Cauliflower, flour, cornflour, garlic, ginger, chilies, soy sauce, ketchup"
            ),
            HomeActivity.Dish(
                "Dry Chilli Potato",
                220,
                R.drawable.dry_chilli_potato,
                "Potatoes, garlic, ginger, soy sauce, cornstarch, vinegar, chili sauce"
            ),
            HomeActivity.Dish(
                "Vegetable Spring Rolls",
                300,
                R.drawable.spring_rolls,
                "Cabbage, carrots, bean sprouts, garlic, ginger, soy sauce"
            ),
            // Add other tabs similarly...
        )
    }

    inner class FavoriteAdapter(private val dishList: MutableList<HomeActivity.Dish>) :
        RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

        inner class ViewHolder(itemView: android.view.View) :
            RecyclerView.ViewHolder(itemView) {

            val dishName: android.widget.TextView = itemView.findViewById(R.id.dishName)
            val dishPrice: android.widget.TextView = itemView.findViewById(R.id.dishPrice)
            val dishImage: ImageView = itemView.findViewById(R.id.dishImage)
            val btnAddToCart: android.widget.Button = itemView.findViewById(R.id.btnAddToCart)
            val favBtn: ImageView = itemView.findViewById(R.id.favBtn)
            val quantityLayout: android.widget.LinearLayout =
                itemView.findViewById(R.id.quantityLayout)
            val btnPlus: android.widget.ImageButton = itemView.findViewById(R.id.btnPlus)
            val btnMinus: android.widget.ImageButton = itemView.findViewById(R.id.btnMinus)
            val txtQuantity: android.widget.TextView = itemView.findViewById(R.id.txtQuantity)
        }

        override fun onCreateViewHolder(parent: android.view.ViewGroup, viewType: Int): ViewHolder {
            val view = layoutInflater.inflate(R.layout.item_dish, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val dish = dishList[position]

            holder.dishName.text = dish.name
            holder.dishPrice.text = "₹${dish.price}"
            holder.dishImage.setImageResource(dish.imageRes)

            var quantity = 0

            // Favorite Button: remove from favorites
            holder.favBtn.setImageResource(R.drawable.ic_fav_filled)
            holder.favBtn.setOnClickListener {
                FavoriteManager.removeFavorite(this@FavoriteActivity, dish.name)
                dishList.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, dishList.size)
                Toast.makeText(this@FavoriteActivity, "${dish.name} removed", Toast.LENGTH_SHORT)
                    .show()
            }

            // Add to Cart
            holder.btnAddToCart.setOnClickListener {
                quantity = 1
                holder.txtQuantity.text = quantity.toString()
                holder.btnAddToCart.visibility = android.view.View.GONE
                holder.quantityLayout.visibility = android.view.View.VISIBLE

                val cartItem = CartItem(dish.name, dish.price, dish.imageRes, quantity)
                CartManager.addToCart(this@FavoriteActivity, cartItem)
                Toast.makeText(
                    this@FavoriteActivity,
                    "${dish.name} added to cart",
                    Toast.LENGTH_SHORT
                ).show()
            }

            // Plus Button
            holder.btnPlus.setOnClickListener {
                quantity++
                holder.txtQuantity.text = quantity.toString()
                CartManager.addToCart(
                    this@FavoriteActivity,
                    CartItem(dish.name, dish.price, dish.imageRes, 1)
                )
            }

            // Minus Button
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

        override fun getItemCount(): Int = dishList.size
    }

    override fun onResume() {
        super.onResume()
        loadFavorites()
    }
}