package com.hcl.foodorder.search.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.Payloads;
import org.springframework.stereotype.Component;

import com.hcl.foodorder.domain.events.MenuItemEvent;
import com.hcl.foodorder.search.model.MenuItem;
import com.hcl.foodorder.search.service.MenuItemsService;

@Component
public class MenuItemStreamListener {

	@Autowired
	private MenuItemsService menuItemsService;
	
	@StreamListener(MenuItemStream.INPUT)
	public void receiveEmployeeMessage(@Payloads MenuItemEvent menuItemEvent) {
		com.hcl.foodorder.domain.restaurant.MenuItem payloadMenuItem = menuItemEvent.getPayload();
		MenuItem searchMenuItem = new MenuItem(payloadMenuItem.getId(), payloadMenuItem.getRestaurantId(), payloadMenuItem.getName(), payloadMenuItem.getDescription(), payloadMenuItem.getPrice(), payloadMenuItem.getQuantity(), payloadMenuItem.getIsAvailable());
		
		String response = menuItemsService.save(searchMenuItem);
		System.out.println("Received MenuItem Details : " + response);
	}
}
