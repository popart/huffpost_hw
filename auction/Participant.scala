package com.auction

import scala.collection.mutable.{Set, HashSet}
import java.util.Date

class Participant(itemStore: KeyValStore) {
  def queryItem(uid: String) = itemStore.get(uid);
  def queryAuction(uid: String): Option[Auction] = {
    itemStore.get(uid) match {
      case Some(item) => item.auction; 
      case None => None;
    }
  }
  def bid(item: Item, bidPrice: Double): Option[Bid] = {
    item.auction match {
      case Some(a) => {
        val bid = BidFactory.makeBid(this, bidPrice);
        a.bid(bid);
      }
      case None => None;
    }
  }
}

class Auctioneer(itemStore: KeyValStore) extends Participant(itemStore) {
  var items = new HashSet[Item]();
  def addItem(item: Item) = {
    itemStore.put(item.name, item);
    items.add(item);
    item.auctioneer = Some(this);
  }
  def startAuction(item: Item): Option[Auction] = {
    if(item.auction.isEmpty && item.auctioneer == Some(this)) {
      item.auction = Some(new Auction(item));
      return item.auction;
    }
    else None;
  }
  def stopAuction(item: Item): Option[Auction] = {
    if(item.auction.nonEmpty && item.auctioneer == Some(this)) {
      item.auction.get.buyer = Some(item.auction.get.bids.head.bidder); 
      return item.auction;
    }
    else None;
  }
  //def startAuction(uid: String): Option[Auction]
  //def stopAuction(uid: String): Option[Auction]
}

class Bid(val bidder: Participant, val price: Double, val time: Date) extends Ordered[Bid] {
  def compare(that: Bid) = this.price.compare(that.price);
}

object BidFactory {
  def makeBid(bidder: Participant, price: Double): Bid = {
    new Bid(bidder, price, new Date());
  }
}
