package com.bidbinding.auction.engine.application.core.usecase;

import com.bidbinding.auction.engine.application.core.model.bid.Bid;
import com.bidbinding.auction.engine.application.core.model.bid.BidPlacementStatus;
import com.bidbinding.auction.engine.application.core.model.bid.ItemBidCommand;
import com.bidbinding.auction.engine.application.core.model.fraud.FraudDetectionResult;
import com.bidbinding.auction.engine.application.core.model.item.ForwardAuctionItem;
import com.bidbinding.auction.engine.application.port.driven.FraudDetectionPort;
import com.bidbinding.auction.engine.application.port.driven.ItemPort;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class ForwardAuctionUsecase {

    private FraudDetectionPort fraudDetectionPort;
    private ItemPort<ForwardAuctionItem> itemPort;

    public BidPlacementStatus placeBid(Bid bid, String onItem) {

        Optional<List<FraudDetectionResult>> fraud = fraudDetectionPort.checkFraudProbability(new ItemBidCommand(bid, onItem));
        fraud.ifPresent(bid::markAsFraud);
        ForwardAuctionItem item = itemPort.getItem(onItem);
        BidPlacementStatus bidPlacementStatus = item.recordBid(bid);
        itemPort.updateItem(item);

        return bidPlacementStatus;

    }

}
