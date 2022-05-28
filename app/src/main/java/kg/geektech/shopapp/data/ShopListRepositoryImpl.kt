package kg.geektech.shopapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kg.geektech.shopapp.domain.ShopItem
import kg.geektech.shopapp.domain.ShopListRepository
import java.lang.RuntimeException

class ShopListRepositoryImpl : ShopListRepository {

    private val shopList = sortedSetOf<ShopItem>({element1,element2-> element1.id.compareTo(element2.id)})
    private var autoIncrementId = 0
    private val shopListLD = MutableLiveData<List<ShopItem>>()

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
        updateList()
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateList()
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldShopItem = getShopItem(shopItem.id)
        shopList.remove(oldShopItem)
        addShopItem(shopItem)
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find {item->
            item.id == shopItemId
        }?: throw RuntimeException("not found found id")
    }

    override fun getShopList(): LiveData<List<ShopItem>>{
        return shopListLD
    }

    fun updateList(){
        shopListLD.value = shopList.toList()
    }
}