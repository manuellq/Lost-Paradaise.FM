package com.mlcorrea.domain.iteractor.album

import com.mlcorrea.domain.executor.PostExecutionThread
import com.mlcorrea.domain.executor.ThreadExecutor
import com.mlcorrea.domain.iteractor.UseCase
import com.mlcorrea.domain.model.Album
import com.mlcorrea.domain.repository.PlatformRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by manuel on 08/04/19
 */
class GetAlbumInfo @Inject constructor(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
    private val platformRepository: PlatformRepository
) :
    UseCase<Album, GetAlbumInfo.Params>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Params): Observable<Album> {
        return platformRepository.getAlbumInfo(params.artist, params.album)
    }

    data class Params(val album: String, val artist: String)
}