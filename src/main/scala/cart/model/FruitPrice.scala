package cart.model

sealed class FruitPrice( val name: String, val price: BigDecimal )

case class Apple() extends FruitPrice( "Apple", BigDecimal("0.60") )
case class Orange() extends FruitPrice( "Orange", BigDecimal("0.25") )
case class Banana() extends FruitPrice( "Banana", BigDecimal("0.20") )
