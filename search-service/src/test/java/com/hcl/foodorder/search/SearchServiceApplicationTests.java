package com.hcl.foodorder.search;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import com.hcl.foodorder.search.model.MenuItem;
import com.hcl.foodorder.search.service.MenuItemsServiceImpl;

@SpringBootTest
class SearchServiceApplicationTests {

	@Mock
	private ElasticsearchOperations elasticsearchOperations;

	@InjectMocks
	private MenuItemsServiceImpl menuItemsService;

	@Test
	public void descriptionsOfItemsResults() {
		List<MenuItem> menuItems = new ArrayList<MenuItem>();
		MenuItem menuItems1 = new MenuItem(123L, 34L, "SandWitches", "North Indian food", 150.90, 3, true);
		menuItems.add(menuItems1);
		// when(elasticsearchOperations.search(Mockito.any(Query.class),Mockito.any(),Mockito.any())).thenReturn(new
		// SearchHits<MenuItem>);

		List<MenuItem> items = menuItemsService.getMenuItemsByDescriptions("North Indian food");

		Assertions.assertEquals(menuItems, items);

	}
}