package com.mlcorrea.lostparadisefm.framework.di.scope

import javax.inject.Scope

/**
 * Created by manuel on 06/04/19
 *
 * A scoping annotation to permit objects whose lifetime should
 * conform to the life of the activity to be memorized in the
 * correct component.
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PerActivity