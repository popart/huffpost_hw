package com.auction

import scala.collection.mutable.PriorityQueue

class Item(val name: String, var reservePrice: Double) {
  var auction: Option[Auction] = None;
  var auctioneer: Option[Auctioneer] = None;
}

class Auction(val item: Item) {
  var buyer: Option[Participant] = None;
  var bids = new PriorityQueue[Bid]();

  def bid(bid: Bid): Option[Bid] = {
    if(buyer == None && bid.price > currentPrice) {
      bids += bid;
      return Some(bid);
    }
    else return None;
  }
  def currentPrice: Double = {
    if(bids.isEmpty) return 0;
    else return bids.head.price;
  }
  //def highestBidder
  def priceSold: Option[Double] = {
    buyer match {
      case Some(_) => Some(currentPrice);
      case None => None;
    }
  }
  def success: Option[Boolean] = {
    buyer match {
      case Some(_) => Some(currentPrice > item.reservePrice);
      case None => None;
    }
  }

}
