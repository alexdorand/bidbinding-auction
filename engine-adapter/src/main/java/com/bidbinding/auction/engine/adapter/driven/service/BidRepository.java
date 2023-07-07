package com.bidbinding.auction.engine.adapter.driven.service;

import com.bidbinding.auction.engine.adapter.driven.dto.Bid;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface BidRepository extends CrudRepository<Bid, Long> {

    @Query("""
			select a from bid a 
			where a.itemId = :item_id 			
			""")
    List<Bid> getBidsForItem(
            @Param("item_id") long itemId);

}
