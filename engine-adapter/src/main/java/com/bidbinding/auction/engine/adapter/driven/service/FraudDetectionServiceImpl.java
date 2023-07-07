package com.bidbinding.auction.engine.adapter.driven.service;

import com.bidbinding.auction.engine.adapter.driven.dto.FraudType;
import com.bidbinding.auction.engine.adapter.driver.dto.BidFraudDto;
import org.springframework.stereotype.Service;

@Service
public class FraudDetectionServiceImpl implements FraudDetectionService {
    @Override
    public FraudType detectFraud(BidFraudDto bid) {
        System.out.println("Detecting fraud for "+bid.getBid().getId()+" placed on "+bid.getOnItemId());
        return FraudType.NONE;
    }
}
