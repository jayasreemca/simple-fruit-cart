package service

import model.{Apple, FruitPrice, Orange}

case class ShoppingCart( contents: List[FruitPrice] = List.empty ) {
  def add( fruitPrice: FruitPrice ) : ShoppingCart = {
    ShoppingCart( fruitPrice :: contents )
  }

  def price: BigDecimal = {
    contents.map( product => product.price ).sum
  }

  def discountedPrice: BigDecimal = {
    val discountOnApples = discountProductOnQuantity( contents.filter( _.isInstanceOf[Apple] ), 2 )
    val discountOnOranges = discountProductOnQuantity( contents.filter( _.isInstanceOf[Orange] ), 3 )
    contents.map( product => product.price ).sum - discountOnApples - discountOnOranges
  }

  def discountProductOnQuantity( fruitPrice: Seq[FruitPrice], quantity: Int ) : BigDecimal = {
    fruitPrice.take( fruitPrice.size / quantity ).map( _.price ).sum
  }
}
