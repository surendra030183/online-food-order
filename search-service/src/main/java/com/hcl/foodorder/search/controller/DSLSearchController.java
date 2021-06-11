package com.hcl.foodorder.search.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.foodorder.search.model.MenuItem;
import com.hcl.foodorder.search.service.MenuItemsService;


@RestController
@RequestMapping("/rest/dslSearch")
public class DSLSearchController {

    private static final Logger logger = LoggerFactory.getLogger(DSLSearchController.class);

    @Autowired
    private MenuItemsService menuItemsService;

    //Save
    @PostMapping("/menuItems")
    public String saveItems(@RequestBody MenuItem menuItem) {
        logger.info("Invoking save menuItems url");
        return menuItemsService.save(menuItem);
    }


    //Name And Quantity
    @GetMapping("/nameandquantity/{name}/{quantity}")
    public List<MenuItem> getProductsByNameAndQuantity(
            @PathVariable String name, @PathVariable int quantity){
        logger.info("Invoking getMenuItems method url based on name and Quantity");
        return menuItemsService.searchMultiField(name, quantity);
    }

    //By itemName specifically for wildcard characters
    @GetMapping("/wildcard/{searchString}")
    public List<MenuItem> getMenuNameSearchData(
            @PathVariable String searchString){
        logger.info("Invoking getMenuItems method url based on name {}"+searchString);
        return menuItemsService.getMenuNameSearchData(searchString);
    }

    //Descriptions
    @GetMapping("/description/{descriptions}")
    public List<MenuItem> getMenuItemsByDescriptions(
            @PathVariable String descriptions){
        logger.info("Invoking getMenuItems method url based on descriptions {}"+descriptions);
        return menuItemsService.getMenuItemsByDescriptions(descriptions);
    }

    //By restaurantId Or ItemId
    @GetMapping("/restaurantIdOrItemId/{id}")
    public List<MenuItem> getMenuItemsByRestaurantIdOrItemId(
            @PathVariable Long id){
        logger.info("Invoking getMenuItems method url based on restaurantIdOrItemId {}"+id);
        return menuItemsService.multiMatchQuery(id);
    }

    //Items price range
    @GetMapping("/itemsPriceRange/{priceMin}/{priceMax}")
    public List<MenuItem> getProductsByPriceRange(
            @PathVariable Double priceMin, @PathVariable Double priceMax){
        logger.info("Invoking getMenuItems method url based on priceRange");
        return menuItemsService.getMenuItemsByPriceRange(priceMin, priceMax);
    }


}
