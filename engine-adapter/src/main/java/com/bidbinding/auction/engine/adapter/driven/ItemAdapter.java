package com.bidbinding.auction.engine.adapter.driven;

import com.bidbinding.auction.engine.adapter.common.Adaptor;
import com.bidbinding.auction.engine.application.core.model.bid.BidsHistory;
import com.bidbinding.auction.engine.application.core.model.item.ForwardAuctionItem;
import com.bidbinding.auction.engine.application.core.model.item.ItemAuctionState;
import com.bidbinding.auction.engine.application.core.model.item.ItemAuctionType;
import com.bidbinding.auction.engine.application.port.driven.ItemPort;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import lombok.AllArgsConstructor;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Adaptor
public class ItemAdapter implements ItemPort<ForwardAuctionItem> {

    private final ItemTimestreamService timestreamService;
    private final ItemMongoDbService itemMongoDbService;
    private final ItemRedisService redisService;

    private final BehaviorSubject<ForwardAuctionItem> itemBehaviorSubject = BehaviorSubject.create();

    private ExecutorService threadpool = Executors.newCachedThreadPool();

    public ItemAdapter(ItemTimestreamService timestreamService, ItemMongoDbService itemMongoDbService, ItemRedisService redisService) {
        this.timestreamService = timestreamService;
        this.itemMongoDbService = itemMongoDbService;
        this.redisService = redisService;
        itemBehaviorSubject.subscribe(new Consumer<ForwardAuctionItem>() {
            @Override
            public void accept(ForwardAuctionItem item) throws Throwable {
                timestreamService.asyncRecordImmutableBid(item);
                itemMongoDbService.asyncWriteItemToDatastore(item);
                redisService.asyncWriteItemToCache(item);
            }
        });
    }

    @Override
    public ItemAuctionType getListingTypeFor(String itemId) {
        return ItemAuctionType.FORWARD_AUCTION;
    }

    @Override
    public void updateItem(ForwardAuctionItem item) {
        threadpool.submit(() -> {
            itemBehaviorSubject.onNext(item);
        });
    }

    @Override
    public ForwardAuctionItem getItem(String itemId) {
        return ForwardAuctionItem.builder().id(UUID.randomUUID().toString()).itemAuctionState(ItemAuctionState.STARTED).bidsHistory(new BidsHistory()).startTimestamp(System.currentTimeMillis()).endTimestamp(System.currentTimeMillis()).build();
    }

}
