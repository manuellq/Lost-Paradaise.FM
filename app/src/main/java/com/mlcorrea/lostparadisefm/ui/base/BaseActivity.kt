package com.mlcorrea.lostparadisefm.ui.base

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.mlcorrea.lostparadisefm.R
import com.mlcorrea.lostparadisefm.error.ErrorMessageFactory
import com.mlcorrea.lostparadisefm.framework.di.module.base.BaseActivityModule
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by manuel on 06/04/19
 */
abstract class BaseActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    @field:Named(BaseActivityModule.ACTIVITY_FRAGMENT_MANAGER)
    lateinit var fragmentManagerB: FragmentManager
    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var viewModelFactoryBis: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    /*------------------FOR DEPENDENCY INJECTION-----------------*/

    override fun supportFragmentInjector(): AndroidInjector<Fragment>? {
        return fragmentInjector
    }

    /*----------------PUBLIC METHOD--------*/

    fun addFragment(containerViewId: Int, fragment: Fragment) {
        fragmentManagerB.beginTransaction()
            .replace(containerViewId, fragment)
            .commit()
    }

    fun setToolbar() {
        val toolbar = findViewById<View>(R.id.toolbar) as? Toolbar
        toolbar?.let {
            setSupportActionBar(toolbar)
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowTitleEnabled(true)
            }
        }
    }

    /**
     * Set the title in the Toolbar
     *
     * @param title [String]
     */
    fun setTitleToolBar(titleToolbar: String?) {
        supportActionBar?.apply {
            title = titleToolbar ?: ""
        }
    }

    /**
     * get the error message
     *
     * @param exception [Exception]
     * @return [String]
     */
    fun getUserMessageError(exception: Exception?): String {
        return ErrorMessageFactory.createFromException(this, exception)
    }
}