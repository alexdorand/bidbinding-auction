package com.bidbinding.auction.engine.adapter.driven.service;

import com.bidbinding.auction.engine.adapter.driver.dto.ItemAdaptor;
import com.bidbinding.auction.engine.adapter.driver.dto.ItemDto;
import com.bidbinding.auction.engine.application.core.model.item.ForwardAuctionItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class AuctionRepositoryImpl implements AuctionRepository {

    @Autowired
    private AuctionJpaService auctionJpaService;

    public ForwardAuctionItem getForwardAuctionItem(String itemId) {
        ItemDto itemDto = auctionJpaService.getItem(itemId);
        return ItemAdaptor.toForward(itemDto);
    }

    @Override
    public void updateForwardItem(ForwardAuctionItem item) {
        System.out.println("Update item in repository");
        ItemDto itemDto = ItemAdaptor.from(item);
        auctionJpaService.updateItem(itemDto);
    }

}
