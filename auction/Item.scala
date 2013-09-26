package auction

//if extended, have to pass params to the implicit constructor
// class FineItem(fineName: String, finePrice: Double) extends
//    Item(fineName, finePrice)
class Item(val name: String, var reservePrice: Double) {

}

trait Auctionable {
  def shout() {
    println ("shout");
  }
}
