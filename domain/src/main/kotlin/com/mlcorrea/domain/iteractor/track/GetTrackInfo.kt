package com.mlcorrea.domain.iteractor.track

import com.mlcorrea.domain.executor.PostExecutionThread
import com.mlcorrea.domain.executor.ThreadExecutor
import com.mlcorrea.domain.iteractor.UseCase
import com.mlcorrea.domain.model.Track
import com.mlcorrea.domain.repository.PlatformRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by manuel on 08/04/19
 */
class GetTrackInfo @Inject constructor(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
    private val platformRepository: PlatformRepository
) : UseCase<Track, GetTrackInfo.Params>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Params): Observable<Track> {
        return platformRepository.getTrackInfo(params.artist,params.track)
    }

    data class Params(val track: String, val artist: String)
}