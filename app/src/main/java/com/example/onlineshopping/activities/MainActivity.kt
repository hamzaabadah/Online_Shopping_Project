package com.example.onlineshopping.activities


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.onlineshopping.R
import com.example.onlineshopping.menu_fragments.ClothesFragment
import com.example.onlineshopping.menu_fragments.HomeFragment
import com.example.onlineshopping.menu_fragments.MobilesFragment
import com.example.onlineshopping.menu_fragments.ShoesFragment
import com.example.onlineshopping.profile_fragments.AddProductFragment
import com.example.onlineshopping.profile_fragments.ProfileFragment
import com.example.onlineshopping.usersactivities.LogInActivity
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)

        //For Default Selected Item at Navigation Drawer
        nav_view.menu.getItem(0).isChecked = true
        onNavigationItemSelected(nav_view.menu.getItem(0))


    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.nav_home -> {
                replaceFragment(HomeFragment())
            }
            R.id.nav_clothes -> {
                replaceFragment(ClothesFragment())
            }
            R.id.nav_shoes -> {
                replaceFragment(ShoesFragment())
            }
            R.id.nav_mobiles -> {
                replaceFragment(MobilesFragment())
            }
            R.id.nav_profile -> {
                val intent = Intent(this, profileActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_add_product ->{
                val intent = Intent(this, AddProductActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_logout ->{

                val preferences = getSharedPreferences("myprefs", MODE_PRIVATE)
                val editor = preferences.edit()
                editor.clear().commit()
                val intent = Intent(this, LogInActivity::class.java)
                startActivity(intent)
                finish()
        }

        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.mainContainer, fragment).commit()
    }


}
