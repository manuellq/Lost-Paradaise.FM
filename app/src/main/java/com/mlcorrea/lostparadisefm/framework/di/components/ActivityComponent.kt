package com.mlcorrea.lostparadisefm.framework.di.components

import com.mlcorrea.lostparadisefm.framework.di.module.ActivityModule
import com.mlcorrea.lostparadisefm.framework.di.scope.PerActivity
import dagger.Component

/**
 * Created by manuel on 06/04/19
 */
@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

}