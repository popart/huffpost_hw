package com.auction

trait UIDGenerator {
  def getUID(): String;
}

object MockUID extends UIDGenerator {
  private var i = 0l;
  def getUID(): String = {
    i = i+1;
    return i.toString();
  }
}
