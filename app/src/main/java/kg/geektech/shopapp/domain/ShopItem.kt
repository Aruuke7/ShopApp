package kg.geektech.shopapp.domain

data class ShopItem(
    var name:String,
    val count:Int = COUNT,
    val enabled:Boolean,
    var id:Int = UNDEFINED_ID
){
    companion object{
        const val UNDEFINED_ID = -1
        const val COUNT = 2
    }
}

