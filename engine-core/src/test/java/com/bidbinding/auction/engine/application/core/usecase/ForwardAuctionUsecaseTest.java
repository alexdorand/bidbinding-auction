package com.bidbinding.auction.engine.application.core.usecase;

import com.bidbinding.auction.engine.application.core.model.bid.Bid;
import com.bidbinding.auction.engine.application.core.model.bid.ItemBidCommand;
import com.bidbinding.auction.engine.application.core.model.item.ForwardAuctionItem;
import com.bidbinding.auction.engine.application.core.model.item.ItemBiddingType;
import com.bidbinding.auction.engine.application.port.driven.AuctionPort;
import com.bidbinding.auction.engine.application.port.driven.EventPort;
import com.bidbinding.auction.engine.application.port.driven.FraudDetectionPort;
import com.bidbinding.auction.engine.application.port.driven.ItemPort;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

class ForwardAuctionUsecaseTest {


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Nested
    @IndicativeSentencesGeneration(separator = " -> ", generator = DisplayNameGenerator.ReplaceUnderscores.class)
    class A_bid_is_placed_by_Place_Bit_Port {


        @Test
        void if_the_bid_is_a_negative_number_fail() {

            String itemId = UUID.randomUUID().toString();
            ItemPort itemPort = Mockito.mock(ItemPort.class);
            Bid bid = Bid
                    .builder()
                    .buyer("Buyer")
                    .amount(BigDecimal.valueOf(100))
                    .id(itemId)
                    .build();

            AuctionPort auctionPort = Mockito.mock(AuctionPort.class);
            EventPort eventPort = Mockito.mock(EventPort.class);
            FraudDetectionPort fraudDetectionPort = Mockito.mock(FraudDetectionPort.class);

            ForwardAuctionUsecase forwardAuctionUsecase = new ForwardAuctionUsecase(auctionPort, eventPort, fraudDetectionPort);
            ReverseAuctionUsecase reverseAuctionUsecase = Mockito.mock(ReverseAuctionUsecase.class);
            SealedBidsUsecase sealedBidsUsecase = Mockito.mock(SealedBidsUsecase.class);

            ForwardAuctionItem forwardAuctionItem = ForwardAuctionItem.builder()
                    .bids(new ArrayList<>())
                    .participantsLastBids(new ConcurrentHashMap<>())
                    .id(UUID.randomUUID().toString())
                    .build();

            Mockito.when(auctionPort.getForwardItem(Mockito.anyString())).thenReturn(forwardAuctionItem);


            Mockito.when(itemPort.getItemBiddingType(Mockito.anyString())).thenReturn(ItemBiddingType.FORWARD_AUCTION);
            Mockito.when(itemPort.detectTypeForItem(Mockito.anyString())).thenReturn(ItemBiddingType.FORWARD_AUCTION);

            Assertions.assertNotNull(itemPort);

            PlaceBidUsecase placeBidUsecase = new PlaceBidUsecase(forwardAuctionUsecase, sealedBidsUsecase,reverseAuctionUsecase, itemPort );

            placeBidUsecase.placeBid(new ItemBidCommand(bid, UUID.randomUUID().toString()));
        }

        @Test
        void if_the_bid_is_a_positive_number_accept() {
        }

        @ParameterizedTest(name = "Bid {0} is placed.")
        @ValueSource(ints = { 2016, 2020, 2048 })
        void if_bid_is_success_full_at(int year) {
        }

    }

}