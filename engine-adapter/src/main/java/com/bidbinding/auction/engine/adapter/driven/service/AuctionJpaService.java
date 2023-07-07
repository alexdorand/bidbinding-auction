package com.bidbinding.auction.engine.adapter.driven.service;

import com.bidbinding.auction.engine.adapter.common.DataAccessService;
import com.bidbinding.auction.engine.adapter.driver.dto.ItemDto;

@DataAccessService
public class AuctionJpaService {

    public ItemDto getItem(String itemId) {
        return null;
    }

    public void updateItem(ItemDto itemDto) {
        System.out.println("Update item in JPA Database");
    }
}
