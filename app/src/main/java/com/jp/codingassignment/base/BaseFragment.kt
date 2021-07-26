package com.jp.codingassignment.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModelProviders

/**
 * Created by Shrinivas Pai on 23/07/21.
 */
abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel> : Fragment(),LifecycleObserver {

    private lateinit var baseActivity: BaseActivity<*, *>
    protected lateinit var binding: T
    protected lateinit var viewModel: V

    override fun onAttach(context: Context) {
        super.onAttach(context)
        baseActivity = context as BaseActivity<*, *>
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, getLayout(), container, false)
        viewModel = ViewModelProviders.of(this).get(getModel())
        binding.lifecycleOwner = this
        return binding.root
    }

    protected fun setHomeEnabled(value: Boolean) {
        baseActivity.supportActionBar?.setDisplayHomeAsUpEnabled(value)
        baseActivity.supportActionBar?.setHomeButtonEnabled(value)
    }

    protected fun setToolbarTitle(title: String) = baseActivity.setToolbarTitle(title)

    protected fun setToolbarTitle(title: Int) = baseActivity.setToolbarTitle(title)


    protected abstract fun getLayout(): Int
    protected abstract fun getModel(): Class<V>

}