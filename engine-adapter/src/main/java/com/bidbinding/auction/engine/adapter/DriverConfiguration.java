package com.bidbinding.auction.engine.adapter;

import com.bidbinding.auction.engine.adapter.common.driven.TimestreamConfiguration;
import com.bidbinding.auction.engine.adapter.driven.EventPortAdapter;
import com.bidbinding.auction.engine.adapter.driven.FraudDetectionAdapter;
import com.bidbinding.auction.engine.adapter.driven.ItemAdapter;
import com.bidbinding.auction.engine.adapter.driven.TenantAdapter;
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
import software.amazon.awssdk.core.client.config.ClientOverrideConfiguration;
import software.amazon.awssdk.core.retry.RetryPolicy;
import software.amazon.awssdk.http.apache.ApacheHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.timestreamwrite.TimestreamWriteClient;

import java.time.Duration;

@Configuration
@EnableJpaRepositories("com.bidbinding.auction.engine.adapter")
@EntityScan("com.bidbinding.auction.engine.adapter")
public class DriverConfiguration {

    @Bean
    public TimestreamConfiguration timestreamConfiguration() {
        return new TimestreamConfiguration("us-east-1", "bidbinding", "itemBids");
    }

    @Bean
    public TimestreamWriteClient timestreamWriteClient() {
        ApacheHttpClient.Builder httpClientBuilder =
                ApacheHttpClient.builder();
        httpClientBuilder.maxConnections(5000);

        RetryPolicy.Builder retryPolicy =
                RetryPolicy.builder();
        retryPolicy.numRetries(10);

        ClientOverrideConfiguration.Builder overrideConfig =
                ClientOverrideConfiguration.builder();
        overrideConfig.apiCallAttemptTimeout(Duration.ofSeconds(20));
        overrideConfig.retryPolicy(retryPolicy.build());

        return TimestreamWriteClient.builder()
                .httpClientBuilder(httpClientBuilder)
                .overrideConfiguration(overrideConfig.build())
                .region(Region.US_EAST_1)
                .build();
    }

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
    ForwardAuctionUsecase forwardAuctionUsecase(EventPortAdapter eventPortAdapter, FraudDetectionAdapter fraudDetectionAdapter, ItemAdapter itemAdapter, TenantAdapter tenantAdapter) {
        return new ForwardAuctionUsecase(eventPortAdapter, fraudDetectionAdapter, itemAdapter, tenantAdapter);
    }

    @Bean
    public PlaceBidUsecase placeBidUsecase(ForwardAuctionUsecase forwardAuctionUsecase, SealedBidsUsecase sealedBidsUsecase, ReverseAuctionUsecase reverseAuctionUsecase, ItemAdapter itemPortAdapter) {
        return new PlaceBidUsecase(forwardAuctionUsecase, sealedBidsUsecase, reverseAuctionUsecase, itemPortAdapter);
    }

}
