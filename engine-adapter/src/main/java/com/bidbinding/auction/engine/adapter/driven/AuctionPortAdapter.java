package com.bidbinding.auction.engine.adapter.driven;

import com.bidbinding.auction.engine.adapter.driven.dto.Bid;
import com.bidbinding.auction.engine.adapter.driven.dto.ItemForward;
import com.bidbinding.auction.engine.adapter.driven.service.AuctionRepository;
import com.bidbinding.auction.engine.adapter.driven.service.BidRepository;
import com.bidbinding.auction.engine.adapter.driven.service.ItemRepository;
import com.bidbinding.auction.engine.adapter.driver.dto.BidAdapter;
import com.bidbinding.auction.engine.adapter.driver.dto.ItemAdaptor;
import com.bidbinding.auction.engine.application.core.model.item.ForwardAuctionItem;
import com.bidbinding.auction.engine.application.core.model.item.ItemBiddingType;
import com.bidbinding.auction.engine.application.port.driven.AuctionPort;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class AuctionPortAdapter implements AuctionPort {

//    private final AuctionRepository auctionRepository;
    private ItemRepository itemRepository;
    private BidRepository bidRepository;

    @Override
    public ItemBiddingType detectTypeForItem(String itemId) {
        return ItemBiddingType.FORWARD_AUCTION;
    }

    @Override
    public void updateForwardItem(ForwardAuctionItem item) {
        Optional<ItemForward> itemForward = itemRepository.getItemByItemId(item.getId());
        itemForward.ifPresent(itemForward1 -> {
            List<Bid> bids = item.getBids().stream()
                    .map(bid -> BidAdapter.adapt(bid, itemForward1.getId()))
                    .collect(Collectors.toList());
            bidRepository.saveAll(bids);
        });

    }


    @Override
    public void notifyTheWinner(String itemId) {

    }

    @Override
    public void notifyTheLosingParticipantsFor(String itemId) {

    }

    @Override
    public ForwardAuctionItem getForwardItem(String itemId) {
        ItemForward itemForward = itemRepository.getItemByItemId(itemId).get();
        List<Bid> bids = bidRepository.getBidsForItem(itemForward.getId());

        ForwardAuctionItem result = ItemAdaptor.toForward(itemForward);
        result.setBids(BidAdapter.adapt(bids));
        if(result.getBids().size()>0)
            result.setWinningBid(result.getBids().get(result.getBids().size()-1));
        return result;
    }
}
