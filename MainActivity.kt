package com.mitali.moviebuff.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.mitali.moviebuff.fregment.AboutApp
import com.mitali.moviebuff.fregment.Favourites
import com.mitali.moviebuff.fregment.PopularMovie
import com.mitali.moviebuff.R

class MainActivity : AppCompatActivity() {

    lateinit var drawerLayout: DrawerLayout
    lateinit var coordinatorLayout : CoordinatorLayout
    lateinit var toolbar : Toolbar
    lateinit var frameLayout: FrameLayout
    lateinit var navigationView : NavigationView

    var previousMenuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_layout)
        coordinatorLayout = findViewById(R.id.coordinator_layout)
        toolbar = findViewById(R.id.toolbar_layout)
        frameLayout = findViewById(R.id.frame_layout)
        navigationView = findViewById(R.id.nav_layout)
        //call fun
        setUpToolbar()
// fun to open popular dashboard primarily
        openPopularMoviesFragment()


        // variable to move the drawer layout in and out
        val actionBarDrawerToggle = ActionBarDrawerToggle(this@MainActivity,
        drawerLayout,
            R.string.open_drawer,
            R.string.close_drawer
        )
        // activate the variable in drawer layout
        // hambuger icon functional
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        // synchronising the state of toggle with state of navigation bar
        actionBarDrawerToggle.syncState()// the hambuger icon into back track icon n vice-versa

        // add click listener on navigation view
        navigationView.setNavigationItemSelectedListener {
           //it -> currently selected id
            if(previousMenuItem != null) {
                previousMenuItem?.isChecked = false
            }
            it.isCheckable = true
            it.isChecked = true
            previousMenuItem = it

            when(it.itemId){
                R.id.dashboard ->
                {
                    // to open fragments
                    openPopularMoviesFragment()
                    drawerLayout.closeDrawers()
                }
                R.id.favourites ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, Favourites())
                        .commit()

                    supportActionBar?.title = "Favourites"
                    drawerLayout.closeDrawers()
                }
                R.id.about ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, AboutApp())
                        .commit()

                    supportActionBar?.title = "About App"
                    drawerLayout.closeDrawers()
                }

            }
            return@setNavigationItemSelectedListener true
        }

    }
    // fun to setup a toolbar as we enable the action bar
    fun setUpToolbar(){
        setSupportActionBar(toolbar)
        supportActionBar?.title="Popular Movies"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
// click fun
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val  id = item.itemId
        if (id == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START)
        }//open the drawer from where the activity starts
        return super.onOptionsItemSelected(item)
    }

    fun openPopularMoviesFragment(){
        val fragment = PopularMovie()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_layout, PopularMovie())
        transaction.commit()
        supportActionBar?.title = "Popular Movies"
        navigationView.setCheckedItem(R.id.pop_fragment)
    }

    override fun onBackPressed() {
        val frag =  supportFragmentManager.findFragmentById(R.id.frame_layout)

        when(frag){
            !is PopularMovie -> openPopularMoviesFragment()

            else -> super.onBackPressed()
        }
    }
}
