package com.bidbinding.auction.engine.application.core.usecase;

import com.bidbinding.auction.engine.application.core.common.UseCase;
import com.bidbinding.auction.engine.application.core.model.bid.Bid;
import com.bidbinding.auction.engine.application.core.model.bid.ItemBidCommand;
import com.bidbinding.auction.engine.application.core.model.bid.BidPlacementStatus;
import com.bidbinding.auction.engine.application.core.model.item.AuctionType;
import com.bidbinding.auction.engine.application.port.driven.ItemPort;
import lombok.AllArgsConstructor;

@UseCase
@AllArgsConstructor
public class PlaceBidUsecase {

    private ForwardAuctionUsecase forwardAuctionUsecase;
    private SealedBidsUsecase sealedBidsUsecase;
    private ReverseAuctionUsecase reverseAuctionUsecase;
    private ItemPort itemPort;


    public AuctionType detectAuctionType(String itemId) {
        return itemPort.getListingTypeFor(itemId);
    }

    public BidPlacementStatus placeBid(ItemBidCommand itemBidCommand) {

        AuctionType type = detectAuctionType(itemBidCommand.onItemId());
        switch (type) {
            case FORWARD_AUCTION -> {
                return forwardAuctionUsecase.placeBid(itemBidCommand.bid(), itemBidCommand.onItemId());
            }
            case REVERSE_AUCTION -> {

            }
            case SEALED_BID_AUCTION -> {

            }
        }
        return BidPlacementStatus.REJECTED;
    }
}
