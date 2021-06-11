package restaurant.service;

import com.hcl.foodorder.domain.exception.RestaurantDetailsNotFoundException;
import com.hcl.foodorder.domain.restaurant.Restaurant;
import com.hcl.foodorder.restaurant.repository.RestaurantRepository;
import com.hcl.foodorder.restaurant.service.RestaurantService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static restaurant.util.DataUtil.restaurant;

@ExtendWith(MockitoExtension.class)
public class RestaurantServiceTest {

    @InjectMocks
    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository repository;

    @Test
    public void testCreateRestaurant() {
        when(repository.save(any())).thenReturn(restaurant());

        Restaurant restaurant = restaurantService.createRestaurant(restaurant());

        assertNotNull(restaurant);
        assertEquals(restaurant(), restaurant);
    }

    @Test
    public void testGetRestaurant_throwsRestaurantDetailsNotFoundException() {
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(null));

        assertThrows(RestaurantDetailsNotFoundException.class, () -> restaurantService.getRestaurant(1l));
    }

    @Test
    public void testGetRestaurant() throws RestaurantDetailsNotFoundException {
        when(repository.findById(anyLong())).thenReturn(Optional.of(restaurant()));

        Restaurant restaurant = restaurantService.getRestaurant(restaurant().getId());

        assertNotNull(restaurant);
        assertEquals(restaurant(), restaurant);
    }
}
