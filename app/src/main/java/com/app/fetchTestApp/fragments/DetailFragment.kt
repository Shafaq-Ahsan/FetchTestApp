package com.app.fetchTestApp.fragments


import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.app.fetchTestApp.R
import com.app.fetchTestApp.viewmodels.MainActivityViewModel
import androidx.navigation.fragment.findNavController
import com.app.fetchTestApp.adapter.models.EventItem
import com.app.fetchTestApp.adapter.models.ListItem
import com.app.fetchTestApp.databinding.FragmentDetailBinding
import com.app.fetchTestApp.utils.*
import kotlin.collections.ArrayList

internal class DetailFragment : BaseFragment() {

    private var position: Int? = 0 // position from arguments to show detail item
    private lateinit var mBinding: FragmentDetailBinding
    private lateinit var mViewModel: MainActivityViewModel
    private var dataList = ArrayList<ListItem>()


    override fun getFragmentLayout() = R.layout.fragment_detail

    override fun getViewBinding() {
        mBinding = binding as FragmentDetailBinding
    }


    override fun getViewModel() {
        mViewModel = ViewModelProviders.of(requireActivity()).get(MainActivityViewModel::class.java)
    }

    override fun observe() {
    }


    override fun setLiveDataValues() {
        //set responsedata value from livedata
        mViewModel.responseData.value?.let {
            setData(it)
        }
    }

    private fun setData(it: ArrayList<ListItem>) {
        //set data to binding and adapter
        dataList = it
        val dataItem = position?.let { it1 -> dataList[it1] } as EventItem
        mBinding.dataResponse = dataItem.getEvent()

    }

    override fun init() {
        position = arguments?.getInt(Enums.Ids.POSITION.key) ?: 0
    }

    override fun setListeners() {
        //back nav button listener
        mBinding.navIcon.setOnClickListener {
            findNavController().navigateUp()
        }
    }


    override fun setLanguageData() {

    }

    override fun onClick(v: View?) {
    }


}


