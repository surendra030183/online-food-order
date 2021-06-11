package restaurant.service;

import com.hcl.foodorder.domain.exception.RestaurantDetailsNotFoundException;
import com.hcl.foodorder.domain.restaurant.MenuItem;
import com.hcl.foodorder.restaurant.repository.MenuRepository;
import com.hcl.foodorder.restaurant.service.MenuService;
import com.hcl.foodorder.restaurant.service.RestaurantService;
import com.hcl.foodorder.restaurant.streams.SendMenuItemDetails;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static restaurant.util.DataUtil.*;

@ExtendWith(MockitoExtension.class)
public class MenuServiceTest {

    @InjectMocks
    private MenuService menuService;

    @Mock
    private RestaurantService restaurantService;
    @Mock
    private MenuRepository repository;
    @Mock
    private SendMenuItemDetails sendMenuItemDetails;

    @Test
    public void testCreateMenu_returnEmptyList() throws RestaurantDetailsNotFoundException {
        when(restaurantService.getRestaurant(anyLong())).thenReturn(null);

        assertEquals(0, menuService.createMenu(menuItemList(), menuItem().getRestaurantId()).size());
    }

    @Test
    public void testCreateMenu() throws RestaurantDetailsNotFoundException {
        when(restaurantService.getRestaurant(anyLong())).thenReturn(restaurant());
        when(repository.saveAll(anyList())).thenReturn(menuItemList());
        doNothing().when(sendMenuItemDetails).sendMessage(any());

        List<MenuItem> menuItems = menuService.createMenu(menuItemList(), menuItem().getRestaurantId());

        assertNotNull(menuItems);
        assertEquals(menuItemList().size(), menuItems.size());
        assertEquals(menuItemList(), menuItems);
    }

    @Test
    public void testGetMenu_returnsEmptyList() throws RestaurantDetailsNotFoundException {
        when(restaurantService.getRestaurant(anyLong())).thenReturn(null);

        assertEquals(0, menuService.getMenu(restaurant().getId()).size());
    }

    @Test
    public void testGetMenu() throws RestaurantDetailsNotFoundException {
        when(restaurantService.getRestaurant(anyLong())).thenReturn(restaurant());
        when(repository.findByRestaurantId(anyLong())).thenReturn(menuItemList());

        List<MenuItem> menuItems = menuService.getMenu(restaurant().getId());

        assertNotNull(menuItems);
        assertEquals(menuItemList().size(), menuItems.size());
        assertEquals(menuItemList(), menuItems);
    }

}
