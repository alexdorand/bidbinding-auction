package com.bidbinding.auction.engine.adapter.driven.dto;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "item_forward")
@Table(name = "item_forward")
@Data
public class ItemForward {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @NotNull
    @Column(name = "item_id")
    private String item_id;

    @Column(name = "description")
    private String description;

}
