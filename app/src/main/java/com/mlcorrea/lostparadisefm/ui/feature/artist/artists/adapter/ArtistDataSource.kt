package com.mlcorrea.lostparadisefm.ui.feature.artist.artists.adapter

import com.mlcorrea.domain.iteractor.artist.GetArtist
import com.mlcorrea.domain.model.ArtistPage
import com.mlcorrea.domain.model.adapter.ViewModelData
import com.mlcorrea.domain.network.NetworkRequestState
import com.mlcorrea.lostparadisefm.ui.base.BaseDataSource
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * Created by manuel.correa on 14/03/2018.
 */
class ArtistDataSource(private val query: String, private val getArtist: GetArtist) :
    BaseDataSource() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    /**
     * Keep Completable reference for the retry event
     */
    private var retryCompletable: Completable? = null


    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, ViewModelData>) {
        //
    }

    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<Long, ViewModelData>) {
        getNetworkState().postValue(NetworkRequestState.LOADING)
        getArtist.clear()
        if (query.isEmpty() && query.isBlank()) {
            callback.onResult(emptyList(), null, 2L)
            getNetworkState().postValue(NetworkRequestState.EMPTY)
        } else {
            getArtist.execute(object : DisposableObserver<ArtistPage>() {
                override fun onComplete() {
                    //
                }

                override fun onNext(t: ArtistPage) {
                    Timber.d("success")
                    // clear retry since last request succeeded
                    setRetry(null)
                    callback.onResult(t.artists, null, 2L)
                    getNetworkState().postValue(NetworkRequestState.LOADED)
                }

                override fun onError(e: Throwable) {
                    Timber.e(e)
                    // keep a Completable for future retry
                    setRetry(Action { loadInitial(params, callback) })
                    onErrorLoading(e)
                }
            }, GetArtist.Params(query, 1, params.requestedLoadSize))
        }
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, ViewModelData>) {
        // set network value to loading.
        getNetworkState().postValue(NetworkRequestState.LOADING)
        //get the initial users from the api
        getArtist.execute(object : DisposableObserver<ArtistPage>() {
            override fun onComplete() {
                //
            }

            override fun onNext(t: ArtistPage) {
                Timber.d("success")
                // clear retry since last request succeeded
                setRetry(null)
                val page: Long = params.key + 1
                getNetworkState().postValue(NetworkRequestState.LOADED)
                callback.onResult(t.artists, page)
            }

            override fun onError(e: Throwable) {
                Timber.e(e)
                // keep a Completable for future retry
                setRetry(Action { loadAfter(params, callback) })
                onErrorLoading(e)
            }
        }, GetArtist.Params(query, params.key.toInt(), params.requestedLoadSize))
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

    fun getCompositeDisposable(): CompositeDisposable {
        return compositeDisposable
    }

    fun getDisposablePaymentReport(): GetArtist {
        return getArtist
    }

    fun disposeAll() {
        compositeDisposable.dispose()
        getArtist.dispose()
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
        // publish the error
        getNetworkState().postValue(NetworkRequestState.error(e))
    }
}