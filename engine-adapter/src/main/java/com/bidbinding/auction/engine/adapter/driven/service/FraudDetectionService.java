package com.bidbinding.auction.engine.adapter.driven.service;

import com.bidbinding.auction.engine.adapter.driven.dto.FraudType;
import com.bidbinding.auction.engine.adapter.driver.dto.BidFraudDto;

public interface FraudDetectionService {

    FraudType detectFraud(BidFraudDto bid);

}
