package com.bidbinding.auction.engine.application.core.usecase;

import com.bidbinding.auction.engine.application.core.common.UseCase;
import com.bidbinding.auction.engine.application.core.model.bid.Bid;
import com.bidbinding.auction.engine.application.core.model.bid.ItemBidCommand;
import com.bidbinding.auction.engine.application.core.model.bid.PlaceBidResult;
import com.bidbinding.auction.engine.application.core.model.item.ItemBiddingType;
import com.bidbinding.auction.engine.application.port.driven.ItemPort;
import lombok.AllArgsConstructor;

@UseCase
@AllArgsConstructor
public class PlaceBidUsecase {

    private ForwardAuctionUsecase forwardAuctionUsecase;
    private SealedBidsUsecase sealedBidsUsecase;
    private ReverseAuctionUsecase reverseAuctionUsecase;
    private ItemPort itemPort;

    public boolean validate(Bid bid) {
        return true;
    }

    public ItemBiddingType detectAuctionType(String itemId) {
        return itemPort.detectTypeForItem(itemId);
    }

    public PlaceBidResult placeBid(ItemBidCommand itemBidCommand) {

        boolean canPlaceBid = validate(itemBidCommand.bid());
        ItemBiddingType type = detectAuctionType(itemBidCommand.onItemId());
        if(canPlaceBid) {
            switch (type) {
                case FORWARD_AUCTION -> {
                    return forwardAuctionUsecase.placeBid(itemBidCommand.bid(), itemBidCommand.onItemId());
                }
                case REVERSE_AUCTION -> {

                }
                case SEALED_BID_AUCTION -> {

                }
            }
        }
        return PlaceBidResult.FAILED;
    }
}
