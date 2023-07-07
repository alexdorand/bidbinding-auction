package com.bidbinding.auction.engine.adapter.driven.service;

import com.bidbinding.auction.engine.adapter.common.DataAccessAdapter;
import com.bidbinding.auction.engine.adapter.driven.dto.Bid;
import com.bidbinding.auction.engine.adapter.driven.dto.ItemForward;
import com.bidbinding.auction.engine.adapter.driver.dto.BidAdapter;
import com.bidbinding.auction.engine.adapter.driver.dto.ItemAdaptor;
import com.bidbinding.auction.engine.adapter.driver.dto.ItemDto;
import com.bidbinding.auction.engine.application.core.model.item.ForwardAuctionItem;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@DataAccessAdapter
@AllArgsConstructor
public class AuctionRepositoryImpl implements AuctionRepository {

    private final ItemRepository itemRepository;
    private final BidRepository bidRepository;

    public ForwardAuctionItem getForwardAuctionItem(String itemId) {
//        Optional<> itemForward = itemRepository.getItemByItemId(itemId);
//        ForwardAuctionItem item = ItemAdaptor.toForward(itemForward);
//        List<Bid> bids = bidRepository.getBidsForItem(itemForward.getId());
//        item.setBids(BidAdapter.adapt(bids));
        return null;
    }

    @Override
    public void updateForwardItem(ForwardAuctionItem item) {
        System.out.println("Update item in repository");
        ItemDto itemDto = ItemAdaptor.from(item);
        //auctionJpaService.updateItem(itemDto);
    }

}
