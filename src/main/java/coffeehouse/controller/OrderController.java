package coffeehouse.controller;

import coffeehouse.Dto.CoffeeHouseDto;
import coffeehouse.entity.*;
import coffeehouse.exception.CoffeeHouseException;
import coffeehouse.model.Order;
import coffeehouse.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("coffeeHouse")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    @RequestMapping(value = "/Registered", method = RequestMethod.POST)
    public ResponseEntity<CoffeeHouseDto<? extends OrderEvent>> post(@RequestBody OrderRegisteredEvent event) throws CoffeeHouseException {
        return ResponseEntity.ok().body(orderService.publishEvent(event));
    }
    @PostMapping
    @RequestMapping(value = "/Accepted", method = RequestMethod.POST)
    public ResponseEntity<CoffeeHouseDto<? extends OrderEvent>> post(@RequestBody OrderAcceptedEvent event) throws CoffeeHouseException {
        return ResponseEntity.ok().body(orderService.publishEvent(event));
    }
    @PostMapping
    @RequestMapping(value = "/Issued", method = RequestMethod.POST)
    public ResponseEntity<CoffeeHouseDto<? extends OrderEvent>> post(@RequestBody OrderIssuedEvent event) throws CoffeeHouseException {
        return ResponseEntity.ok().body(orderService.publishEvent(event));
    }
    @PostMapping
    @RequestMapping(value = "/Cancelled", method = RequestMethod.POST)
    public ResponseEntity<CoffeeHouseDto<? extends OrderEvent>> post(@RequestBody OrderCancelledEvent event) throws CoffeeHouseException {
        return ResponseEntity.ok().body(orderService.publishEvent(event));
    }
    @PostMapping
    @RequestMapping(value = "/Ready", method = RequestMethod.POST)
    public ResponseEntity<CoffeeHouseDto<? extends OrderEvent>> post(@RequestBody OrderReadyEvent event) throws CoffeeHouseException {
        return ResponseEntity.ok().body(orderService.publishEvent(event));
    }

    @GetMapping
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CoffeeHouseDto<Order>> get(@PathVariable("id") int id) {
        return ResponseEntity.ok().body(orderService.findOrder(id));
    }

    @ExceptionHandler
    public ResponseEntity<CoffeeHouseDto<String>> handleException(Exception e) {
        return ResponseEntity.badRequest().body(new CoffeeHouseDto<>(e.getClass().getSimpleName() + "  |||  " + e.getMessage(), "Exception"));
    }

}
