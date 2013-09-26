package auction

object TestProgram {
  val store = new MockStore();
  val a = new Auctioneer(store, MockUID);

	def run() = {
		println("Hello, world")
	}
}
