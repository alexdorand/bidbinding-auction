package com.bidbinding.auction.engine.application.core.model.bid;

import com.bidbinding.auction.engine.application.core.model.fraud.FraudDetectionResult;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
public class Bid {

    private String id;
    private BigDecimal amount;
    private String buyer;
    private BidPlacementStatus bidPlacementStatus;
    private List<FraudDetectionResult> fraudDetectionResults;

    public void markAsFraud(List<FraudDetectionResult> fraudDetectionResults) {
        this.fraudDetectionResults = fraudDetectionResults;
        this.setBidPlacementStatus(BidPlacementStatus.REJECTED_DUE_TO_FRAUD);
    }

}
