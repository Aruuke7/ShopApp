package kg.geektech.shopapp.presentation

import androidx.lifecycle.ViewModel
import kg.geektech.shopapp.data.ShopListRepositoryImpl
import kg.geektech.shopapp.domain.*

class MainViewModel:ViewModel() {

    private val shopListRepositoryImpl = ShopListRepositoryImpl()
    private val addShopItemUseCase = AddShopItemUseCase(shopListRepositoryImpl)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(shopListRepositoryImpl)
    private val editShopItemUseCase = EditShopItemUseCase(shopListRepositoryImpl)
    private val getShopItemUseCase = GetShopItemUseCase(shopListRepositoryImpl)
    private val getShopListUseCase = GetShopListUseCase(shopListRepositoryImpl)

    val shopListLD = getShopListUseCase.getShopList()

    fun addShopItem(shopItem: ShopItem){
        addShopItemUseCase.addShopItem(shopItem)
    }

    fun deleteShopItem(shopItem: ShopItem){
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun editShopItem(shopItem: ShopItem){
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
    }

    fun getShopItem(id:Int): ShopItem {
        return getShopItemUseCase.getShopItem(id)
    }



}