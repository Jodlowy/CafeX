import FoodTemperature.Hot
import ItemType.Food

case class CafeBill(items: List[Item]) {
  val billContent: List[Item] = items
  private val maxServiceCharge: BigDecimal = 20

  def total: BigDecimal = {
    val itemsPrice = billContent match {
      case Nil => BigDecimal(0)
      case contentList: List[Item] =>
        contentList.map(_.price).sum.setScale(4, BigDecimal.RoundingMode.HALF_UP)
    }
    applyService(itemsPrice)
  }

  private def serviceCharge: BigDecimal = {
    val foodItems = billContent.filter(_.itemType == Food)
    if (foodItems.isEmpty) 0 else if (foodItems.exists(_.foodTemperature == Hot))
      0.2 else 0.1
  }

  private def applyService(itemsPrice: BigDecimal): BigDecimal = {
    val charge = itemsPrice * serviceCharge
    if (charge > maxServiceCharge) itemsPrice + maxServiceCharge else itemsPrice + charge
  }
}

object CafeBill {
  def apply(items: Item*): CafeBill = new CafeBill(items.toList)
}
