package com.example.foodieexpressfrontend

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

// PUBLIC_INTERFACE
class MainActivity : AppCompatActivity() {
    /** Main container for FoodieExpress Demo, with bottom navigation and four tabs. */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_main)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    openFragment(HomeFragment())
                    true
                }
                R.id.nav_search -> {
                    openFragment(SearchFragment())
                    true
                }
                R.id.nav_cart -> {
                    openFragment(CartFragment())
                    true
                }
                R.id.nav_profile -> {
                    openFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }
        // Set initial tab (Home)
        if (savedInstanceState == null) {
            bottomNav.selectedItemId = R.id.nav_home
            openFragment(HomeFragment())
        }
    }

    // PUBLIC_INTERFACE
    private fun openFragment(fragment: Fragment) {
        /** Utility to swap tab fragments in main container. */
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
