package com.auction

object Test {
  val itemStore = new MockKeyValStore();
  val a1 = new Auctioneer(itemStore);
  val a2 = new Auctioneer(itemStore);
  val p1 = new Participant(itemStore);
  val p2 = new Participant(itemStore);
  val p3 = new Participant(itemStore);
  val p4 = new Participant(itemStore);
  val p5 = new Participant(itemStore);

  val i1 = new Item(MockUID.getUID(), 10.0);
  val n1 = i1.name;

  val i2 = new Item(MockUID.getUID(), 10.0);
  val n2 = i2.name;

  val i3 = new Item(MockUID.getUID(), 10.0);
  val n3 = i3.name;

	def run() = {
    a1.addItem(i1);
    a1.addItem(i2);
    a2.addItem(i3);

    println("Querying Item before Auction");
    println(p1.queryItem(n1));
    println(p1.queryAuction(n1));

    println("Bad Auction Starts");
    println(a1.startAuction(i3));
    println(a2.startAuction(i1));
    
    println("Good Auction Starts");
    println(a1.startAuction(i1));
    println(a2.startAuction(i3));

    println("Bidding");  
    p1.bid(i1, 2.5);
    p2.bid(i1, 2.0);
    p3.bid(i1, 3.0);

    p4.bid(i2, 4.0);

    p5.bid(i3, 11.0);
    a1.bid(i3, 10.0);

    println("Query Open Auction");
    auctionPrint(p1.queryAuction(n1));
    auctionPrint(p1.queryAuction(n2));
    auctionPrint(p1.queryAuction(n3));

    a1.stopAuction(i1);
    a1.stopAuction(i3);
    println("Query Closed Auction");
    auctionPrint(p1.queryAuction(n1));
    auctionPrint(p1.queryAuction(n2));
    auctionPrint(p1.queryAuction(n3));

    a2.stopAuction(i3);
    auctionPrint(a2.queryAuction(n3));

    def auctionPrint(o: Option[Auction]) = {
      o match {
        case Some(a) => {
          println("Item: "+a.item.name);
          println("Current Price: "+a.currentPrice); 
          println("Buyer?: "+a.buyer);
          println("Price Sold?: "+a.priceSold);
          println("Success?: "+a.success);

        }
        case None => println("no auction");
      }
    }


	}
}
