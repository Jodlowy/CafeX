case class CafeBill(items: List[Item]) {
  val billContent: List[Item] = items
}

object CafeBill {
  def apply(items: Item*): CafeBill = new CafeBill(items.toList)
}
