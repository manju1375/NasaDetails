package com.manju1375.nasadetails.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.snackbar.Snackbar
import com.manju1375.nasadetails.R
import com.manju1375.nasadetails.databinding.ActivityMainBinding
import com.manju1375.nasadetails.network.NetworkStatus
import com.manju1375.nasadetails.network.NetworkStatusHelper
import com.manju1375.nasadetails.ui.viewmodel.NasaDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NasaGalleryActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var networkStatusHelper: NetworkStatusHelper
    private val nasaDetailsViewModel: NasaDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)


        networkStatusHelper.observe(this, {
            when (it) {
                NetworkStatus.Available -> {
                    if (nasaDetailsViewModel.isNetworkLast.value == true) {
                        display(resources.getString(R.string.network_available))
                        nasaDetailsViewModel.changeNetworkStatus(false)
                    }
                }
                NetworkStatus.Unavailable -> {
                    display(resources.getString(R.string.network_not_available))
                    nasaDetailsViewModel.changeNetworkStatus(true)
                }
            }
        })
    }

    private fun display(msg: String) {
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_LONG).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}