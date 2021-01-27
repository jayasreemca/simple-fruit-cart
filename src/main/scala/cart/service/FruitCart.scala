package cart.service

import cart.model.{Apple, Banana, FruitPrice, Orange}

case class FruitCart(contents: List[FruitPrice] = List.empty ) {
  def add( fruitPrice: FruitPrice ) : FruitCart = {
    FruitCart( fruitPrice :: contents )
  }

  def price: BigDecimal = {
    contents.map( product => product.price ).sum
  }

  def discountedPrice: BigDecimal = {
    //val discountOnApples = discountProductOnQuantity( contents.filter( _.isInstanceOf[Apple] ), 2 )
    val discountOnOranges = discountProductOnQuantity( contents.filter( _.isInstanceOf[Orange] ), 3 )
    //val discountOnBanana = discountProductOnQuantity( contents.filter(_.isInstanceOf[Banana]), 2)
    val cheapestFreeInAppleAndBanana = contents.filter( p => p.isInstanceOf[Apple] || p.isInstanceOf[Banana]).sortBy(f => f.price)
    val discountOnCheapestFree = discountProductOnQuantity(cheapestFreeInAppleAndBanana, 2)
    contents.map( product => product.price ).sum - discountOnCheapestFree - discountOnOranges
  }

  def discountProductOnQuantity( fruitPrice: Seq[FruitPrice], quantity: Int ) : BigDecimal = {
    fruitPrice.take( fruitPrice.size / quantity ).map( _.price ).sum
  }
}
