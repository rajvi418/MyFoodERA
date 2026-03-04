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

        // Profile Click
        findViewById<ImageView>(R.id.profileIcon).setOnClickListener {

            startActivity(Intent(this, UserProfileActivity::class.java))

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

    // ✅ Correct Dish Model (FIXED)
    data class Dish(
        val name: String,
        val price: Int,
        val imageRes: Int,
        val description: String
    )

    private fun loadDishesForTab(tabIndex: Int) {

        val dishes = mutableListOf<Dish>()

        when (tabIndex) {
            0 -> { // Indian
                dishes.add(
                    Dish(
                        "Samosa", 600, R.drawable.samosa, "All-purpose flour (for dough)\n" +
                                "\n" +
                                "\u2022 Potatoes\n" +
                                "\n" +
                                "\u2022 Peas\n" +
                                "\n" +
                                "\u2022 Spices: cumin, coriander, chili, garam masala\n" +
                                "\n" +
                                "\u2022 Oil for frying"
                    )
                )
                dishes.add(
                    Dish(
                        "Pav Bhaji",
                        200,
                        R.drawable.pav_bhaji,
                        "Mixed vegetables (potato, peas, carrots, bell pepper)\n" +
                                "\n" +
                                "\u2022 Onion & tomato\n" +
                                "\n" +
                                "\u2022 Pav bhaji masala\n" +
                                "\n" +
                                "\u2022 Butter\n" +
                                "\n" +
                                "\u2022 Pav bread"
                    )
                )
                dishes.add(
                    Dish(
                        "Masala Dosa", 200, R.drawable.dosa, "Rice & urad dal (for dosa batter)\n" +
                                "\n" +
                                "\u2022 Potatoes\n" +
                                "\n" +
                                "\u2022 Onion\n" +
                                "\n" +
                                "\u2022 Mustard seeds\n" +
                                "\n" +
                                "\u2022 Turmeric, chili, curry leaves\n" +
                                "\n" +
                                "\u2022 Oil"
                    )
                )
                dishes.add(
                    Dish(
                        "Veg Pulao", 350, R.drawable.veg_pulao, "\u2022 Rice (basmati)\n" +
                                "\n" +
                                "\u2022 Mixed vegetables (peas, carrots, beans)\n" +
                                "\n" +
                                "\u2022 Onion\n" +
                                "\n" +
                                "\u2022 Ginger, garlic\n" +
                                "\n" +
                                "\u2022 Spices: cumin, bay leaf, cardamom, cinnamon\n" +
                                "\n" +
                                "\u2022 Oil or ghee"
                    )
                )
                dishes.add(
                    Dish(
                        "Paneer Butter Masala",
                        350,
                        R.drawable.paneer_butter_masala,
                        "\u2022 Paneer (Indian cottage cheese)\n" +
                                "\n" +
                                "\u2022 Tomatoes\n" +
                                "\n" +
                                "\u2022 Onion\n" +
                                "\n" +
                                "\u2022 Garlic, ginger\n" +
                                "\n" +
                                "\u2022 Butter/cream\n" +
                                "\n" +
                                "\u2022 Spices: garam masala, turmeric, chili powder, cumin"
                    )
                )
                dishes.add(
                    Dish(
                        "Chole Bhature", 350, R.drawable.chole_bhature, "\u2022  Chickpeas\n" +
                                "\n" +
                                "\u2022 Onion, garlic, ginger\n" +
                                "\n" +
                                "\u2022 Tomatoes\n" +
                                "\n" +
                                "\u2022 Spices: cumin, coriander, turmeric, garam masala, chili\n" +
                                "\n" +
                                "\u2022 Flour, yeast, yogurt (for bhature)\n" +
                                "\n" +
                                "\u2022 Oil"
                    )
                )
                dishes.add(
                    Dish(
                        "Gulab Jamun", 350, R.drawable.gulab_jamun, "\u2022 Khoya (milk solids)\n" +
                                "\n" +
                                "\u2022 All-purpose flour\n" +
                                "\n" +
                                "\u2022 Sugar\n" +
                                "\n" +
                                "\u2022 Water\n" +
                                "\n" +
                                "\u2022 Cardamom\n" +
                                "\n" +
                                "\u2022 Ghee or oil for frying"
                    )
                )


            }

            1 -> { // Italian
                dishes.add(
                    Dish(
                        "Pasta alla Norma",
                        350,
                        R.drawable.pasta_alla_norma,
                        "\u2022 Pasta (typically rigatoni or spaghetti)\n" +
                                "\n" +
                                "\u2022 Eggplant (aubergine)\n" +
                                "\n" +
                                "\u2022 Tomatoes or tomato sauce\n" +
                                "\n" +
                                "\u2022 Garlic\n" +
                                "\n" +
                                "\u2022 Olive oil\n" +
                                "\n" +
                                "\u2022 Ricotta salata cheese\n" +
                                "\n" +
                                "\u2022 Basil"
                    )
                )
                dishes.add(
                    Dish(
                        "Pizza Margherita",
                        500,
                        R.drawable.pizza_margherita,
                        "\u2022 Pizza dough\n" +
                                "\n" +
                                "\u2022 Tomato sauce\n" +
                                "\n" +
                                "\u2022 Fresh mozzarella\n" +
                                "\n" +
                                "\u2022 Olive oil\n" +
                                "\n" +
                                "\u2022 Fresh basil\n" +
                                "\n" +
                                "\u2022 Salt"
                    )
                )
                dishes.add(
                    Dish(
                        "Parmigiana di Melanzane (Eggplant Parmesan)",
                        450,
                        R.drawable.parmigiana_di_melanzane,
                        "\u2022 Eggplant\n" +
                                "\n" +
                                "\u2022 Tomato sauce\n" +
                                "\n" +
                                "\u2022 Mozzarella cheese\n" +
                                "\n" +
                                "\u2022 Parmesan cheese\n" +
                                "\n" +
                                "\u2022 Olive oil\n" +
                                "\n" +
                                "\u2022 Basil"
                    )
                )
                dishes.add(
                    Dish(
                        "Cacio e Pepe",
                        220,
                        R.drawable.cacio_e_pepe,
                        "\u2022 Pasta (spaghetti or tonnarelli)\n" +
                                "\n" +
                                "\u2022 Pecorino Romano cheese\n" +
                                "\n" +
                                "\u2022 Black pepper"
                    )
                )
                dishes.add(
                    Dish(
                        "Pasta e Ceci", 220, R.drawable.pasta_e_ceci, "\u2022  Pasta\n" +
                                "\n" +
                                "\u2022 Chickpeas\n" +
                                "\n" +
                                "\u2022 Garlic\n" +
                                "\n" +
                                "\u2022 Olive oil\n" +
                                "\n" +
                                "\u2022 Rosemary or thyme\n" +
                                "\n" +
                                "\u2022 Salt & pepper"
                    )
                )
                dishes.add(
                    Dish(
                        "Caprese Salad", 220, R.drawable.caprese_salad, "\u2022 Tomatoes\n" +
                                "\n" +
                                "\u2022 Fresh mozzarella\n" +
                                "\n" +
                                "\u2022 Basil\n" +
                                "\n" +
                                "\u2022 Olive oil\n" +
                                "\n" +
                                "\u2022 Balsamic vinegar (optional)\n" +
                                "\n" +
                                "\u2022 Salt & pepper--"
                    )
                )

            }

            2 -> { // Chinese
                dishes.add(
                    Dish(
                        "Buddha's Delight (Lo Han Jai)",
                        220,
                        R.drawable.luohan_zhai,
                        "\u2022 Napa cabbage\n" +
                                "\n" +
                                "\u2022 Carrots\n" +
                                "\n" +
                                "\u2022 Mushrooms (shiitake, black fungus)\n" +
                                "\n" +
                                "\u2022 Tofu\n" +
                                "\n" +
                                "\u2022 Snow peas\n" +
                                "\n" +
                                "\u2022 Soy sauce, garlic, ginger"
                    )
                )
                dishes.add(
                    Dish(
                        "Fried Vegetables",
                        220,
                        R.drawable.stir_fried_vegetables_in_garlic_sauce,
                        "\u2022 Broccoli, \u2022 carrots, \u2022 bell peppers, \u2022 snow peas\n" +
                                "\n" +
                                "\u2022 Garlic\n" +
                                "\n" +
                                "\u2022 Soy sauce\n" +
                                "\n" +
                                "\u2022 Sesame oil\n" +
                                "\n" +
                                "\u2022 Cornstarch"
                    )
                )
                dishes.add(
                    Dish(
                        "Mapo Tofu",
                        220,
                        R.drawable.mapo_tofu,
                        "\u2022 Tofu (soft/firmer depending on preference)\n" +
                                "\n" +
                                "\u2022 Ground pork or mushrooms (optional for vegetarian)\n" +
                                "\n" +
                                "\u2022 Doubanjiang (spicy bean paste)\n" +
                                "\n" +
                                "\u2022 Garlic, ginger, green onions\n" +
                                "\n" +
                                "\u2022 Soy sauce\n" +
                                "\n" +
                                "\u2022 Sichuan peppercorns"
                    )
                )
                dishes.add(
                    Dish(
                        "Gravy Manchurian",
                        220,
                        R.drawable.gravy_manchurian,
                        "\u2022 Cauliflower or mixed veggies (for balls)\n" +
                                "\n" +
                                "\u2022 All-purpose flour, cornflour\n" +
                                "\n" +
                                "\u2022 Garlic, ginger, green chilies\n" +
                                "\n" +
                                "\u2022 Soy sauce, tomato ketchup\n" +
                                "\n" +
                                "\u2022 Spring onions"
                    )
                )
                dishes.add(
                    Dish(
                        "Dry Chilli Potato",
                        220,
                        R.drawable.dry_chilli_potato,
                        "\u2022 Potatoes\n" +
                                "\n" +
                                "\u2022 Garlic, ginger, green chilies\n" +
                                "\n" +
                                "\u2022 Soy sauce\n" +
                                "\n" +
                                "\u2022 Cornstarch\n" +
                                "\n" +
                                "\u2022 Vinegar, chili sauce"
                    )
                )
                dishes.add(
                    Dish(
                        "Vegetable Spring Rolls",
                        300,
                        R.drawable.spring_rolls,
                        "\u2022 Spring roll wrappers\n" +
                                "\n" +
                                "\u2022 Cabbage" +
                                "\n" +
                                "\u2022 Carrots" + "\n" + "\u2022 Bean sprouts\n" +
                                "\n" +
                                "\u2022 Garlic, ginger\n" +
                                "\n" +
                                "\u2022 Soy sauce"
                    )
                )
            }

            3 -> { // Mexican
                dishes.add(
                    Dish(
                        "Black Bean Tacos", 150, R.drawable.black_bean_tacos, "\u2022 Tortillas\n" +
                                "\n" +
                                "\u2022 Black beans\n" +
                                "\n" +
                                "\u2022 Corn\n" +
                                "\n" +
                                "\u2022 Onion, bell peppers\n" +
                                "\n" +
                                "\u2022 Cumin, chili powder\n" +
                                "\n" +
                                "\u2022 Cheese, salsa, avocado (optional)"
                    )
                )
                dishes.add(
                    Dish(
                        "Enchiladas", 200, R.drawable.enchiladas, "\u2022 Corn tortillas\n" +
                                "\n" +
                                "\u2022 Cheese\n" +
                                "\n" +
                                "\u2022 Black beans or chicken\n" +
                                "\n" +
                                "\u2022 Enchilada sauce (tomato or chili based)\n" +
                                "\n" +
                                "\u2022 Onion, garlic"
                    )
                )
                dishes.add(
                    Dish(
                        "Fajitas", 120, R.drawable.fajitas, "\u2022 Bell peppers, onions\n" +
                                "\n" +
                                "\u2022 Chicken or tofu\n" +
                                "\n" +
                                "\u2022 Tortillas\n" +
                                "\n" +
                                "\u2022 Spices: cumin, chili powder, paprika\n" +
                                "\n" +
                                "\u2022 Lime, garlic"
                    )
                )
                dishes.add(
                    Dish(
                        "Burrito Bowls", 200, R.drawable.burrito_bowls, "\u2022 Rice or quinoa\n" +
                                "\n" +
                                "\u2022 Beans (black or pinto)\n" +
                                "\n" +
                                "\u2022 Corn\n" +
                                "\n" +
                                "\u2022 Salsa, guacamole\n" +
                                "\n" +
                                "\u2022 Lettuce, cheese, sour cream"
                    )
                )
                dishes.add(
                    Dish(
                        "Tamale", 200, R.drawable.tamales, "\u2022 Masa harina (corn dough)\n" +
                                "\n" +
                                "\u2022 Corn husks\n" +
                                "\n" +
                                "\u2022 Filling: beans, cheese, or meat\n" +
                                "\n" +
                                "\u2022 Lard or oil"
                    )
                )
                dishes.add(
                    Dish(
                        "Calabacitas",
                        200,
                        R.drawable.calabacitas,
                        "\u2022 Zucchini (calabacitas)\n" +
                                "\n" +
                                "\u2022 Corn\n" +
                                "\n" +
                                "\u2022 Tomatoes, onion\n" +
                                "\n" +
                                "\u2022 Garlic\n" +
                                "\n" +
                                "\u2022 Cheese (optional)\n" +
                                "\n" +
                                "\u2022 Chiles"
                    )
                )

            }

            4 -> { // Japanese
                dishes.add(
                    Dish(
                        "Nasu Dengaku", 400, R.drawable.nasu_dengaku, "\u2022 Eggplant\n" +
                                "\n" +
                                "\u2022 Miso paste (sweetened)\n" +
                                "\n" +
                                "\u2022 Mirin\n" +
                                "\n" +
                                "\u2022 Sugar\n" +
                                "\n" +
                                "\u2022 Sake"
                    )
                )
                dishes.add(
                    Dish(
                        "Vegetable Tempura",
                        350,
                        R.drawable.vegetable_tempura,
                        "\u2022 Vegetables: sweet potato, zucchini, eggplant, bell peppers\n" +
                                "\n" +
                                "\u2022 Tempura batter (flour, ice-cold water, egg)\n" +
                                "\n" +
                                "\u2022 Oil for frying"
                    )
                )
                dishes.add(
                    Dish(
                        "Agedashi Tofu", 300, R.drawable.agedashi_tofu, "\u2022 Soft tofu\n" +
                                "\n" +
                                "\u2022 Potato starch or cornstarch\n" +
                                "\n" +
                                "\u2022 Dashi stock\n" +
                                "\n" +
                                "\u2022 Soy sauce, mirin\n" +
                                "\n" +
                                "\u2022 Green onions, grated daikon"
                    )
                )
                dishes.add(
                    Dish(
                        "Vegetable Curry (Japanese)",
                        400,
                        R.drawable.vegetable_curry,
                        "\u2022 Carrots, potatoes, onions, pumpkin, eggplant\n" +
                                "\n" +
                                "\u2022 Japanese curry roux\n" +
                                "\n" +
                                "\u2022 Soy sauce, garlic, ginger"
                    )
                )
                dishes.add(
                    Dish(
                        "Zaru Soba / Udon",
                        350,
                        R.drawable.zaru_soba,
                        "\u2022 Soba or udon noodles\n" +
                                "\n" +
                                "\u2022 Dashi-based dipping sauce (soy sauce, mirin, dashi)\n" +
                                "\n" +
                                "\u2022 Nori\n" +
                                "\n" +
                                "\u2022 Wasabi, green onions"
                    )
                )
                dishes.add(
                    Dish(
                        "Gyoza", 300, R.drawable.gyoza, "\u2022 Gyoza wrappers\n" +
                                "\n" +
                                "\u2022 Cabbage\n" +
                                "\n" +
                                "\u2022 Garlic, ginger, green onions\n" +
                                "\n" +
                                "\u2022 Ground pork or mushrooms\n" +
                                "\n" +
                                "\u2022 Soy sauce, sesame oil"
                    )
                )
            }

            5 -> { // Dessert
                dishes.add(
                    Dish(
                        "Sago Mango Dessert",
                        250,
                        R.drawable.sago_mango_dessert,
                        "\u2022 Sago pearls\n" +
                                "\n" +
                                "\u2022 Mango puree\n" +
                                "\n" +
                                "\u2022 Sugar\n" +
                                "\n" +
                                "\u2022 Coconut milk or cream"
                    )
                )
                dishes.add(
                    Dish(
                        "Banana and Chocolate Parfait",
                        150,
                        R.drawable.parfait,
                        "\u2022 Bananas\n" +
                                "\n" +
                                "\u2022 Chocolate (syrup or chips)\n" +
                                "\n" +
                                "\u2022 Whipped cream\n" +
                                "\n" +
                                "\u2022 Biscuit or granola"
                    )
                )
                dishes.add(
                    Dish(
                        "Chocolate Pudding Mousse with Avocado",
                        180,
                        R.drawable.pudding_mousse,
                        "\u2022 Ripe avocado\n" +
                                "\n" +
                                "\u2022 Cocoa powder\n" +
                                "\n" +
                                "\u2022 Sweetener (sugar, honey, or maple syrup)\n" +
                                "\n" +
                                "\u2022 Vanilla extractRipe avocado\n" +
                                "\n" +
                                "\u2022 Cocoa powder\n" +
                                "\n" +
                                "\u2022 Sweetener (sugar, honey, or maple syrup)\n" +
                                "\n" +
                                "\u2022 Vanilla extract"
                    )
                )
                dishes.add(
                    Dish(
                        "Dessert with Berries",
                        250,
                        R.drawable.dessert_with_berries,
                        "\u2022 Mixed berries (strawberries, blueberries, raspberries)\n" +
                                "\n" +
                                "\u2022 Sugar\n" +
                                "\n" +
                                "\u2022 Cream or custard\n" +
                                "\n" +
                                "\u2022 Biscuit or sponge (optional)"
                    )
                )
                dishes.add(
                    Dish(
                        "Tiramisu Cake",
                        150,
                        R.drawable.tiramisu_cake,
                        "\u2022 Ladyfinger biscuits\n" +
                                "\n" +
                                "\u2022 Mascarpone cheese\n" +
                                "\n" +
                                "\u2022 Eggs\n" +
                                "\n" +
                                "\u2022 Sugar\n" +
                                "\n" +
                                "\u2022 Coffee\n" +
                                "\n" +
                                "\u2022 Cocoa powder\n" +
                                "\n" +
                                "\u2022 Marsala wine or rum (optional)."
                    )
                )
                dishes.add(
                    Dish(
                        "Cherries and Strawberry Cheesecake",
                        180,
                        R.drawable.cherries_and_strawberry_cheesecake,
                        "\u2022 Cream cheese\n" +
                                "\n" +
                                "\u2022 Sugar\n" +
                                "\n" +
                                "\u2022 Eggs\n" +
                                "\n" +
                                "\u2022 Graham cracker crust (or biscuit base)\n" +
                                "\n" +
                                "\u2022 Cherries & strawberries\n" +
                                "\n" +
                                "\u2022 Heavy cream or sour cream"
                    )
                )
            }
        }

        dishRecyclerView.adapter = DishAdapter(dishes)
    }

    // ✅ Adapter
    inner class DishAdapter(private val dishList: List<Dish>) :
        RecyclerView.Adapter<DishAdapter.DishViewHolder>() {

        inner class DishViewHolder(itemView: android.view.View) :
            RecyclerView.ViewHolder(itemView) {

            val dishImage: ImageView = itemView.findViewById(R.id.dishImage)
            val dishName: android.widget.TextView = itemView.findViewById(R.id.dishName)
            val dishPrice: android.widget.TextView = itemView.findViewById(R.id.dishPrice)
            val favBtn: ImageView = itemView.findViewById(R.id.favBtn)
            val addToCartBtn: android.widget.Button = itemView.findViewById(R.id.btnAddToCart)
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
            holder.dishPrice.text = "₹${dish.price}"
            holder.dishImage.setImageResource(dish.imageRes)

            // ❤️ Favorite State
            if (FavoriteManager.isFavorite(this@HomeActivity, dish.name)) {
                holder.favBtn.setImageResource(R.drawable.ic_fav_filled)
            } else {
                holder.favBtn.setImageResource(R.drawable.ic_fav_outline)
            }

            // ❤️ Favorite Click
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

            // 🛒 Add To Cart
            holder.addToCartBtn.setOnClickListener {

                CartManager.addToCart(
                    this@HomeActivity,
                    CartItem(
                        name = dish.name,
                        imageRes = dish.imageRes,
                        price = dish.price,
                        quantity = 1
                    )
                )

                Toast.makeText(
                    this@HomeActivity,
                    "${dish.name} added to Cart 🛒",
                    Toast.LENGTH_SHORT
                ).show()
            }

            // 📖 Show Description
            holder.dishImage.setOnClickListener {
                AlertDialog.Builder(this@HomeActivity)
                    .setTitle(dish.name)
                    .setMessage(dish.description)
                    .setPositiveButton("OK", null)
                    .show()
            }
        }

        override fun getItemCount(): Int = dishList.size
    }
}