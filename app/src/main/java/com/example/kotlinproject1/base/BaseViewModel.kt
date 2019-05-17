package com.example.kotlinproject1.base

import android.arch.lifecycle.ViewModel
import com.example.kotlinproject1.injection.component.DaggerViewModelInjector
import com.example.kotlinproject1.injection.component.ViewModelInjector
import com.example.kotlinproject1.injection.module.NetworkModule
import com.example.kotlinproject1.ui.post.PostListViewModel

abstract class BaseViewModel: ViewModel() {

    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is PostListViewModel -> injector.inject(this)
        }
    }

}