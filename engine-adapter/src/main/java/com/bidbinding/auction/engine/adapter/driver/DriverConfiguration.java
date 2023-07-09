package com.bidbinding.auction.engine.adapter.driver;

import com.bidbinding.auction.engine.adapter.driven.FraudDetectionAdapter;
import com.bidbinding.auction.engine.adapter.driven.ItemPortAdapter;
import com.bidbinding.auction.engine.application.core.usecase.ForwardAuctionUsecase;
import com.bidbinding.auction.engine.application.core.usecase.PlaceBidUsecase;
import com.bidbinding.auction.engine.application.core.usecase.ReverseAuctionUsecase;
import com.bidbinding.auction.engine.application.core.usecase.SealedBidsUsecase;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.bidbinding.auction.engine.adapter")
@EntityScan("com.bidbinding.auction.engine.adapter")
public class DriverConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        objectMapper.configure(SerializationFeature.WRITE_SELF_REFERENCES_AS_NULL, false);
        return objectMapper;
    }

    @Bean
    ReverseAuctionUsecase reverseAuctionUsecase() {
        return new ReverseAuctionUsecase();
    }

    @Bean
    SealedBidsUsecase sealedBidsUsecase() {
        return new SealedBidsUsecase();
    }

    @Bean
    ForwardAuctionUsecase forwardAuctionUsecase(FraudDetectionAdapter fraudDetectionPort, ItemPortAdapter itemPort) {
        return new ForwardAuctionUsecase(fraudDetectionPort, itemPort);
    }

    @Bean
    public PlaceBidUsecase placeBidUsecase(ForwardAuctionUsecase forwardAuctionUsecase, SealedBidsUsecase sealedBidsUsecase, ReverseAuctionUsecase reverseAuctionUsecase, ItemPortAdapter itemPortAdapter) {
        return new PlaceBidUsecase(forwardAuctionUsecase, sealedBidsUsecase, reverseAuctionUsecase, itemPortAdapter);
    }

}
