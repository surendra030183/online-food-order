package com.hcl.foodorder.search.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.foodorder.search.model.MenuItem;
import com.hcl.foodorder.search.repository.MenuItemRepository;

@RestController
@RequestMapping("/rest/search")
public class SearchController {

    private static final Logger logger = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    MenuItemRepository menuItemRepository;

    @GetMapping(value = "/name/{itemName}")
    public List<MenuItem> searchName(@PathVariable final String itemName) {
        logger.info("Invoking searchName method url {}"+itemName);
        return menuItemRepository.findByName(itemName);
    }

    @GetMapping(value = "/{itemId}")
    public Optional<MenuItem> searchId(@PathVariable final Long itemId) {
        logger.info("Invoking searchId method url {}"+ itemId);
        return menuItemRepository.findById(itemId.toString());
    }

    @GetMapping(value = "/all")
    public List<MenuItem> searchAll() {
        logger.info("Invoking searchAll method url {}");
        List<MenuItem> itemList = new ArrayList<>();
        Iterable<MenuItem> items = menuItemRepository.findAll();
        items.forEach(itemList::add);
        return itemList;
    }

}
