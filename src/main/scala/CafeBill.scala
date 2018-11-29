case class CafeBill(items: List[Item]) {
  val billContent: List[Item] = items
  
  def total: BigDecimal = {
    billContent match {
      case Nil => BigDecimal(0)
      case contentList: List[Item] =>
        contentList.map(_.price).sum.setScale(4, BigDecimal.RoundingMode.HALF_UP)
    }
  }
}

object CafeBill {
  def apply(items: Item*): CafeBill = new CafeBill(items.toList)
}
