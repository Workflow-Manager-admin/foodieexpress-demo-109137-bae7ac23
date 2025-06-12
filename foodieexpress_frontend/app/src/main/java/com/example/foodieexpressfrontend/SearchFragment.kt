package com.example.foodieexpressfrontend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.TextView

// PUBLIC_INTERFACE
class SearchFragment : Fragment() {
    /** This is the placeholder Search tab for the FoodieExpress main container. */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val tv = TextView(requireContext())
        tv.text = "\uD83D\uDD0D Search (Find restaurants or dishes)"
        tv.textSize = 26f
        tv.setTextColor(resources.getColor(R.color.colorPrimary, null))
        tv.setPadding(0, 128, 0, 0)
        tv.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
        return tv
    }
}
