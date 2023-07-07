package com.bidbinding.auction.engine.adapter.driver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class BidPlacementResponse implements Serializable {
    String status;
}
