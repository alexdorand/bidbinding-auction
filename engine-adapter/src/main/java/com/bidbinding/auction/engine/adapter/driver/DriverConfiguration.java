package com.bidbinding.auction.engine.adapter.driver;

import com.bidbinding.auction.engine.adapter.driven.AuctionPortAdapter;
import com.bidbinding.auction.engine.adapter.driven.EventPortAdapter;
import com.bidbinding.auction.engine.adapter.driven.FraudDetectionAdapter;
import com.bidbinding.auction.engine.adapter.driven.ItemPortAdapter;
import com.bidbinding.auction.engine.application.core.usecase.ForwardAuctionUsecase;
import com.bidbinding.auction.engine.application.core.usecase.PlaceBidUsecase;
import com.bidbinding.auction.engine.application.core.usecase.ReverseAuctionUsecase;
import com.bidbinding.auction.engine.application.core.usecase.SealedBidsUsecase;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.bidbinding.auction.engine.adapter")
@EntityScan("com.bidbinding.auction.engine.adapter")
public class DriverConfiguration {


    @Bean
    ReverseAuctionUsecase reverseAuctionUsecase() {
        return new ReverseAuctionUsecase();
    }

    @Bean
    SealedBidsUsecase sealedBidsUsecase() {
        return new SealedBidsUsecase();
    }

    @Bean
    ForwardAuctionUsecase forwardAuctionUsecase(AuctionPortAdapter auctionPort, EventPortAdapter eventPort, FraudDetectionAdapter fraudDetectionPort) {
        return new ForwardAuctionUsecase(auctionPort, eventPort, fraudDetectionPort);
    }

    @Bean
    public PlaceBidUsecase placeBidUsecase(ForwardAuctionUsecase forwardAuctionUsecase, SealedBidsUsecase sealedBidsUsecase, ReverseAuctionUsecase reverseAuctionUsecase, ItemPortAdapter itemPortAdapter) {
        return new PlaceBidUsecase(forwardAuctionUsecase, sealedBidsUsecase,reverseAuctionUsecase, itemPortAdapter);
    }

}
