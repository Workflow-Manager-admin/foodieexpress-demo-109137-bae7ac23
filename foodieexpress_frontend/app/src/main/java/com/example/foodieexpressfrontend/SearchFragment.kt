package com.example.foodieexpressfrontend

import android.os.Bundle
import android.text.InputType
import android.view.*
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

// PUBLIC_INTERFACE
class SearchFragment : Fragment() {
    /** The Search tab with a static search bar and a mock filtered restaurant list.
     * Uses static/mock data only.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Mock list of restaurants (same as Home)
        val allRestaurants = listOf(
            "Pasta Palace" to "Italian",
            "Spicy Dragon" to "Chinese",
            "Curry Hub" to "Indian",
            "Burger World" to "American",
            "Sushi Central" to "Japanese"
        )
        // Simulate a search for "Sushi"
        val query = "Sushi"
        val filteredRestaurants = allRestaurants.filter {
            it.first.contains(query, ignoreCase = true) ||
            it.second.contains(query, ignoreCase = true)
        }

        // Outer layout
        val scrollView = ScrollView(requireContext())
        val contentLayout = LinearLayout(requireContext())
        contentLayout.orientation = LinearLayout.VERTICAL
        contentLayout.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.colorSecondary))
        contentLayout.setPadding(24, 36, 24, 36)

        // Search bar row (static, no actual interaction)
        val searchBarLayout = LinearLayout(requireContext())
        searchBarLayout.orientation = LinearLayout.HORIZONTAL
        searchBarLayout.setPadding(0, 0, 0, 24)
        searchBarLayout.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
        val editText = EditText(requireContext())
        editText.setText(query)
        editText.isEnabled = false
        editText.hint = "Search restaurants or cuisine"
        editText.inputType = InputType.TYPE_CLASS_TEXT
        editText.textSize = 17f
        editText.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
        editText.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
        searchBarLayout.addView(editText)
        val icon = ImageView(requireContext())
        icon.setImageResource(android.R.drawable.ic_menu_search)
        val size = 48
        val iconLp = LinearLayout.LayoutParams(size, size)
        iconLp.setMargins(16, 0, 8, 0)
        icon.layoutParams = iconLp
        searchBarLayout.addView(icon)

        contentLayout.addView(searchBarLayout)

        // Add label for results
        val resultLabel = TextView(requireContext())
        resultLabel.text = "\uD83D\uDD0D Search Results: \"$query\""
        resultLabel.textSize = 18f
        resultLabel.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorPrimaryDark))
        resultLabel.setPadding(0, 0, 0, 18)
        contentLayout.addView(resultLabel)

        if (filteredRestaurants.isEmpty()) {
            val noResult = TextView(requireContext())
            noResult.text = "No restaurants found."
            noResult.setTextColor(ContextCompat.getColor(requireContext(), R.color.tabUnselected))
            noResult.textSize = 17f
            contentLayout.addView(noResult)
        } else {
            for ((name, cuisine) in filteredRestaurants) {
                val card = CardView(requireContext())
                val lp = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT)
                lp.setMargins(0, 0, 0, 22)
                card.layoutParams = lp
                card.radius = 18f
                card.cardElevation = 6f
                card.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
                val inner = LinearLayout(requireContext())
                inner.orientation = LinearLayout.HORIZONTAL
                inner.setPadding(20, 16, 20, 16)
                val img = ImageView(requireContext())
                img.setImageResource(android.R.drawable.ic_menu_gallery)
                val imgSize = 92
                val imgLp = LinearLayout.LayoutParams(imgSize, imgSize)
                imgLp.setMargins(0, 0, 18, 0)
                img.layoutParams = imgLp
                val col = LinearLayout(requireContext())
                col.orientation = LinearLayout.VERTICAL
                val nameTv = TextView(requireContext())
                nameTv.text = name
                nameTv.textSize = 18f
                nameTv.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorPrimary))
                val cuisineTv = TextView(requireContext())
                cuisineTv.text = cuisine
                cuisineTv.setTextColor(ContextCompat.getColor(requireContext(), R.color.tabUnselected))
                cuisineTv.textSize = 15f
                col.addView(nameTv)
                col.addView(cuisineTv)
                inner.addView(img)
                inner.addView(col)
                card.addView(inner)
                contentLayout.addView(card)
            }
        }
        scrollView.addView(contentLayout)
        return scrollView
    }
}
