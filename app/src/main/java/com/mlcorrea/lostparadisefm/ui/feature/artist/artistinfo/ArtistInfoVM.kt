package com.mlcorrea.lostparadisefm.ui.feature.artist.artistinfo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mlcorrea.domain.iteractor.artist.GetArtistInfo
import com.mlcorrea.domain.model.Artist
import com.mlcorrea.domain.model.response.ResponseRx
import com.mlcorrea.domain.network.NetworkRequestState
import io.reactivex.observers.DisposableObserver

/**
 * Created by manuel on 08/04/19
 */
class ArtistInfoVM constructor(private val getArtistInfo: GetArtistInfo) : ViewModel() {

    val artistData: MutableLiveData<ResponseRx<Artist>> = MutableLiveData()
    val networkState = MutableLiveData<NetworkRequestState>()

    private var isLoadedModel = false
    private lateinit var artistCache: String

    override fun onCleared() {
        getArtistInfo.dispose()
        super.onCleared()
    }

    fun initVM(artist: String) {
        if (isLoadedModel) return
        isLoadedModel = true
        artistCache = artist
        networkState.value = NetworkRequestState.INIT
        getAlbumNetwork(artist)
    }

    fun retry() {
        networkState.value = NetworkRequestState.INIT
        getAlbumNetwork(artistCache)
    }

    private fun getAlbumNetwork(artist: String) {
        artistData.value = ResponseRx.Loading()
        getArtistInfo.execute(GetArtistObserver(), GetArtistInfo.Params(artist))
    }

    inner class GetArtistObserver : DisposableObserver<Artist>() {
        override fun onComplete() {
            //
        }

        override fun onNext(t: Artist) {
            networkState.value = NetworkRequestState.LOADED
            artistData.value = ResponseRx.Success(t)
        }

        override fun onError(e: Throwable) {
            networkState.value = NetworkRequestState.error(e as Exception)
            artistData.value = ResponseRx.Error(e)
        }

    }
}