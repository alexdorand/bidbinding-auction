package com.bidbinding.auction.engine.adapter.driven;

import com.bidbinding.auction.engine.adapter.driven.dto.FraudType;
import com.bidbinding.auction.engine.adapter.driven.service.FraudDetectionService;
import com.bidbinding.auction.engine.adapter.driver.dto.BidFraudAdaptor;
import com.bidbinding.auction.engine.adapter.driver.dto.BidFraudDto;
import com.bidbinding.auction.engine.application.core.model.bid.ItemBidCommand;
import com.bidbinding.auction.engine.application.port.driven.FraudDetectionPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FraudDetectionAdapter implements FraudDetectionPort {

    private FraudDetectionService fraudDetectionService;

    @Autowired
    public FraudDetectionAdapter(FraudDetectionService fraudDetectionService) {
        this.fraudDetectionService = fraudDetectionService;
    }


    @Override
    public boolean isBidFraudulent(ItemBidCommand itemBidCommand) {
        BidFraudDto bidToValidate = BidFraudAdaptor.dtoFrom(itemBidCommand.bid());
        bidToValidate.setOnItemId(itemBidCommand.onItemId());
        FraudType fraudType = fraudDetectionService.detectFraud(bidToValidate);
        System.out.println("Fraud Detection Result: "+fraudType);
        return fraudType.isFraudDetected();
    }

    @Override
    public void reportSuspectedFraud(String bid) {
        System.out.println("report suspected fraud");
    }
}
