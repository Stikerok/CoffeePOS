package com.hfad.coffeepos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.hfad.coffeepos.presentation.main.ui.SingOutDialogFragment

class MainActivity : AppCompatActivity(), ToolbarController {

    private lateinit var navController: NavController
    private lateinit var toolbarMenu: Menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val host: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
                ?: return
        navController = host.navController

        val appBarConfiguration = AppBarConfiguration(
            navController.graph,
            fallbackOnNavigateUpListener = ::onSupportNavigateUp
        )
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setupWithNavController(navController, appBarConfiguration)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        if (menu != null) {
            toolbarMenu = menu
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.button_sign_out) {
            val singOutDialogFragment = SingOutDialogFragment()
            val manager = supportFragmentManager
            singOutDialogFragment.show(manager, "myDialog")
        }
        return true
    }

    override fun setTitleToolbar(title: String) {
        supportActionBar?.title = title
    }

    override fun setVisibilityScanner(visible: Boolean) {
        toolbarMenu.getItem(0).isVisible = visible
    }

}