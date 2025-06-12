package com.example.foodieexpressfrontend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.TextView

// PUBLIC_INTERFACE
class HomeFragment : Fragment() {
    /** This is the placeholder Home tab for the FoodieExpress main container. */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val tv = TextView(requireContext())
        tv.text = "\uD83C\uDF5D Home (Restaurants List)"
        tv.textSize = 26f
        tv.setTextColor(resources.getColor(R.color.colorPrimary, null))
        tv.setPadding(0, 128, 0, 0)
        tv.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
        return tv
    }
}
