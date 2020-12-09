package ru.geekbrains.bookofrecipes.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.geekbrains.bookofrecipes.R
import ru.geekbrains.bookofrecipes.presentation.ui.searching.SearchDialogFragment

class MainActivity : AppCompatActivity() {

    private  var searchDialogFragment: SearchDialogFragment = SearchDialogFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initNavController()


    }

    private fun initNavController() {
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
                as NavHostFragment
        val navController = navHostFragment.navController

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.navigation_recipes, R.id.navigation_favorites)
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }


    fun searchByIngredients(view: View){
        searchDialogFragment.show(supportFragmentManager, "MyCustomFragment")

    }

    fun onClickSearchByIngredients(view:View){
        println("${searchDialogFragment.getTextFromEditText()}                   that's it")
    }

}