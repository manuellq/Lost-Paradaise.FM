package com.mlcorrea.domain.iteractor.artist

import com.mlcorrea.domain.executor.PostExecutionThread
import com.mlcorrea.domain.executor.ThreadExecutor
import com.mlcorrea.domain.iteractor.UseCase
import com.mlcorrea.domain.model.ArtistPage
import com.mlcorrea.domain.repository.PlatformRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by manuel on 07/04/19
 */
class GetArtist @Inject constructor(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
    private val platformRepository: PlatformRepository
) :
    UseCase<ArtistPage, GetArtist.Params>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Params): Observable<ArtistPage> {
        return platformRepository.getArtists(params.album, params.page.toString(), params.limit.toString())
    }

    data class Params(val album: String, val page: Int, val limit: Int)
}