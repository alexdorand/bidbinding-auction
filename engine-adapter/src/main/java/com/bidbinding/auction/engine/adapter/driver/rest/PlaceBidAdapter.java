package com.bidbinding.auction.engine.adapter.driver.rest;

import com.bidbinding.auction.engine.adapter.driver.dto.BidAdapter;
import com.bidbinding.auction.engine.adapter.driver.dto.BidDto;
import com.bidbinding.auction.engine.adapter.driver.dto.BidPlacementResponse;
import com.bidbinding.auction.engine.application.core.model.bid.ItemBidCommand;
import com.bidbinding.auction.engine.application.core.model.bid.PlaceBidResult;
import com.bidbinding.auction.engine.application.core.usecase.PlaceBidUsecase;
import com.bidbinding.auction.engine.application.port.driver.PlaceBidPort;
import jakarta.validation.ConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

import static jakarta.validation.Validation.buildDefaultValidatorFactory;

@RestController
public class PlaceBidAdapter implements PlaceBidPort {

    @Autowired
    private PlaceBidUsecase placeBidUsecase;

    @Override
    public PlaceBidResult placeBid(ItemBidCommand itemBidCommand) {
        return placeBidUsecase.placeBid(itemBidCommand);
    }

    @PostMapping(value = "/bid", consumes = "application/json", produces = "application/json")
    public BidPlacementResponse placeBidExpand(@RequestBody BidDto bid, @RequestHeader(value = "Correlation-Id", required = false) String correlationId) {
        //Set<ConstraintViolation<BidDto>> violations = buildDefaultValidatorFactory().getValidator().validate(bid);
        //System.out.println(violations.size());
        PlaceBidResult placeBidResult = placeBid(new ItemBidCommand(BidAdapter.adapt(bid), bid.getItemId()));

        return new BidPlacementResponse(placeBidResult.name());
    }
}
