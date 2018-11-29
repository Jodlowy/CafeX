import ItemType.Food

case class CafeBill(items: List[Item]) {
  val billContent: List[Item] = items

  def total: BigDecimal = {
    val itemsPrice = billContent match {
      case Nil => BigDecimal(0)
      case contentList: List[Item] =>
        contentList.map(_.price).sum.setScale(4, BigDecimal.RoundingMode.HALF_UP)
    }
    applyService(itemsPrice)
  }

  private def applyService(itemsPrice: BigDecimal): BigDecimal = {
    val foodItems = billContent.filter(_.itemType == Food)
    if (foodItems.isEmpty) itemsPrice else itemsPrice + (itemsPrice * 0.1)
  }
}

object CafeBill {
  def apply(items: Item*): CafeBill = new CafeBill(items.toList)
}
