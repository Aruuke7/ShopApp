package kg.geektech.shopapp.data

import kg.geektech.shopapp.domain.ShopItem
import kg.geektech.shopapp.domain.ShopListRepository

class ShopListRepositoryImpl : ShopListRepository {

    private val shopList = mutableListOf<ShopItem>()
    private var autoIncrementId = 0

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
    }

    override fun deleteShopItem() {
        shopList.removeFirst()
    }

    override fun editShopItem(shopItem: ShopItem) {
            shopList[0] = shopItem
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList[shopItemId]
    }

    override fun getShopList(): List<ShopItem> = shopList.toList()
}