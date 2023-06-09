package com.app.fetchTestApp.fragments


import android.os.Handler
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.app.fetchTestApp.R
import com.app.fetchTestApp.databinding.FragmentSplashBinding
import com.app.fetchTestApp.utils.Constants.SPLASH_TIME_OUT
import com.app.fetchTestApp.viewmodels.MainActivityViewModel

internal class SplashFragment : BaseFragment() {

    private lateinit var mBinding: FragmentSplashBinding
    private lateinit var mViewModel: MainActivityViewModel

    override fun getFragmentLayout() = R.layout.fragment_splash

    override fun getViewBinding() {
        mBinding = binding as FragmentSplashBinding
    }

    override fun getViewModel() {
        mViewModel = ViewModelProviders.of(requireActivity()).get(MainActivityViewModel::class.java)
    }

    override fun observe() {

    }

    private fun navigateAhead() {
        findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
    }

    override fun setLiveDataValues() {

    }

    override fun init() {

    }

    override fun onResume() {
        super.onResume()
        Handler().postDelayed({
            navigateAhead()
        }, SPLASH_TIME_OUT)
    }
    override fun setListeners() {

    }

    override fun setLanguageData() {

    }

    override fun onClick(v: View?) {

    }
}


