package com.pradip.shaadi.ui

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.pradip.shaadi.common.observeK
import com.pradip.shaadi.common.setVisibleState
import com.pradip.shaadi.dataModel.CandidateDetailsDataModel
import com.pradip.shaadi.viewmodels.MainViewModel
import com.pradip.weatherapp.R
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.error_message.*
import kotlinx.android.synthetic.main.image_loader.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MainViewModel
    private lateinit var forecastDaysAdapter: ForecastDaysAdapter


    companion object {
        private const val TAG = "MainActivity"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        setUpObserver()
        setListeners()
        getCandidateDetails()
    }

    private fun getCandidateDetails() {
        viewModel.getWeatherforeCast()
    }

    private fun setUpObserver() {

        viewModel.getCandidateDetails.observeK(
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
        setUpRecyclerView()
    }


    private fun setListeners() {
        btnRetry.setOnClickListener {
            showView()

        }
    }

    private fun onSuccess(weatherForecastDataModel: CandidateDetailsDataModel) {
        updateData(weatherForecastDataModel)
    }

    private fun updateData(candidateDetailsDataModel: CandidateDetailsDataModel) {

        candidateDetailsDataModel.results?.let { forecastDaysAdapter.updateData(it) }
//        weatherForecastDataModel.forecast.forecastday?.let { forecastDaysAdapter.updateData(it) }


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
//

    }


}
