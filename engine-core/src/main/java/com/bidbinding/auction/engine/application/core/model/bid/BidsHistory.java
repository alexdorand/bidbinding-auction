package com.bidbinding.auction.engine.application.core.model.bid;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BidsHistory {

    private List<Bid> bids;
    private List<Bid> rejectedBids;
    private Map<String, String> detectedFraudulentBids;
    private Bid winningBid;

    public boolean bidderAttemptedFraudOnThisItemAlready(String bidder) {
        return detectedFraudulentBids != null && detectedFraudulentBids.keySet().contains(bidder);
    }

    public boolean isThereAPreviousWinner() {
        return winningBid != null;
    }

    public boolean isWinningBidOutbid(BigDecimal amount) {
        return winningBid.getAmount().doubleValue() < amount.doubleValue();
    }

}
