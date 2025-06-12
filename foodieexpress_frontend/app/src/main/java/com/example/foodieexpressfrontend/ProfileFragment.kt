package com.example.foodieexpressfrontend

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

// PUBLIC_INTERFACE
class ProfileFragment : Fragment() {
    /** The Profile tab shows static user info and a mock order history list (all UI static). */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Mock user and order history
        val username = "Amy Gourmet"
        val email = "amy.gourmet@example.com"
        val orders = listOf(
            OrderMock("2024-05-15", "Pasta Palace", "Rigatoni Alfredo", 27.98, "Completed"),
            OrderMock("2024-05-10", "Sushi Central", "Dragon Roll", 16.50, "Completed"),
            OrderMock("2024-04-28", "Curry Hub", "Masala Dosa", 19.50, "Completed"),
            OrderMock("2024-04-18", "Spicy Dragon", "General Tso's Chicken", 14.25, "Completed")
        )

        val scrollView = ScrollView(requireContext())
        val contentLayout = LinearLayout(requireContext())
        contentLayout.orientation = LinearLayout.VERTICAL
        contentLayout.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.colorSecondary))
        contentLayout.setPadding(24, 36, 24, 36)

        // User info
        val userIcon = TextView(requireContext())
        userIcon.text = "\uD83D\uDC64"
        userIcon.textSize = 48f
        userIcon.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorAccent))
        userIcon.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
        contentLayout.addView(userIcon)
        val userName = TextView(requireContext())
        userName.text = username
        userName.textSize = 23f
        userName.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorPrimary))
        userName.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
        contentLayout.addView(userName)
        val userEmail = TextView(requireContext())
        userEmail.text = email
        userEmail.textSize = 15f
        userEmail.setTextColor(ContextCompat.getColor(requireContext(), R.color.tabUnselected))
        userEmail.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
        userEmail.setPadding(0, 0, 0, 36)
        contentLayout.addView(userEmail)

        val label = TextView(requireContext())
        label.text = "Order History"
        label.textSize = 20f
        label.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorPrimaryDark))
        label.setPadding(0, 0, 0, 20)
        contentLayout.addView(label)

        // Order history cards
        for (order in orders) {
            val card = CardView(requireContext())
            val lp = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)
            lp.setMargins(0, 0, 0, 18)
            card.layoutParams = lp
            card.radius = 16f
            card.cardElevation = 5f
            card.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
            val col = LinearLayout(requireContext())
            col.orientation = LinearLayout.VERTICAL
            col.setPadding(18, 18, 18, 18)

            val orderDate = TextView(requireContext())
            orderDate.text = "\uD83D\uDCC5 ${order.date}"
            orderDate.textSize = 14f
            orderDate.setTextColor(ContextCompat.getColor(requireContext(), R.color.tabUnselected))
            val orderTitle = TextView(requireContext())
            orderTitle.text = "${order.restaurant} - ${order.dish}"
            orderTitle.textSize = 16f
            orderTitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorPrimaryDark))
            val amount = TextView(requireContext())
            amount.text = "₹" + "%.2f".format(order.price) + " (${order.status})"
            amount.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorAccent))
            amount.textSize = 16f

            col.addView(orderTitle)
            col.addView(orderDate)
            col.addView(amount)
            card.addView(col)
            contentLayout.addView(card)
        }

        scrollView.addView(contentLayout)
        return scrollView
    }

    data class OrderMock(
        val date: String,
        val restaurant: String,
        val dish: String,
        val price: Double,
        val status: String
    )
}
