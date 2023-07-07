package com.bidbinding.auction.engine.adapter.driver.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder
@Data
public class BidDto {

    @NotBlank(message = "bidder is required") String bidder;
    @NotBlank(message = "item required") String itemId;
    @NotNull(message = "amount is required") BigDecimal amount;


}
