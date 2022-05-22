package kg.geektech.shopapp.presentation.second

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kg.geektech.shopapp.R
import kg.geektech.shopapp.domain.ShopItem
import kg.geektech.shopapp.presentation.second.RecyclerViewAdapter.*

class RecyclerViewAdapter : ListAdapter<ShopItem, ViewHolderCustom>(ShopItemDiffCallback()) {

    inner class ViewHolderCustom(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val count: TextView = itemView.findViewById(R.id.text_view_count)
        val tvName: TextView = itemView.findViewById(R.id.text_view)
    }

    var shopItemClickListener: ((ShopItem) -> Unit)? = null
    var shopItemOnLongClickListener: ((ShopItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCustom {
        val layout = when (viewType) {
            VIEW_TYPE_ENABLE -> R.layout.item_recycler
            VIEW_TYPE_DISABLE -> R.layout.item_recycler_disable
            else -> throw RuntimeException("Неизвестная ошибка $viewType")
        }
        return ViewHolderCustom(LayoutInflater.from(parent.context).inflate(layout, parent, false))
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.enabled) VIEW_TYPE_ENABLE
        else VIEW_TYPE_DISABLE
    }

    override fun onBindViewHolder(holder: ViewHolderCustom, position: Int) {
        val shopItem = getItem(position)
        holder.tvName.text = shopItem.name
        holder.count.text = shopItem.count.toString()
        holder.itemView.setOnClickListener {
            shopItemClickListener?.invoke(shopItem)
        }
        holder.itemView.setOnLongClickListener {
            shopItemOnLongClickListener?.invoke(shopItem)
            return@setOnLongClickListener true
        }
    }

    companion object {
        const val VIEW_TYPE_ENABLE = 100
        const val VIEW_TYPE_DISABLE = 101
    }
}