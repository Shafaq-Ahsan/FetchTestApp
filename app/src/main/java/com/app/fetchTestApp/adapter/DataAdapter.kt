package com.app.fetchTestApp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.fetchTestApp.ApplicationClass
import com.app.fetchTestApp.adapter.models.EventItem
import com.app.fetchTestApp.adapter.models.HeaderItem
import com.app.fetchTestApp.adapter.models.ListItem
import com.app.fetchTestApp.databinding.HeaderItemBinding
import com.app.fetchTestApp.databinding.ItemListCardBinding
import com.app.fetchTestApp.databinding.ShimmerItemListBinding
import com.app.fetchTestApp.interfaces.BindHolder
import com.app.fetchTestApp.interfaces.ListClickListener
import com.app.fetchTestApp.utils.capitalizeWords

internal class DataAdapter(
    private val mContext: Context,
    private var list: ArrayList<ListItem>,
    private val mListClickLister: ListClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val LIST = 1
        const val SHIMMER = 2
        const val HEADER = 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            SHIMMER -> ShimmerListHolder.from(parent)
            HEADER -> HeaderListHolder.from(parent)
            else -> ListViewHolder.from(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BindHolder -> holder.bind(position, mListClickLister, mContext, list)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (list.size <= 0) {
            SHIMMER
        } else if (list[position].getType() == ListItem.TYPE_EVENT) {
            LIST
        } else {
            HEADER
        }
    }

    //show updated list from api or livedata
    fun setData(data: ArrayList<ListItem>) {
        this.list = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return if (list.size <= 0) 4 else list.size
    }

}

//List view holder
private class ListViewHolder(
    private val binding: ItemListCardBinding

) : RecyclerView.ViewHolder(binding.root), BindHolder {
    companion object {
        fun from(
            parent: ViewGroup
        ): RecyclerView.ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemListCardBinding.inflate(
                layoutInflater,
                parent,
                false
            )
            return ListViewHolder(binding)
        }
    }

    // to set views data
    override fun bind(
        position: Int,
        mListClickLister: ListClickListener,
        mContext: Context,
        list: ArrayList<ListItem>
    ) {
        val dataItem = list[position] as EventItem
        binding.tvTitle.text = dataItem.getEvent().name?.capitalizeWords() ?: ""
        itemView.setOnClickListener {
            it?.let {
                mListClickLister.onItemClick(position)
            }
        }
    }


}

//Shimmer view holder
private class ShimmerListHolder(binding: ShimmerItemListBinding) :
    RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun from(parent: ViewGroup): RecyclerView.ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ShimmerItemListBinding.inflate(
                layoutInflater,
                parent,
                false
            )
            return ShimmerListHolder(binding)
        }
    }
}

//Header view holder
private class HeaderListHolder(val binding: HeaderItemBinding) :
    RecyclerView.ViewHolder(binding.root), BindHolder {
    companion object {
        fun from(parent: ViewGroup): RecyclerView.ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = HeaderItemBinding.inflate(
                layoutInflater,
                parent,
                false
            )
            return HeaderListHolder(binding)
        }
    }

    override fun bind(
        position: Int,
        mListClickLister: ListClickListener,
        mContext: Context,
        list: ArrayList<ListItem>
    ) {
        binding.txtHeader.text = ApplicationClass.languageJson?.listingScreen?.headerTitle +" "+ (list[position] as HeaderItem).listId.toString()
    }
}