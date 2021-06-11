package restaurant.controller;

import com.hcl.foodorder.domain.exception.RestaurantDetailsNotFoundException;
import com.hcl.foodorder.domain.exception.RestaurantMenuCreationException;
import com.hcl.foodorder.domain.restaurant.MenuItem;
import com.hcl.foodorder.domain.restaurant.Restaurant;
import com.hcl.foodorder.restaurant.controller.RestaurantController;
import com.hcl.foodorder.restaurant.service.MenuService;
import com.hcl.foodorder.restaurant.service.RestaurantService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static restaurant.util.DataUtil.menuItemList;
import static restaurant.util.DataUtil.restaurant;

@ExtendWith(MockitoExtension.class)
public class RestaurantControllerTest {

    @InjectMocks
    private RestaurantController restaurantController;

    @Mock
    private RestaurantService restaurantService;
    @Mock
    private MenuService menuService;

    @Test
    public void testCreate() {
        when(restaurantService.createRestaurant(any(Restaurant.class))).thenReturn(restaurant());

        ResponseEntity<Restaurant> response = restaurantController.create(restaurant());

        verify(restaurantService).createRestaurant(restaurant());

        assertNotNull(response, "Response cannot be null");
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(restaurant(), response.getBody());
    }

    @Test
    public void testGetRetaurantByRestaurantId_throwsRestaurantDetailsNotFoundException() throws RestaurantDetailsNotFoundException {
        when(restaurantService.getRestaurant(anyLong())).thenThrow(new RestaurantDetailsNotFoundException());

        assertThrows(RestaurantDetailsNotFoundException.class, () -> restaurantController.get(1L));
    }

    @Test
    public void testGetRetaurantByRestaurantId() throws RestaurantDetailsNotFoundException {
        when(restaurantService.getRestaurant(anyLong())).thenReturn(restaurant());

        ResponseEntity<Restaurant> response = restaurantController.get(1L);

        assertNotNull(response, "Response cannot be null");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(restaurant(), response.getBody());
    }

    @Test
    public void testcreateMenu_throwsRestaurantDetailsNotFoundException() throws RestaurantDetailsNotFoundException {
        when(restaurantService.getRestaurant(anyLong())).thenThrow(new RestaurantDetailsNotFoundException());

        assertThrows(RestaurantDetailsNotFoundException.class, () -> restaurantController.createMenu(menuItemList(), menuItemList().get(0).getRestaurantId()));
    }

    @Test
    public void testcreateMenu_throwsRestaurantMenuCreationException() throws RestaurantDetailsNotFoundException {
        when(restaurantService.getRestaurant(anyLong())).thenReturn(null);

        assertThrows(RestaurantMenuCreationException.class, () -> restaurantController.createMenu(menuItemList(), menuItemList().get(0).getRestaurantId()));
    }

    @Test
    public void testcreateMenu() throws RestaurantDetailsNotFoundException, RestaurantMenuCreationException {
        when(restaurantService.getRestaurant(anyLong())).thenReturn(restaurant());
        when(menuService.createMenu(anyList(), anyLong())).thenReturn(menuItemList());

        ResponseEntity<List<MenuItem>> response  = restaurantController.createMenu(menuItemList(), menuItemList().get(0).getRestaurantId());

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(menuItemList().size(), response.getBody().size());
        assertEquals(menuItemList(), response.getBody());
    }

    @Test
    public void testGetMenuDetails() {
        when(menuService.getMenu(anyLong())).thenReturn(menuItemList());

        ResponseEntity<List<MenuItem>> menuItems = restaurantController.getMenuDetails(1L);

        assertNotNull(menuItems);
        assertEquals(HttpStatus.OK, menuItems.getStatusCode());
        assertNotNull(menuItems.getBody());
        assertEquals(menuItemList().size(), menuItems.getBody().size());
    }

}
