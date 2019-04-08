package com.mlcorrea.lostparadisefm.framework.network

import android.content.Context
import com.mlcorrea.data.repository.PlatformRepositoryImpl
import com.mlcorrea.domain.repository.PlatformRepository
import com.mlcorrea.lostparadisefm.framework.retrofit.apimanager.ApiManager
import com.mlcorrea.lostparadisefm.framework.retrofit.repository.ApiControllerImpl

/**
 * Created by manuel on 06/04/19
 */
object Injection {


    fun providePlatformRepositoryImpl(
        context: Context,
        apiManager: ApiManager
    ): PlatformRepository {
        return PlatformRepositoryImpl(ApiControllerImpl(apiManager))
    }

}