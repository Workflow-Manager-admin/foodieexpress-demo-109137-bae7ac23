package com.example.foodieexpressfrontend

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

// PUBLIC_INTERFACE
class CartFragment : Fragment() {
    /** The Cart tab for FoodieExpress: shows a scrollable mock cart list and total. */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Mock cart data
        val cartItems = listOf(
            CartMock("Rigatoni Alfredo", "Pasta Palace", android.R.drawable.ic_menu_gallery, 13.99, 2),
            CartMock("Dragon Roll", "Sushi Central", android.R.drawable.ic_menu_gallery, 16.50, 1),
            CartMock("Masala Dosa", "Curry Hub", android.R.drawable.ic_menu_gallery, 9.75, 3)
        )

        val scrollView = ScrollView(requireContext())
        val contentLayout = LinearLayout(requireContext())
        contentLayout.orientation = LinearLayout.VERTICAL
        contentLayout.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.colorSecondary))
        contentLayout.setPadding(24, 36, 24, 36)

        // Title
        val title = TextView(requireContext())
        title.text = "\uD83D\uDED2 My Cart"
        title.textSize = 25f
        title.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorPrimary))
        title.setPadding(0, 0, 0, 32)
        title.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
        contentLayout.addView(title)

        // Add cart card for each item
        var total = 0.0
        for (item in cartItems) {
            val itemTotal = item.price * item.qty
            total += itemTotal

            val card = CardView(requireContext())
            val lp = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            lp.setMargins(0, 0, 0, 22)
            card.layoutParams = lp
            card.radius = 18f
            card.cardElevation = 7f
            card.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
            val row = LinearLayout(requireContext())
            row.orientation = LinearLayout.HORIZONTAL
            row.setPadding(22, 16, 22, 16)
            val img = ImageView(requireContext())
            img.setImageResource(item.imgRes)
            val imgSize = 92
            val imgLp = LinearLayout.LayoutParams(imgSize, imgSize)
            imgLp.setMargins(0, 0, 18, 0)
            img.layoutParams = imgLp
            val col = LinearLayout(requireContext())
            col.orientation = LinearLayout.VERTICAL
            val dishTv = TextView(requireContext())
            dishTv.text = item.name
            dishTv.textSize = 18f
            dishTv.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorPrimaryDark))
            val restTv = TextView(requireContext())
            restTv.text = "\uD83C\uDF74 ${item.restaurant}"
            restTv.textSize = 15f
            restTv.setTextColor(ContextCompat.getColor(requireContext(), R.color.tabUnselected))
            val priceQtyTv = TextView(requireContext())
            priceQtyTv.text = "$${"%.2f".format(item.price)} x${item.qty} (\u20B9${"%.2f".format(itemTotal)})"
            priceQtyTv.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorAccent))
            col.addView(dishTv)
            col.addView(restTv)
            col.addView(priceQtyTv)
            row.addView(img)
            row.addView(col)
            card.addView(row)
            contentLayout.addView(card)
        }
        // Total
        val totalTv = TextView(requireContext())
        totalTv.text = "Total: \u20B9%.2f".format(total)
        totalTv.textSize = 22f
        totalTv.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorAccent))
        totalTv.setPadding(0, 36, 0, 16)
        totalTv.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
        contentLayout.addView(totalTv)
        // Fake "Checkout" button (not functional)
        val checkoutBtn = Button(requireContext())
        checkoutBtn.text = "Checkout"
        checkoutBtn.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.colorAccent))
        checkoutBtn.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        checkoutBtn.textSize = 18f
        checkoutBtn.isEnabled = false // Not real
        val btnLp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        btnLp.setMargins(18, 0, 18, 0)
        checkoutBtn.layoutParams = btnLp
        contentLayout.addView(checkoutBtn)
        scrollView.addView(contentLayout)
        return scrollView
    }

    data class CartMock(
        val name: String,
        val restaurant: String,
        val imgRes: Int,
        val price: Double,
        val qty: Int
    )
}
