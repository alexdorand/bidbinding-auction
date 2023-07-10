package com.bidbinding.auction.engine.application.core.model.item;

import com.bidbinding.auction.engine.application.core.model.bid.Bid;
import com.bidbinding.auction.engine.application.core.model.bid.BidPlacementStatus;
import com.bidbinding.auction.engine.application.core.model.bid.BidsHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
public final class ForwardAuctionItem implements Item {

    private String id;
    private String tenantId;

    private Bid bid;

    private BigDecimal reservedPrice;
    private long startTimestamp;
    private long endTimestamp;

    private ItemAuctionState itemAuctionState;
    private long concludedOn;

    private transient BidsHistory bidsHistory;

    public void storeForTenant(String tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    public BidPlacementStatus recordBid(Bid bid) {
        if (!bid.getBidPlacementStatus().isFraud()) {
            if (canPlaceBid(bid)) {
                bid.setBidPlacementStatus(BidPlacementStatus.ACCEPTED);
            } else {
                bid.setBidPlacementStatus(BidPlacementStatus.REJECTED);
            }
        }
        this.bid = bid;
        return bid.getBidPlacementStatus();
    }

    @Override
    public void conclude() {
        if (isAuctionEnded() || isAuctionNotStarted()) {
            itemAuctionState = ItemAuctionState.CONCLUDED;
            this.concludedOn = System.currentTimeMillis();
        }
    }

    @Override
    public void cancel() {
        itemAuctionState = ItemAuctionState.CANCELLED;
    }

    private boolean canPlaceBid(Bid bid) {
        if (isAuctionNotStarted() || isAuctionEnded()) return false;
        if (bidsHistory.bidderAttemptedFraudOnThisItemAlready(bid.getBuyer())) return false;
        return isIncomingBidAmountIsLargerThanCurrentWinningBid(bid);
    }

    private boolean isIncomingBidAmountIsLargerThanCurrentWinningBid(Bid bid) {
        return bidsHistory.isThereAPreviousWinner() && bidsHistory.isWinningBidOutbid(bid.getAmount());
    }

    @Override
    public boolean isAuctionEnded() {
        return endTimestamp < System.currentTimeMillis();
    }

    @Override
    public boolean isAuctionNotStarted() {
        return startTimestamp > System.currentTimeMillis();
    }

    @Override
    public boolean isAuctionConcluded() {
        return itemAuctionState == ItemAuctionState.CONCLUDED;
    }


}
