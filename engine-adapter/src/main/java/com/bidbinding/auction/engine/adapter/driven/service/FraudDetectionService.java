package com.bidbinding.auction.engine.adapter.driven.service;

import com.bidbinding.auction.engine.adapter.driven.dto.BidFraudDto;
import com.bidbinding.auction.engine.application.core.model.fraud.FraudDetectionResult;

import java.util.List;

public interface FraudDetectionService {

    List<FraudDetectionResult> detectFraud(BidFraudDto bid);

}
