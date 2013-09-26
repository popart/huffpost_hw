package auction

trait UIDGenerator {
  def getUID(): Long;
}

object MockUID extends UIDGenerator {
  private var i = 0l;
  def getUID(): Long = {
    i = i+1;
    return i;
  }
}
