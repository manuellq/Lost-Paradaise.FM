package com.mlcorrea.domain.iteractor.artist

import com.mlcorrea.domain.executor.PostExecutionThread
import com.mlcorrea.domain.executor.ThreadExecutor
import com.mlcorrea.domain.iteractor.UseCase
import com.mlcorrea.domain.model.Artist
import com.mlcorrea.domain.repository.PlatformRepository
import io.reactivex.Observable

/**
 * Created by manuel on 08/04/19
 */
class GetArtistInfo  constructor(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
    private val platformRepository: PlatformRepository
) :
    UseCase<Artist, GetArtistInfo.Params>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Params): Observable<Artist> {
        return platformRepository.getArtistInfo(params.artist)
    }

    data class Params(val artist: String)
}