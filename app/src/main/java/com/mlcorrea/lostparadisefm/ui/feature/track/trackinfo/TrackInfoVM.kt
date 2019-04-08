package com.mlcorrea.lostparadisefm.ui.feature.track.trackinfo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mlcorrea.domain.iteractor.track.GetTrackInfo
import com.mlcorrea.domain.model.Track
import com.mlcorrea.domain.model.response.ResponseRx
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

/**
 * Created by manuel on 08/04/19
 */
class TrackInfoVM @Inject constructor(private val getTrackInfo: GetTrackInfo) : ViewModel() {

    val trackData: MutableLiveData<ResponseRx<Track>> = MutableLiveData()

    private var isLoadedModel = false
    private lateinit var artistCache: String
    private lateinit var trackCache: String

    override fun onCleared() {
        getTrackInfo.dispose()
        super.onCleared()
    }

    fun initVM(artist: String, track: String) {
        if (isLoadedModel) return
        isLoadedModel = true
        artistCache = artist
        trackCache = track
        getAlbumNetwork(artist, track)
    }

    fun retry() {
        getAlbumNetwork(artistCache, trackCache)
    }

    private fun getAlbumNetwork(artist: String, track: String) {
        trackData.value = ResponseRx.Loading()
        getTrackInfo.execute(GetTrackInfoObserver(), GetTrackInfo.Params(track, artist))
    }

    inner class GetTrackInfoObserver : DisposableObserver<Track>() {
        override fun onComplete() {
            //
        }

        override fun onNext(t: Track) {
            trackData.value = ResponseRx.Success(t)
        }

        override fun onError(e: Throwable) {
            trackData.value = ResponseRx.Error(e as Exception)
        }

    }
}