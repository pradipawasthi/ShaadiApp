package com.pradip.shaadi.di

import androidx.lifecycle.ViewModel
import com.pradip.data.db.CandidateDetailsDao
import com.pradip.data.db.ShaadiDatabase
import com.pradip.data.repository.CandidateDetailsRepositoryImpl
import com.pradip.data.service.CandidateDetailsService
import com.pradip.domain.repository.CandidateDetailsRepository
import com.pradip.shaadi.di.scope.PerActivity
import com.pradip.shaadi.ui.MainActivity
import com.pradip.shaadi.viewmodels.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import retrofit2.Retrofit

/**
 *  Created by Pradip Awasthi on 2019-09-14.
 */

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    internal abstract fun contributeMainActivity(): MainActivity


    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    abstract fun bindCandidateDetailsRepositoryImpl(candidateDetailsRepositoryImpl: CandidateDetailsRepositoryImpl): CandidateDetailsRepository




    @Module
    companion object {

        @Provides
        @JvmStatic
        fun provideCandidateDetailsService(retrofit: Retrofit): CandidateDetailsService =
            retrofit.create(CandidateDetailsService::class.java)


        @Provides
        @JvmStatic
        internal fun bindCandidateDetailsDao(shaadiDatabase: ShaadiDatabase): CandidateDetailsDao {
            return shaadiDatabase.candidateDetailsDao()
        }

    }


}