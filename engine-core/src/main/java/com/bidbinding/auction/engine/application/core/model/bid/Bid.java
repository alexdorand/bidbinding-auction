package com.bidbinding.auction.engine.application.core.model.bid;

import com.bidbinding.auction.engine.application.core.model.fraud.FraudDetectionResult;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class Bid {

    private String id;
    private BigDecimal amount;
    private String buyer;
    private long timestamp;
    private BidPlacementStatus bidPlacementStatus;
    private FraudDetectionResult fraudDetectionResult;

    public void markAsFraud(FraudDetectionResult fraudDetectionResult) {
        this.fraudDetectionResult = fraudDetectionResult;
        this.setBidPlacementStatus(BidPlacementStatus.REJECTED_DUE_TO_FRAUD);
    }

}
