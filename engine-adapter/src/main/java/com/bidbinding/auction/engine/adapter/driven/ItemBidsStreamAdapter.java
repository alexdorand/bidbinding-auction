package com.bidbinding.auction.engine.adapter.driven;

import org.springframework.stereotype.Service;

@Service
public class ItemBidsStreamAdapter {

//    private final Map<String, BidStream> bidStream = new ConcurrentHashMap<>();
//
//
//    public Optional<List<ForwardBid>> findBidByItemId(String itemId) {
//        if(bidStream.keySet().contains(itemId)) {
//            return Optional.of(bidStream.get(itemId).getBids());
//        }
//        return Optional.empty();
//    }
//
//    public ForwardBid getLastBidFor(String itemId) {
//        return lastBids.get(itemId);
//    }
//
//    public int compareToLastBid(ForwardBid bid) {
////        if(lastBids.get(bid.getItem().getId()) != null) {
////            return lastBids.get(bid.getItem().getId()).getAmount().compareTo(bid.getAmount());
////        }
//        return -1;
//    }
//
//    public void addBidToItem(ForwardBid bid) {
//        //bidStream.putIfAbsent(bid.getItem().getId(),BidStream.builder().bids(new ArrayList<>()).build());
//        //bidStream.get(bid.getItem().getId()).getBids().add(bid);
//        //lastBids.put(bid.getItem().getId(), bid);
//    }
}
