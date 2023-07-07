package com.bidbinding.auction.engine.adapter.driven.service;

import com.bidbinding.auction.engine.adapter.driven.dto.ItemForward;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ItemRepository extends CrudRepository<ItemForward, Long> {

    @Query(value = "select a from item_forward a where a.item_id = :itemId")
	Optional<ItemForward> getItemByItemId(
            @Param("itemId") String itemId);

}
