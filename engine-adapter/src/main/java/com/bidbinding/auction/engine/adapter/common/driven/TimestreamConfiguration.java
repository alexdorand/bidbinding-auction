package com.bidbinding.auction.engine.adapter.common.driven;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TimestreamConfiguration {

    private String region;
    private String database;
    private String table;

}
