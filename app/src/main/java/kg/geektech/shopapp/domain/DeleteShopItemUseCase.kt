package kg.geektech.shopapp.domain

class DeleteShopItemUseCase(private val shopListRepository: ShopListRepository) {

    fun deleteShopItem(){
        shopListRepository.deleteShopItem()
    }
}