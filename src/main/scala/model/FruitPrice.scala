package model

sealed class FruitPrice( val name: String, val price: BigDecimal )

case class Apple() extends FruitPrice( "Apple", BigDecimal("0.60") )
case class Orange() extends FruitPrice( "Orange", BigDecimal("0.25") )
