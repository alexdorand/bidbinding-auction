package com.bidbinding.auction.engine.adapter.driver.rest;

import com.bidbinding.auction.engine.adapter.driver.dto.BidPlacementRequest;
import com.bidbinding.auction.engine.adapter.driver.dto.BidPlacementResponse;
import com.bidbinding.auction.engine.application.core.model.bid.BidPlacementStatus;
import com.bidbinding.auction.engine.application.core.model.bid.ItemBidCommand;
import com.bidbinding.auction.engine.application.core.usecase.PlaceBidUsecase;
import com.bidbinding.auction.engine.application.port.driver.PlaceBidPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import static com.bidbinding.auction.engine.adapter.common.driver.RestAdapterFunctions.status;
import static com.bidbinding.auction.engine.adapter.driver.dto.BidPlacementAdapter.toBid;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
public class PlaceBidAdapter implements PlaceBidPort {

    @Autowired
    private PlaceBidUsecase placeBidUsecase;

    @Override
    public BidPlacementStatus placeBid(ItemBidCommand itemBidCommand) {
        return placeBidUsecase.placeBid(itemBidCommand);
    }


    @PostMapping(value = "/bid", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<BidPlacementResponse> placeBidExpand(@RequestBody BidPlacementRequest bidPlacementRequest,
                                                               @RequestHeader(value = "Correlation-Id", required = false) String correlationId) {

        BidPlacementStatus placeBidResult = placeBid(new ItemBidCommand(toBid(bidPlacementRequest), bidPlacementRequest.itemId()));
        BidPlacementResponse response = new BidPlacementResponse(placeBidResult);
        return new ResponseEntity<>(response, status.apply(placeBidResult));

    }

}
