package kg.geektech.shopapp.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kg.geektech.shopapp.data.ShopListRepositoryImpl
import kg.geektech.shopapp.domain.*

class MainViewModel:ViewModel() {

    private val shopListRepositoryImpl = ShopListRepositoryImpl()
    val shopListLD = MutableLiveData<List<ShopItem>>()
    private val addShopItemUseCase = AddShopItemUseCase(shopListRepositoryImpl)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(shopListRepositoryImpl)
    private val editShopItemUseCase = EditShopItemUseCase(shopListRepositoryImpl)
    private val getShopItemUseCase = GetShopItemUseCase(shopListRepositoryImpl)
    private val getShopListUseCase = GetShopListUseCase(shopListRepositoryImpl)

    fun addShopItem(shopItem: ShopItem){
        addShopItemUseCase.addShopItem(shopItem)
    }

    fun deleteShopItem(){
        deleteShopItemUseCase.deleteShopItem()
    }

    fun editShopItem(shopItem: ShopItem){
        editShopItemUseCase.editShopItem(shopItem)
    }

    fun getShopItem(id:Int){
        getShopItemUseCase.getShopItem(id)
    }

    fun getShopList(){
        shopListLD.value = getShopListUseCase.getShopList()
    }

}