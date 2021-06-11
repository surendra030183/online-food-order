package restaurant;

import com.hcl.foodorder.restaurant.RestaurantServiceApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;

class RestaurantServiceApplicationTests {

    @Test
    public void isAnnotatatedWithSpringBoot() {
        Class mainClass = RestaurantServiceApplication.class;
        Assertions.assertNotNull(
                mainClass.getAnnotation(SpringBootApplication.class),
                "Main class should be annotated with SpringBootApplication annotation"
        );
    }

}