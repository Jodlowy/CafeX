import FoodTemperature._
import ItemType._

case class Item(itemType: ItemType, price: BigDecimal, foodTemperature: FoodTemperature)

object Cola extends Item(Drink, 0.5, Cold)
object Coffee extends Item(Drink, 1, Hot)
object CheeseSandwich extends Item(Food, 2, Cold)
object SteakSandwich extends Item(Food, 4.5, Hot)
