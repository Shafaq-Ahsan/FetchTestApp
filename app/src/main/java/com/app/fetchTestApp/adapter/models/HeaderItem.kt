package com.app.fetchTestApp.adapter.models

class HeaderItem(
    val listId: Int?
) : ListItem() {

    // here getters and setters
    // for title and so on, built
    // using date
    override fun getType(): Int {
        return TYPE_HEADER
    }
}