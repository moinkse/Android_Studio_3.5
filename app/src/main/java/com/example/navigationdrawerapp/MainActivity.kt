package com.example.navigationdrawerapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {
    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, 0, 0
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_profile -> {
                Toast.makeText(this, "Profile clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_messages -> {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "plain/text"
                intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("some@email.address"))
                intent.putExtra(Intent.EXTRA_SUBJECT, "subject")
                intent.putExtra(Intent.EXTRA_TEXT, "mail body")
                startActivity(Intent.createChooser(intent, ""))
            }
            R.id.nav_friends -> {
                intent= Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/"))
                startActivity(intent)
            }
            R.id.nav_update -> {
                intent= Intent(Intent.ACTION_VIEW, Uri.parse("https://www.driversupdate.com/"))
                startActivity(intent)
            }
            R.id.nav_logout -> {
                moveTaskToBack(true);
                exitProcess(-1)
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
