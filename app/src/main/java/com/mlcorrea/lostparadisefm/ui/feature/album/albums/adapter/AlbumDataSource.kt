package com.mlcorrea.lostparadisefm.ui.feature.album.albums.adapter

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.mlcorrea.domain.iteractor.album.GetAlbums
import com.mlcorrea.domain.model.AlbumPage
import com.mlcorrea.domain.model.adapter.ViewModelData
import com.mlcorrea.domain.network.NetworkRequestState
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.concurrent.atomic.AtomicInteger

/**
 * Created by manuel.correa on 14/03/2018.
 */
class AlbumDataSource(private val query: String, private val getAlbums: GetAlbums) :
    PageKeyedDataSource<Long, ViewModelData>() {

    private val networkState = MutableLiveData<NetworkRequestState>()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private var pageNumber: AtomicInteger = AtomicInteger(1)
    /**
     * Keep Completable reference for the retry event
     */
    private var retryCompletable: Completable? = null


    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, ViewModelData>) {
    }

    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<Long, ViewModelData>) {
        networkState.postValue(NetworkRequestState.LOADING)
        getAlbums.clear()
        if (query.isEmpty() && query.isBlank()) {
            callback.onResult(emptyList(), null, 2L)
            networkState.postValue(NetworkRequestState.EMPTY)
        } else {
            getAlbums.execute(object : DisposableObserver<AlbumPage>() {
                override fun onComplete() {
                    //
                }

                override fun onNext(t: AlbumPage) {
                    Timber.d("success")
                    // clear retry since last request succeeded
                    setRetry(null)
                    callback.onResult(t.albums as List<ViewModelData>, null, 2L)
                    networkState.postValue(NetworkRequestState.LOADED)
                }

                override fun onError(e: Throwable) {
                    Timber.e(e)
                    // keep a Completable for future retry
                    setRetry(Action { loadInitial(params, callback) })
                    onErrorLoading(e)
                }
            }, GetAlbums.Params(query, 1, params.requestedLoadSize))
        }
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, ViewModelData>) {
        // set network value to loading.
        networkState.postValue(NetworkRequestState.LOADING)
        //get the initial users from the api
        getAlbums.execute(object : DisposableObserver<AlbumPage>() {
            override fun onComplete() {
                //
            }

            override fun onNext(t: AlbumPage) {
                Timber.d("success")
                // clear retry since last request succeeded
                setRetry(null)
                val page: Long = params.key + 1
                networkState.postValue(NetworkRequestState.LOADED)
                callback.onResult(t.albums as List<ViewModelData>, page)
            }

            override fun onError(e: Throwable) {
                Timber.e(e)
                // keep a Completable for future retry
                setRetry(Action { loadAfter(params, callback) })
                onErrorLoading(e)
            }
        }, GetAlbums.Params(query, params.key.toInt(), params.requestedLoadSize))
    }

    /*--------------PUBLIC METHOD----------*/

    fun retry() {
        retryCompletable?.let {
            compositeDisposable.add(
                it
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ }, { throwable -> Timber.e(throwable.message) })
            )
        }
    }

    fun getNetworkState(): MutableLiveData<NetworkRequestState> {
        return networkState
    }

    fun getCompositeDisposable(): CompositeDisposable {
        return compositeDisposable
    }

    fun getDisposablePaymentReport(): GetAlbums {
        return getAlbums
    }

    fun disposeAll() {
        compositeDisposable.dispose()
        getAlbums.dispose()
    }

    /*------------PRIVATE METHOD---------*/

    private fun setRetry(action: Action?) {
        if (action == null) {
            this.retryCompletable = null
        } else {
            this.retryCompletable = Completable.fromAction(action)
        }
    }

    private fun onErrorLoading(e: Throwable) {
        pageNumber.decrementAndGet()
        // publish the error
        networkState.postValue(NetworkRequestState.error(e))
    }
}