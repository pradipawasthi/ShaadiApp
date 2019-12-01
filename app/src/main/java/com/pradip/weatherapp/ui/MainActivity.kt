package com.pradip.weatherapp.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import com.pradip.weatherapp.BuildConfig.APPLICATION_ID
import com.pradip.weatherapp.R
import com.pradip.weatherapp.common.observeK
import com.pradip.weatherapp.common.setVisibleState
import com.pradip.weatherapp.dataModel.WeatherForecastDataModel
import com.pradip.weatherapp.isInternetConnected
import com.pradip.weatherapp.viewmodels.MainViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.error_message.*
import kotlinx.android.synthetic.main.image_loader.*
import java.util.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MainViewModel
    private lateinit var forecastDaysAdapter: ForecastDaysAdapter

    companion object {
        private const val TAG = "MainActivity"
        private const val REQUEST_PERMISSIONS_REQUEST_CODE = 34
    }

    private lateinit var fusedLocationClient: FusedLocationProviderClient


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        setUpObserver()
        setListeners()
    }

    private fun getWeatherForecast(
        city: String,
        latitude: Double,
        longitude: Double
    ) {
        viewModel.getWeatherforeCast(city, latitude, longitude)
    }

    private fun setUpObserver() {

        viewModel.getWeatherForecastData.observeK(
            this,
            ::onSuccess,
            ::onApiError,
            ::unAuthorizeUserError,
            ::ioExceptionHandler,
            ::updateProgressBarState
        )
    }

    private fun init() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        setUpRecyclerView()

    }

    private fun setListeners() {
        btnRetry.setOnClickListener {
            getLastLocation()
            showView()

        }
    }


    override fun onStart() {
        super.onStart()
        if (!checkPermissions()) {
            requestPermissions()
        } else {
            getLastLocation()
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        fusedLocationClient.lastLocation
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful && task.result != null) {
                    if (isInternetConnected(this)) {
                        getCityName(
                            task.result!!.latitude,
                            task.result!!.longitude
                        )
                    } else {
                        Toast.makeText(this@MainActivity, "no_location_detected", Toast.LENGTH_LONG)
                            .show()
                        showNetworkError()
                    }

                } else {
                    Log.d(TAG, "no_location_detected")
                    Toast.makeText(this@MainActivity, "no_location_detected", Toast.LENGTH_LONG)
                        .show()
                    showNetworkError()
                }
            }
    }

    private fun getCityName(latitude: Double, longitude: Double) {
        val geocoder = Geocoder(this, Locale.getDefault())
        val addresses = geocoder.getFromLocation(latitude, longitude, 1)
        val city = addresses[0].locality
        getWeatherForecast(city, latitude, longitude)
    }

    private fun showSnackbar(
        snackStrId: Int,
        actionStrId: Int = 0,
        listener: View.OnClickListener? = null
    ) {
        val snackbar = Snackbar.make(
            findViewById(android.R.id.content), getString(snackStrId),
            Snackbar.LENGTH_INDEFINITE
        )
        if (actionStrId != 0 && listener != null) {
            snackbar.setAction(getString(actionStrId), listener)
        }
        snackbar.show()
    }


    private fun checkPermissions() =
        ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

    private fun startLocationPermissionRequest() {
        ActivityCompat.requestPermissions(
            this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
            REQUEST_PERMISSIONS_REQUEST_CODE
        )
    }

    private fun requestPermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        ) {
            Log.i(TAG, "Displaying permission rationale to provide additional context.")
            showSnackbar(R.string.permission_rationale, android.R.string.ok, View.OnClickListener {
                // Request permission
                startLocationPermissionRequest()
            })

        } else {
            startLocationPermissionRequest()
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
            when {
                grantResults.isEmpty() -> Log.i(TAG, "User interaction was cancelled.")
                (grantResults[0] == PackageManager.PERMISSION_GRANTED) -> getLastLocation()
                else -> {
                    showSnackbar(
                        R.string.permission_denied_explanation, R.string.settings,
                        View.OnClickListener {
                            val intent = Intent().apply {
                                action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                                data = Uri.fromParts("package", APPLICATION_ID, null)
                                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                            }
                            startActivity(intent)
                        })
                }
            }
        }
    }


    private fun onSuccess(weatherForecastDataModel: WeatherForecastDataModel) {
        updateData(weatherForecastDataModel)
    }

    private fun updateData(weatherForecastDataModel: WeatherForecastDataModel) {
        tvTemperature.text =
            getString(
                R.string.string_current_temp,
                weatherForecastDataModel.current.tempC.toString()
            )
        tvLocation.text = weatherForecastDataModel.location.name
        weatherForecastDataModel.forecast.forecastday?.let { forecastDaysAdapter.updateData(it) }


    }

    private fun showProgressBar() {
        val aniRotate =
            AnimationUtils.loadAnimation(applicationContext, R.anim.image_rotation)
        ivLoader.startAnimation(aniRotate)
    }

    private fun unAuthorizeUserError() {
        showErrorView()
    }


    private fun onApiError() {
        showErrorView()
    }

    private fun updateProgressBarState(state: Boolean) {
        progressBar.setVisibleState(state)
        rootView.setVisibleState(!state)
        if (state) {
            showProgressBar()
        }

    }

    private fun ioExceptionHandler() {
        showErrorView()
    }


    private fun showErrorView() {
        errorView.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        rootView.visibility = View.GONE

    }

    fun showNetworkError() {
        errorView.setVisibleState(true)
        errorText.text = getString(R.string.check_your_internet)
        rootView.setVisibleState(false)
    }

    fun showView() {
        errorView.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
        rootView.visibility = View.VISIBLE
    }

    private fun setUpRecyclerView() {
        forecastDaysAdapter = ForecastDaysAdapter()
        forecastItem.adapter = forecastDaysAdapter
        forecastItem.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))


    }
}
