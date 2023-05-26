package com.app.fetchTestApp.adapter.models

abstract class ListItem {
    abstract fun getType(): Int

    companion object {
        const val TYPE_HEADER = 0
        const val TYPE_EVENT = 1
    }
}