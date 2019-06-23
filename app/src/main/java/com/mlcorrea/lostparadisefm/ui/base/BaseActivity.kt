package com.mlcorrea.lostparadisefm.ui.base

import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.mlcorrea.lostparadisefm.R
import com.mlcorrea.lostparadisefm.error.ErrorMessageFactory

/**
 * Created by manuel on 06/04/19
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    /*----------------PUBLIC METHOD--------*/

    fun addFragment(containerViewId: Int, fragment: Fragment) {
        supportFragmentManager.beginTransaction()
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