package com.bidbinding.auction.engine.application.core.model.item;

import com.bidbinding.auction.engine.application.core.model.bid.Bid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Getter
@Builder
@AllArgsConstructor
public final class ForwardAuctionItem implements Item {

    private final String id;
    @Setter
    private List<Bid> bids;
    @Setter
    private Map<String, Bid> participantsLastBids;
    @Setter
    private Bid winningBid;
    private BigDecimal reservedPrice;
    //private ItemBiddingType type;


    @Override
    public boolean canPlaceBid(Bid bid) {
        if(winningBid!=null) {
            if(winningBid.getAmount().doubleValue()<bid.getAmount().doubleValue()) {
                return true;
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void recordBid(Bid bid) {
        bids.forEach(Bid::decreaseRankValue);
        bid.declareWinner();
        bids.add(bid);
        winningBid = bid;
    }

    @Override
    public ItemBiddingType getItemBiddingType() {
        return ItemBiddingType.FORWARD_AUCTION;
    }


}
