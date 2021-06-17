package com.sun.gamevui

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.sun.gamevui.databinding.ActivityMainBinding
import com.sun.gamevui.ui.detail.GameDetailFragment
import com.sun.gamevui.utils.NetworkUtil
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModel<MainViewModel>()
    private var isConnectionInternet = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ViewModelProviders.of(this).get(viewModel.javaClass)
        isConnectionInternet = NetworkUtil.isConnection(this)
        if (!isConnectionInternet) {
            showDialogCheckInternet()
            return
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentNavHost) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigation.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.gameDetailFragment -> binding.bottomNavigation.visibility = View.GONE
                R.id.tagFragment -> binding.bottomNavigation.visibility = View.GONE
                R.id.newsDetailFragment -> binding.bottomNavigation.visibility = View.GONE
                else -> binding.bottomNavigation.visibility = View.VISIBLE
            }

        }
    }

    private fun showDialogCheckInternet() {
        AlertDialog.Builder(this)
            .setTitle("Network error!!!")
            .setMessage("Check your internet connection")
            .setCancelable(false)
            .setPositiveButton("OK") { _, _ ->
                startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
                finish()
            }
            .setNegativeButton("Cancel") { _, _ ->
                finish()
            }.show()
    }
}
