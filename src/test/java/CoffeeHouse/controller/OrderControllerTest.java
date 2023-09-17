package CoffeeHouse.controller;

import coffeehouse.controller.OrderController;
import coffeehouse.Dto.CoffeeHouseDto;
import coffeehouse.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {
    @Mock
    private OrderService orderService;
    @InjectMocks
    private OrderController orderController;

    @Test
    public void testHandleException() {
        Exception exception = new Exception("Test exception");
        ResponseEntity<CoffeeHouseDto<String>> expectedResponse = ResponseEntity
                .badRequest()
                .body(new CoffeeHouseDto<>(exception.getClass().getSimpleName() + "  |||  " + exception.getMessage(), "Exception"));

        ResponseEntity<CoffeeHouseDto<String>> response = orderController.handleException(exception);

        assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
        assertEquals(expectedResponse.getBody(), response.getBody());
    }
}
