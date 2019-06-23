package com.mlcorrea.lostparadisefm.framework.retrofit.repository

import com.mlcorrea.data.dto.*
import com.mlcorrea.data.dto.base.NetworkResponseDTO
import com.mlcorrea.data.dto.model.AlbumDto
import com.mlcorrea.data.dto.model.ArtistDTO
import com.mlcorrea.data.dto.model.TrackInfoDTO
import com.mlcorrea.data.network.ApiController
import com.mlcorrea.domain.exception.APIError
import com.mlcorrea.domain.model.*
import com.mlcorrea.lostparadisefm.UnitTest
import com.mlcorrea.lostparadisefm.framework.retrofit.apimanager.ApiManager
import com.mlcorrea.lostparadisefm.framework.retrofit.service.ApiService
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test

/**
 * Created by manuel on 08/04/19
 */
class ApiControllerImplTest : UnitTest() {

    @MockK
    lateinit var apiManager: ApiManager
    @MockK
    lateinit var apiService: ApiService


    private lateinit var apiController: ApiController

    @Before
    fun setUp() {
        apiController = ApiControllerImpl(apiManager)
        every { apiManager.apiServices }.returns(apiService)
    }

    @Test
    fun `get albums but there is not response from backend`() {
        //Given
        val testObserver = TestObserver<Any>()
        val input: NetworkResponseDTO<AlbumsResponseDTO> = NetworkResponseDTO(null, 2, "Error")
        every { apiService.getAlbums("", "", "") }.returns(Observable.just(input))
        //When
        val result = apiController.getAlbums("", "", "")
        result.subscribe(testObserver)
        //Then
        testObserver.assertError { error: Throwable -> error is APIError }
        testObserver.assertNotComplete()
        testObserver.assertNoValues()
    }

    @Test
    fun `get albums but there is  response from backend`() {
        //Given
        val testObserver = TestObserver<Any>()
        val albumsDto = mockk<AlbumsResponseDTO>()
        val albums = mockk<AlbumPage>()
        val input: NetworkResponseDTO<AlbumsResponseDTO> = NetworkResponseDTO(albumsDto, null, null)
        every { albumsDto.unwrapDto() }.returns(albums)
        every { apiService.getAlbums("", "", "") }.returns(Observable.just(input))
        //When
        val result = apiController.getAlbums("", "", "")
        result.subscribe(testObserver)
        //Then
        testObserver.assertNoErrors()
        testObserver.assertComplete()
    }

    @Test
    fun `get artist but there is not response from backend`() {
        //Given
        val testObserver = TestObserver<Any>()
        val input: NetworkResponseDTO<ArtistsResponseDTO> = NetworkResponseDTO(null, 2, "Error")
        every { apiService.getArtists("", "", "") }.returns(Observable.just(input))
        //When
        val result = apiController.getArtist("", "", "")
        result.subscribe(testObserver)
        //Then
        testObserver.assertError { error: Throwable -> error is APIError }
        testObserver.assertNotComplete()
        testObserver.assertNoValues()
    }

    @Test
    fun `get artist but there is  response from backend`() {
        //Given
        val testObserver = TestObserver<Any>()
        val artistDto = mockk<ArtistsResponseDTO>()
        val artist = mockk<ArtistPage>()
        val input: NetworkResponseDTO<ArtistsResponseDTO> = NetworkResponseDTO(artistDto, null, null)
        every { artistDto.unwrapDto() }.returns(artist)
        every { apiService.getArtists("", "", "") }.returns(Observable.just(input))
        //When
        val result = apiController.getArtist("", "", "")
        result.subscribe(testObserver)
        //Then
        testObserver.assertNoErrors()
        testObserver.assertComplete()
    }

    @Test
    fun `get track but there is not response from backend`() {
        //Given
        val testObserver = TestObserver<Any>()
        val input: NetworkResponseDTO<TracksResponseDTO> = NetworkResponseDTO(null, 2, "Error")
        every { apiService.getTracks("", "", "") }.returns(Observable.just(input))
        //When
        val result = apiController.getTrack("", "", "")
        result.subscribe(testObserver)
        //Then
        testObserver.assertError { error: Throwable -> error is APIError }
        testObserver.assertNotComplete()
        testObserver.assertNoValues()
    }

    @Test
    fun `get track but there is  response from backend`() {
        //Given
        val testObserver = TestObserver<Any>()
        val trackDto = mockk<TracksResponseDTO>()
        val track = mockk<TrackPage>()
        val input: NetworkResponseDTO<TracksResponseDTO> = NetworkResponseDTO(trackDto, null, null)
        every { trackDto.unwrapDto() }.returns(track)
        every { apiService.getTracks("", "", "") }.returns(Observable.just(input))
        //When
        val result = apiController.getTrack("", "", "")
        result.subscribe(testObserver)
        //Then
        testObserver.assertNoErrors()
        testObserver.assertComplete()
    }

    @Test
    fun `get album info but there is not response from backend`() {
        //Given
        val testObserver = TestObserver<Any>()
        val input = AlbumInfoResponseDTO(null, 2, "Error")
        every { apiService.getAlbumInfo("", "") }.returns(Observable.just(input))
        //When
        val result = apiController.getAlbumInfo("", "")
        result.subscribe(testObserver)
        //Then
        testObserver.assertError { error: Throwable -> error is APIError }
        testObserver.assertNotComplete()
        testObserver.assertNoValues()
    }

    @Test
    fun `get album info but there is  response from backend`() {
        //Given
        val testObserver = TestObserver<Any>()
        val albumDto = mockk<AlbumDto>()
        val album = mockk<Album>()
        val input = AlbumInfoResponseDTO(albumDto, null, null)
        every { albumDto.unwrapDto() }.returns(album)
        every { apiService.getAlbumInfo("", "") }.returns(Observable.just(input))
        //When
        val result = apiController.getAlbumInfo("", "")
        result.subscribe(testObserver)
        //Then
        testObserver.assertNoErrors()
        testObserver.assertComplete()
    }

    @Test
    fun `get artist info but there is not response from backend`() {
        //Given
        val testObserver = TestObserver<Any>()
        val input = ArtistInfoResponseDTO(null, 2, "Error")
        every { apiService.getArtistInfo("") }.returns(Observable.just(input))
        //When
        val result = apiController.getArtistInfo("")
        result.subscribe(testObserver)
        //Then
        testObserver.assertError { error: Throwable -> error is APIError }
        testObserver.assertNotComplete()
        testObserver.assertNoValues()
    }

    @Test
    fun `get artist info but there is  response from backend`() {
        //Given
        val testObserver = TestObserver<Any>()
        val artistDto = mockk<ArtistDTO>()
        val artist = mockk<Artist>()
        val input = ArtistInfoResponseDTO(artistDto, null, null)
        every { artistDto.unwrapDto() }.returns(artist)
        every { apiService.getArtistInfo("") }.returns(Observable.just(input))
        //When
        val result = apiController.getArtistInfo("")
        result.subscribe(testObserver)
        //Then
        testObserver.assertNoErrors()
        testObserver.assertComplete()
    }


    @Test
    fun `get track info but there is not response from backend`() {
        //Given
        val testObserver = TestObserver<Any>()
        val input = TrackInfoResponseDTO(null, 2, "Error")
        every { apiService.getTrackInfo("", "") }.returns(Observable.just(input))
        //When
        val result = apiController.getTrackInfo("", "")
        result.subscribe(testObserver)
        //Then
        testObserver.assertError { error: Throwable -> error is APIError }
        testObserver.assertNotComplete()
        testObserver.assertNoValues()
    }

    @Test
    fun `get track info but there is  response from backend`() {
        //Given
        val testObserver = TestObserver<Any>()
        val trackDto = mockk<TrackInfoDTO>()
        val track = mockk<Track>()
        val input = TrackInfoResponseDTO(trackDto, null, null)
        every { trackDto.unwrapDto() }.returns(track)
        every { apiService.getTrackInfo("","") }.returns(Observable.just(input))
        //When
        val result = apiController.getTrackInfo("","")
        result.subscribe(testObserver)
        //Then
        testObserver.assertNoErrors()
        testObserver.assertComplete()
    }
}