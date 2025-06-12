package com.example.foodieexpressfrontend

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

// PUBLIC_INTERFACE
class HomeFragment : Fragment() {
    /** The Home tab for FoodieExpress main container with a scrollable list of mock restaurants. */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Mock restaurant data
        val restaurants = listOf(
            RestaurantMock(
                "Pasta Palace",
                android.R.drawable.ic_menu_gallery,
                4.7,
                "Italian"
            ),
            RestaurantMock(
                "Spicy Dragon",
                android.R.drawable.ic_menu_gallery,
                4.5,
                "Chinese"
            ),
            RestaurantMock(
                "Curry Hub",
                android.R.drawable.ic_menu_gallery,
                4.6,
                "Indian"
            ),
            RestaurantMock(
                "Burger World",
                android.R.drawable.ic_menu_gallery,
                4.2,
                "American"
            ),
            RestaurantMock(
                "Sushi Central",
                android.R.drawable.ic_menu_gallery,
                4.9,
                "Japanese"
            )
        )

        // Set up outer scroll view
        val scrollView = ScrollView(requireContext())
        val contentLayout = LinearLayout(requireContext())
        contentLayout.orientation = LinearLayout.VERTICAL
        contentLayout.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.colorSecondary))
        contentLayout.setPadding(24, 36, 24, 36)

        // Add section title
        val title = TextView(requireContext())
        title.text = "\uD83C\uDF5D Restaurants"
        title.textSize = 26f
        title.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorPrimary))
        title.setPadding(0, 0, 0, 36)
        title.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
        contentLayout.addView(title)

        // Add each restaurant as a card
        for (rest in restaurants) {
            val card = CardView(requireContext())
            val lp = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            lp.setMargins(0, 0, 0, 28)
            card.layoutParams = lp
            card.radius = 24f
            card.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
            card.cardElevation = 7f

            val innerRow = LinearLayout(requireContext())
            innerRow.orientation = LinearLayout.HORIZONTAL
            innerRow.setPadding(24, 20, 24, 20)

            val img = ImageView(requireContext())
            img.setImageResource(rest.imgRes)
            val imgParams = LinearLayout.LayoutParams(128, 128)
            imgParams.setMargins(0, 0, 28, 0)
            img.layoutParams = imgParams

            val textCol = LinearLayout(requireContext())
            textCol.orientation = LinearLayout.VERTICAL
            textCol.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            val nameTv = TextView(requireContext())
            nameTv.text = rest.name
            nameTv.textSize = 20f
            nameTv.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorPrimaryDark))

            val cuisineTv = TextView(requireContext())
            cuisineTv.text = "\uD83C\uDF54 ${rest.cuisine}"
            cuisineTv.textSize = 15f
            cuisineTv.setTextColor(ContextCompat.getColor(requireContext(), R.color.tabUnselected))

            val ratingTv = TextView(requireContext())
            ratingTv.text = "\u2B50 %.1f".format(rest.rating)
            ratingTv.textSize = 18f
            ratingTv.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorAccent))

            textCol.addView(nameTv)
            textCol.addView(cuisineTv)
            textCol.addView(ratingTv)

            innerRow.addView(img)
            innerRow.addView(textCol)

            card.addView(innerRow)
            contentLayout.addView(card)
        }

        scrollView.addView(contentLayout)
        return scrollView
    }

    // Simple mock data class
    data class RestaurantMock(
        val name: String,
        val imgRes: Int,
        val rating: Double,
        val cuisine: String
    )
}
