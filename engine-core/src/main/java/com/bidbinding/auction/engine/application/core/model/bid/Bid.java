package com.bidbinding.auction.engine.application.core.model.bid;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class Bid {

    private String id;
    private BigDecimal amount;
    private String buyer;
    private int rank = 0;
    private boolean winner = false;


    public void declareWinner() {
        this.winner = true;
    }

    public void outbid() {
        this.winner =false;
    }

    public void increaseRankValue() {
        rank++;
    }

    public void decreaseRankValue() {
        if(rank>0) {
            rank--;
        }
    }
}
