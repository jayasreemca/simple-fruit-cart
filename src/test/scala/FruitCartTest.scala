import cart.model.{Apple, Banana, Orange}
import cart.service.FruitCart
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class FruitCartTest extends AnyFlatSpec with Matchers{

  "A shopping cart contents" should "be zero when empty" in {
    val emptyCart = FruitCart()
    emptyCart.contents.isEmpty should be ( true )
    emptyCart.price should be ( BigDecimal(0))
  }

  "The price for apple" should "be 0.60P" in {
    val cart = FruitCart().add( Apple() )
    cart.price should be ( BigDecimal( "0.60" ) )
  }

  "The price for orange" should "be 0.25P" in {
    val cart = FruitCart().add( Orange() )
    cart.price should be ( BigDecimal( "0.25" ) )
  }

  "The price for Banana" should "be 0.20P" in {
    val cart = FruitCart().add( Banana() )
    cart.price should be ( BigDecimal( "0.20" ) )
  }

  "A cart with [ Apple, Apple, Orange, Apple ]" should "be 2.05 Actual price" in {
    val cart = FruitCart().add( Apple() ).add( Apple() ).add( Orange() ).add( Apple() )
    cart.price should be ( BigDecimal("2.05"))
  }

  "A cart with Apples" should "give discount as 2 for 1" in {
    val cart = FruitCart().add(Apple()).add(Apple())
    cart.discountedPrice should be ( BigDecimal(0.60))
  }

  "A cart of oranges" should "have discount as 3 for 2 " in {
    val cart = FruitCart().add(Orange()).add(Orange()).add(Orange())
    cart.discountedPrice should be ( BigDecimal(0.50))
  }

  "A cart with Banana" should "give discount as 2 for 1[Buy One get One Free offer]" in {
    val cart = FruitCart().add(Banana()).add(Banana())
    cart.discountedPrice should be ( BigDecimal(0.20))
  }

  "A cart with Banana and Apple" should "Banana is free" in {
    val cart = FruitCart().add(Apple()).add(Banana()).add(Banana())
    cart.discountedPrice should be ( BigDecimal(0.80))
  }

}
