package com.example.myfoodera

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class DishAdapter(
    private val dishList: List<HomeActivity.Dish>
) : RecyclerView.Adapter<DishAdapter.DishViewHolder>() {

    inner class DishViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val dishImage: ImageView = itemView.findViewById(R.id.dishImage)
        val dishName: TextView = itemView.findViewById(R.id.dishName)
        val dishPrice: TextView = itemView.findViewById(R.id.dishPrice)

        val btnPlus: ImageButton = itemView.findViewById(R.id.btnPlus)
        val btnMinus: ImageButton = itemView.findViewById(R.id.btnMinus)
        val txtQuantity: TextView = itemView.findViewById(R.id.txtQuantity)

        val addToCartBtn: TextView = itemView.findViewById(R.id.addToCartBtn)
        val favBtn: ImageView = itemView.findViewById(R.id.favBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_dish, parent, false)
        return DishViewHolder(view)
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {

        val dish = dishList[position]
        var quantity = 1

        holder.dishName.text = dish.name
        holder.dishPrice.text = "₹${dish.price}"
        holder.dishImage.setImageResource(dish.imageRes) // ✅ FIXED
        holder.txtQuantity.text = quantity.toString()

        // ➕ Increase
        holder.btnPlus.setOnClickListener {
            quantity++
            holder.txtQuantity.text = quantity.toString()
        }

        // ➖ Decrease
        holder.btnMinus.setOnClickListener {
            if (quantity > 1) {
                quantity--
                holder.txtQuantity.text = quantity.toString()
            }
        }

        // ❤️ Favorite Click
        holder.favBtn.setOnClickListener {
            Toast.makeText(
                holder.itemView.context,
                "${dish.name} added to favorites ❤️",
                Toast.LENGTH_SHORT
            ).show()
        }

        // 🛒 Add To Cart
        holder.addToCartBtn.setOnClickListener {

            val item = CartItem(
                name = dish.name,
                price = dish.price,          // ✅ Int (NO toDouble)
                quantity = quantity,
                imageRes = dish.imageRes     // ✅ REQUIRED
            )

            CartManager.addToCart(holder.itemView.context, item)

            Toast.makeText(
                holder.itemView.context,
                "${dish.name} added to cart 🛒",
                Toast.LENGTH_SHORT
            ).show()

            quantity = 1
            holder.txtQuantity.text = quantity.toString()
        }

    }

    override fun getItemCount(): Int = dishList.size
}