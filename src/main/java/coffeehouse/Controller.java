package coffeehouse;

import coffeehouse.entity.OrderEvent;
import coffeehouse.model.Order;
import coffeehouse.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("coffeeHouse")
public class Controller {

    @Autowired
    private OrderService orderService;

    @PostMapping
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<? extends String> post(@RequestBody OrderEvent event) {
        orderService.publishEvent(event);
        return ResponseEntity.ok().body("all good");
    }

    @GetMapping
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Order> get(int id) {
        return ResponseEntity.ok().body(orderService.findOrder(id));

    }


    @ExceptionHandler
    public ResponseEntity<? extends String> handleException(Exception e) {
        return ResponseEntity.badRequest().body(e.getClass().getSimpleName() + "  |||  " + e.getMessage());
    }

}
