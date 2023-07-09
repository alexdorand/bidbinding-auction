package com.bidbinding.auction.engine.adapter.driven;

import com.bidbinding.auction.engine.adapter.driven.service.FraudDetectionService;
import com.bidbinding.auction.engine.adapter.driven.dto.BidFraudAdaptor;
import com.bidbinding.auction.engine.adapter.driven.dto.BidFraudDto;
import com.bidbinding.auction.engine.application.core.model.bid.Bid;
import com.bidbinding.auction.engine.application.core.model.bid.ItemBidCommand;
import com.bidbinding.auction.engine.application.core.model.fraud.FraudDetectionResult;
import com.bidbinding.auction.engine.application.port.driven.FraudDetectionPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FraudDetectionAdapter implements FraudDetectionPort {
    @Autowired
    private FraudDetectionService fraudDetectionService;

    @Override
    public Optional<List<FraudDetectionResult>> checkFraudProbability(ItemBidCommand itemBidCommand) {
        BidFraudDto bidToValidate = BidFraudAdaptor.dtoFrom(itemBidCommand.bid());
        bidToValidate.setOnItemId(itemBidCommand.onItemId());
        List<FraudDetectionResult> fraudDetectionResults = fraudDetectionService.detectFraud(bidToValidate);
        if(fraudDetectionResults.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(fraudDetectionResults);
    }

    @Override
    public void reportSuspectedFraud(String itemId, Bid bid, List<FraudDetectionResult> fraudDetectionResults) {

    }

}
