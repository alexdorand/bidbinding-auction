package com.bidbinding.auction.engine.adapter.driven.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "bid")
@Table(name = "bid")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bid {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "bid_id")
    private String bid_id;

    @Column(name = "buyer")
    private String buyer;

    @Column(name = "winner")
    private String winner;

    @NotNull
    @Column(name = "item_id")
    private int itemId;

    @Column(name = "amount")
    private float amount;

}
