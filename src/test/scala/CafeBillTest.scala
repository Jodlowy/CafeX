import org.scalatest.{FunSpec, Matchers}

class CafeBillTest extends FunSpec with Matchers {

  describe("Creating") {
    describe("empty bill") {
      val bill = CafeBill()

      it("should contain no items") {
        bill.billContent should be(empty)
      }
    }
    describe("bill with items") {
      val bill = CafeBill(Cola, Coffee)
      it("should contain items") {
        bill.billContent should not be empty
      }
      it("should contain given items") {
        bill.billContent should contain allOf(Cola, Coffee)
      }
    }
  }
  describe("Counting charge") {
    describe("for bill with only drinks should apply no extra charge") {
      val bill = CafeBill(Coffee, Cola)
      bill.total should be(Coffee.price + Cola.price)
    }
  }
}