package auction

class Participant() {
}

class Auctioneer(store: Store, uider: UIDGenerator) extends Participant {
  val keyStore = store.getKeyStore();
  def addItem(item: Item) = {
      keyStore.put(uider.getUID(), item);
  }

}
