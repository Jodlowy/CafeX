import FoodTemperature._
import ItemType._
import org.scalatest.{FunSpec, Matchers}

class CafeBillTest extends FunSpec with Matchers {

  describe("Creating") {
    describe("empty bill") {
      val bill = CafeBill()

      it("should contain no items") {
        bill.billContent should be(empty)
      }
      it("should not charge anything") {
        bill.total should be(0)
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
    describe("for bill with cold food should apply 10%") {
      val bill = CafeBill(Coffee, CheeseSandwich)
      val totalWithCharge = Coffee.price + CheeseSandwich.price + ((Coffee.price + CheeseSandwich.price) * 0.1)
      bill.total should be(totalWithCharge)
    }
    describe("for bill with hot food should apply 20%") {
      val bill = CafeBill(Coffee, SteakSandwich)
      val totalWithCharge = Coffee.price + SteakSandwich.price + ((Coffee.price + SteakSandwich.price) * 0.2)
      bill.total should be(totalWithCharge)
    }
  }
  describe("for bill where charge exceeds 20 pounds should add just that") {
    //creating special Items otherwise it would have to be massive for such a Sandwich Party :)
    val bill = CafeBill(Item(Food, 55, Hot), Item(Food, 55, Hot))

    val totalWithCharge = (55 * 2) + 20
    bill.total should be(totalWithCharge)
  }
}