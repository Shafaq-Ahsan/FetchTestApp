package com.app.fetchTestApp.interfaces

import android.content.Context
import com.app.fetchTestApp.adapter.models.ListItem

internal interface BindHolder {
    fun bind(
        position: Int,
        mListClickLister: ListClickListener,
        mContext: Context,
        list: ArrayList<ListItem>
    )

}