package com.bidbinding.auction.engine.adapter.driven.service;

import com.bidbinding.auction.engine.adapter.driven.dto.BidFraudDto;
import com.bidbinding.auction.engine.application.core.model.fraud.FraudDetectionResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FraudDetectionServiceImpl implements FraudDetectionService {

    @Override
    public List<FraudDetectionResult> detectFraud(BidFraudDto bid) {
        return new ArrayList<>();
    }
}
