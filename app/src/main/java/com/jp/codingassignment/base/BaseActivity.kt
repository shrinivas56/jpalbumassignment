package com.jp.codingassignment.base


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModelProviders

/**
 * Created by Shrinivas Pai on 23/07/21.
 */
abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel> : AppCompatActivity(),
    LifecycleObserver {

    protected lateinit var binding: T
    protected lateinit var viewModel: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        viewModel = ViewModelProviders.of(this).get(getModel())

        binding.lifecycleOwner = this
    }

    protected fun setToolbar(toolbar: androidx.appcompat.widget.Toolbar) {
        toolbar.setTitleTextColor(ContextCompat.getColor(this, android.R.color.white))
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    protected val setTitleForActivity = {title:String -> supportActionBar?.setTitle(title) }

    //this method is used by fragment to add title on toolbar if dynamic data
    fun setToolbarTitle(title: String) = supportActionBar?.setTitle(title)

    //this method is used by fragment to add title on toolbar if static data
    fun setToolbarTitle(title: Int) = supportActionBar?.setTitle(title)



    protected abstract fun getLayoutId(): Int
    protected abstract fun getModel(): Class<V>


}