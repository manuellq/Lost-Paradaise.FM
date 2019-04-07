package com.mlcorrea.domain.iteractor.album

import com.mlcorrea.domain.executor.PostExecutionThread
import com.mlcorrea.domain.executor.ThreadExecutor
import com.mlcorrea.domain.iteractor.UseCase
import com.mlcorrea.domain.model.AlbumPage
import com.mlcorrea.domain.repository.PlatformRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by manuel on 07/04/19
 */
class GetAlbums @Inject constructor(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
    private val platformRepository: PlatformRepository
) :
    UseCase<AlbumPage, GetAlbums.Params>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Params): Observable<AlbumPage> {
        return platformRepository.getAlbums(params.album, params.page.toString(), params.limit.toString())
    }

    data class Params(val album: String, val page: Int, val limit: Int)
}