package com.auction

import scala.collection.mutable.HashMap
import scala.collection.mutable.Map

trait KeyValStore {
  def get(uid: String): Option[Item];
  def put(uid: String, item: Item): Option[Item];
}

class MockKeyValStore extends KeyValStore {
  private var mysteryImpl = new HashMap[String, Item]();
  def get(uid: String) = mysteryImpl.get(uid);
  def put(uid: String, item: Item): Option[Item] = mysteryImpl.put(uid, item);
}
