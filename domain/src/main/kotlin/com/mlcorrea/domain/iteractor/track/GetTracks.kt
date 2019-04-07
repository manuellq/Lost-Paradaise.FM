package com.mlcorrea.domain.iteractor.track

import com.mlcorrea.domain.executor.PostExecutionThread
import com.mlcorrea.domain.executor.ThreadExecutor
import com.mlcorrea.domain.iteractor.UseCase
import com.mlcorrea.domain.model.TrackPage
import com.mlcorrea.domain.repository.PlatformRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by manuel on 07/04/19
 */
class GetTracks @Inject constructor(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
    private val platformRepository: PlatformRepository
) :
    UseCase<TrackPage, GetTracks.Params>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Params): Observable<TrackPage> {
        return platformRepository.getTracks(params.track, params.page.toString(), params.limit.toString())
    }

    data class Params(val track: String, val page: Int, val limit: Int)
}