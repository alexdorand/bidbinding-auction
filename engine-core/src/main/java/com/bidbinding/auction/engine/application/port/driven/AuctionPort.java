package com.bidbinding.auction.engine.application.port.driven;

import com.bidbinding.auction.engine.application.core.model.item.ForwardAuctionItem;
import com.bidbinding.auction.engine.application.core.model.item.ItemBiddingType;

public interface AuctionPort {

    ItemBiddingType detectTypeForItem(String itemId);
    void updateForwardItem(ForwardAuctionItem item);

    void notifyTheWinner(String itemId);

    void notifyTheLosingParticipantsFor(String itemId);

    ForwardAuctionItem getForwardItem(String itemId);
}
