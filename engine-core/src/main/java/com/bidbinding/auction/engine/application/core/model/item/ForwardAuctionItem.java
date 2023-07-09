package com.bidbinding.auction.engine.application.core.model.item;

import com.bidbinding.auction.engine.application.core.model.bid.Bid;
import com.bidbinding.auction.engine.application.core.model.bid.BidPlacementStatus;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public final class ForwardAuctionItem implements Item {

    private String id;

    private Bid bid;

    private BigDecimal reservedPrice;
    private long startTimestamp;
    private long endTimestamp;

    private transient List<Bid> bidsHistory;
    private transient Bid winningBid;
    private boolean concluded;
    private long concludedOn;

    @Override
    public BidPlacementStatus recordBid(Bid bid) {
        if(!bid.getBidPlacementStatus().isFraud()) {
            if(canPlaceBid(bid)) {
                bid.setBidPlacementStatus(BidPlacementStatus.ACCEPTED);
            } else {
                bid.setBidPlacementStatus(BidPlacementStatus.REJECTED);
            }
        }
        return bid.getBidPlacementStatus();
    }

    @Override
    public void conclude() {
        this.concluded = true;
        this.concludedOn = System.currentTimeMillis();
    }


    private boolean canPlaceBid(Bid bid) {
        if (isAuctionNotStarted() || isAuctionEnded()) return false;
        return isIncomingBidAmountIsLargerThanWinningBid(bid);
    }

    private boolean isIncomingBidAmountIsLargerThanWinningBid(Bid bid) {
        return winningBid!=null && winningBid.getAmount().doubleValue() < bid.getAmount().doubleValue();
    }

    private boolean isAuctionEnded() {
        return endTimestamp < System.currentTimeMillis();
    }

    private boolean isAuctionNotStarted() {
        return startTimestamp > System.currentTimeMillis();
    }



}
