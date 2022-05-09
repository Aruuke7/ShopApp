package kg.geektech.shopapp.presentation.second

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.geektech.shopapp.databinding.ItemRecyclerBinding
import kg.geektech.shopapp.domain.ShopItem

class RecyclerViewAdapter(): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private val elements = mutableListOf<ShopItem>()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(element:String, count: Int){
        elements.add(ShopItem(element, count,true))
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(shopItem: ShopItem) {
            binding.textView.text = shopItem.name
            binding.textViewCount.text = shopItem.count.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        return ViewHolder(ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        holder.bind(elements[position])
    }

    override fun getItemCount(): Int {
        return elements.size
    }
}