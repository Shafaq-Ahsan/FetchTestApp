package com.app.fetchTestApp.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.fetchTestApp.adapter.DataAdapter
import com.app.fetchTestApp.adapter.models.ListItem

class Utils {
    companion object {
        @BindingAdapter("data")
        @JvmStatic
        fun dataList(
            recyclerView: RecyclerView,
            dataList: List<ListItem>?
        ) {
            dataList?.let {
                recyclerView.adapter?.let { (recyclerView.adapter as DataAdapter).setData(dataList as ArrayList<ListItem>) }
            }
        }
    }
}