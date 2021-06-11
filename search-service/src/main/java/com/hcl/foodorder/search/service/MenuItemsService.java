package com.hcl.foodorder.search.service;

import java.util.List;

import com.hcl.foodorder.search.model.MenuItem;

public interface MenuItemsService {

    String save(MenuItem menuItem);
    List<MenuItem> searchMultiField(String name, int qty);
    List<MenuItem> getMenuNameSearchData(String input);
    List<MenuItem> multiMatchQuery(Long id);
    List<MenuItem> getMenuItemsByPriceRange(final Double itemPriceMin, final Double itemPriceMax);
    List<MenuItem> getMenuItemsByDescriptions(final String descriptions);
}
