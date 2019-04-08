package com.mlcorrea.lostparadisefm.ui.feature.album.albuminfo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mlcorrea.domain.iteractor.album.GetAlbumInfo
import com.mlcorrea.domain.model.Album
import com.mlcorrea.domain.model.response.ResponseRx
import com.mlcorrea.domain.network.NetworkRequestState
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

/**
 * Created by manuel on 08/04/19
 */
class AlbumInfoVM @Inject constructor(private val getAlbumInfo: GetAlbumInfo) : ViewModel() {

    val albumData: MutableLiveData<ResponseRx<Album>> = MutableLiveData()
    val networkState = MutableLiveData<NetworkRequestState>()

    private var isLoadedModel = false
    private lateinit var artistCache: String
    private lateinit var albumCache: String

    override fun onCleared() {
        getAlbumInfo.dispose()
        super.onCleared()
    }

    fun initVM(artist: String, album: String, image: String?) {
        if (isLoadedModel) return
        isLoadedModel = true
        artistCache = artist
        albumCache = album
        networkState.value = NetworkRequestState.INIT
        getAlbumNetwork(artist, album)
    }

    fun retry() {
        networkState.value = NetworkRequestState.INIT
        getAlbumNetwork(artistCache, albumCache)
    }

    private fun getAlbumNetwork(artist: String, album: String) {
        albumData.value = ResponseRx.Loading()
        getAlbumInfo.execute(GetAlbumObserver(), GetAlbumInfo.Params(album, artist))
    }

    inner class GetAlbumObserver : DisposableObserver<Album>() {
        override fun onComplete() {
            //
        }

        override fun onNext(t: Album) {
            networkState.value = NetworkRequestState.LOADED
            albumData.value = ResponseRx.Success(t)
        }

        override fun onError(e: Throwable) {
            networkState.value = NetworkRequestState.error(e as Exception)
            albumData.value = ResponseRx.Error(e)
        }

    }
}