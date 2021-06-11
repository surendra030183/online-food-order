package restaurant.util;

import com.hcl.foodorder.domain.common.Address;
import com.hcl.foodorder.domain.restaurant.MenuItem;
import com.hcl.foodorder.domain.restaurant.Restaurant;

import java.util.Arrays;
import java.util.List;

public class DataUtil {

    public static Restaurant restaurant() {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);
        restaurant.setName("Fauzi");
        restaurant.setDescription("Curry, Biryani");
        restaurant.setAddress(new Address());
        restaurant.setIsActive(true);
        restaurant.setIsOpen(true);
        return restaurant;
    }

    public static MenuItem menuItem() {
        MenuItem menuItem = new MenuItem();
        menuItem.setId(11L);
        menuItem.setRestaurantId(1L);
        menuItem.setName("Biryani");
        menuItem.setDescription("Spicy Biryani");
        menuItem.setPrice(100.00);
        menuItem.setQuantity(2);
        menuItem.setIsAvailable(true);
        return menuItem;
    }

    public static List<MenuItem> menuItemList() {
        return Arrays.asList(menuItem());
    }


}
