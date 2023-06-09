package com.app.fetchTestApp.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import com.app.fetchTestApp.R
import com.app.fetchTestApp.databinding.ActivityMainBinding
import com.app.fetchTestApp.ApplicationClass
import com.app.fetchTestApp.dialogs.LoaderFragment
import com.app.fetchTestApp.utils.DisplayNotification
import com.app.fetchTestApp.utils.NetworkAvailability
import com.app.fetchTestApp.viewmodels.MainActivityViewModel

class MainActivity : BaseActivity() {
    //main activity binding variable
    private lateinit var mBinding: ActivityMainBinding
    //viewModel variable
    private lateinit var mViewModel: MainActivityViewModel
    //nav controller
    private lateinit var mNavController: NavController
    private lateinit var mNavDestination: NavDestination

    //loader when observe is called
    private val loaderFragment by lazy { LoaderFragment() }

    //for network state check
    private var networkAvailability: NetworkAvailability? = null
    private val receiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            if (intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false)) {
                //to show message on top of screen
                mViewModel.callMessageNotification(
                    ApplicationClass.languageJson?.messages?.errorMessages?.internet ?: "",
                    DisplayNotification.STYLE.FAILURE
                )
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        //To register network availability broadcast listener
        networkAvailability = NetworkAvailability.instance
        networkAvailability?.registerNetworkAvailability(this, receiver)
    }

    private fun init() {
        mViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        initNavController()
        observe()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun initNavController() {
        mNavController = findNavController(R.id.nav_host_fragment)
        mNavController.apply {
            addOnDestinationChangedListener { _, destination, _ ->
                mNavDestination = destination
            }
        }
    }
    //baseActivity abstract method
    override fun getActivityLayout() = R.layout.activity_main

    //baseActivity abstract method
    override fun getViewBinding() {
        mBinding = binding as ActivityMainBinding
    }

    private fun observe() {
        mViewModel.apply {
            //loader receiver to show loader fragment
            loader.observe(this@MainActivity, Observer { showLoader ->
                showLoader ?: return@Observer
                if (showLoader) {
                    if (loaderFragment.isVisible) {
                        loaderFragment.dismiss()
                    }
                    loaderFragment.isCancelable = false
                    val ft = supportFragmentManager.beginTransaction()
                    val prev = supportFragmentManager.findFragmentByTag("dialog")
                    if (prev != null) {
                        ft.remove(prev)
                    }
                    ft.addToBackStack(null)
                    ft.let { loaderFragment.show(it, "dialog") }
                } else {
                    try {
                        loaderFragment.dismiss()
                    } catch (t: Throwable) {
                        t.printStackTrace()
                    }
                }

            })
            //custom message view to show messages
            notificationMessage.observe(this@MainActivity, Observer {
                it ?: return@Observer
                if (it.show) {
                    DisplayNotification.show(
                        this@MainActivity,
                        mBinding.notificationLayout,
                        it.style,
                        it.message
                    )
                    setNotificationMessage(it.copy(show = false))
                }
            })
        }
    }

    override fun onDestroy() {
        //unregister network receiver
        networkAvailability?.unregisterNetworkAvailability(this, receiver)
        super.onDestroy()
    }
}