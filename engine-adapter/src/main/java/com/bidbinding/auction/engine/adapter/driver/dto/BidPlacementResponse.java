package com.bidbinding.auction.engine.adapter.driver.dto;

import com.bidbinding.auction.engine.application.core.model.bid.BidPlacementStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BidPlacementResponse implements Serializable {
    BidPlacementStatus status;
}
