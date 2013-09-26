package auction

import scala.collection.mutable.HashMap
import scala.collection.mutable.Map

trait Store {
  def getKeyStore(): Map[Long, Item];
}

class MockStore extends Store {
  private var map = new HashMap[Long, Item](); 
  def getKeyStore(): Map[Long, Item] = {
    return map;
  }
}
