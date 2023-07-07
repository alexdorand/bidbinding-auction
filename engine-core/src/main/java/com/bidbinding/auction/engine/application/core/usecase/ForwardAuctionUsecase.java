package com.bidbinding.auction.engine.application.core.usecase;

import com.bidbinding.auction.engine.application.core.model.bid.Bid;
import com.bidbinding.auction.engine.application.core.model.bid.BidNotificationCommand;
import com.bidbinding.auction.engine.application.core.model.bid.ItemBidCommand;
import com.bidbinding.auction.engine.application.core.model.bid.PlaceBidResult;
import com.bidbinding.auction.engine.application.core.model.item.ForwardAuctionItem;
import com.bidbinding.auction.engine.application.port.driven.AuctionPort;
import com.bidbinding.auction.engine.application.port.driven.EventPort;
import com.bidbinding.auction.engine.application.port.driven.FraudDetectionPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ForwardAuctionUsecase {

    private AuctionPort auctionPort;
    private EventPort eventPort;
    private FraudDetectionPort fraudDetectionPort;

    public PlaceBidResult placeBid(Bid bid, String onItem) {

        if(fraudDetectionPort.isBidFraudulent(new ItemBidCommand(bid, onItem))) {

            fraudDetectionPort.reportSuspectedFraud(bid.getBuyer());
            eventPort.notifyFraudulentBidDetected(new BidNotificationCommand(bid, onItem));

            return PlaceBidResult.FAILED;
        } else {

            ForwardAuctionItem item = auctionPort.getForwardItem(onItem);

            if(item.canPlaceBid(bid)) {

                item.recordBid(bid);
                auctionPort.updateForwardItem(item);

                eventPort.notifyBidPlaced(new BidNotificationCommand(bid, onItem));

                return PlaceBidResult.ACCEPTED;
            } else {

                eventPort.notifyBidFailed(new BidNotificationCommand(bid, onItem));
                return PlaceBidResult.REJECTED;
            }

        }
    }

}
