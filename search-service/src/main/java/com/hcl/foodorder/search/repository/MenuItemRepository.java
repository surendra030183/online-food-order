package com.hcl.foodorder.search.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.hcl.foodorder.search.model.MenuItem;

@Repository
public interface MenuItemRepository extends ElasticsearchRepository<MenuItem, String> {

    List<MenuItem> findByName(String name);
    Optional<MenuItem> findById(String itemId);
}
