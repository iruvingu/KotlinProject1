package com.example.kotlinproject1.ui.post

import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.example.kotlinproject1.network.PostApi
import com.example.kotlinproject1.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class PostListViewModel: BaseViewModel() {
    @Inject
    lateinit var postApi: PostApi

    private lateinit var subscription: Disposable

    //add a MutableLiveData
    val loadingVisibilty: MutableLiveData<Int> = MutableLiveData()

    init {
        loadPosts()
    }

    private fun loadPosts() {
        subscription = postApi.getPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe(
                { onRetrievePostListSuccess() },
                { onRetrievePostListError() }
            )
    }

    private fun onRetrievePostListStart() {
        loadingVisibilty.value = View.VISIBLE
    }

    private fun onRetrievePostListFinish() {
        loadingVisibilty.value = View.GONE
    }

    private fun onRetrievePostListError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun onRetrievePostListSuccess() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

}