package com.bidbinding.auction.engine.adapter.driven;

import com.bidbinding.auction.engine.adapter.driven.service.AuctionRepository;
import com.bidbinding.auction.engine.application.core.model.item.ForwardAuctionItem;
import com.bidbinding.auction.engine.application.core.model.item.ItemBiddingType;
import com.bidbinding.auction.engine.application.port.driven.AuctionPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class AuctionPortAdapter implements AuctionPort {

    private final AuctionRepository auctionRepository;

    @Autowired
    public AuctionPortAdapter(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }


    @Override
    public ItemBiddingType detectTypeForItem(String itemId) {
        return null;
    }

    @Override
    public void updateForwardItem(ForwardAuctionItem item) {
        System.out.println("Update item in adapter");
        auctionRepository.updateForwardItem(item);
    }


    @Override
    public void notifyTheWinner(String itemId) {

    }

    @Override
    public void notifyTheLosingParticipantsFor(String itemId) {

    }

    @Override
    public ForwardAuctionItem getForwardItem(String itemId) {
        return ForwardAuctionItem.builder()
                .id(UUID.randomUUID().toString())
                .bids(new ArrayList<>())
                .participantsLastBids(new ConcurrentHashMap<>())
                .reservedPrice(null)
                .winningBid(null)
                .build();
    }
}
