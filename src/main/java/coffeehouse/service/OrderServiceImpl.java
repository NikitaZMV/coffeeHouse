package coffeehouse.service;

import coffeehouse.entity.OrderEvent;
import coffeehouse.model.Order;
import coffeehouse.repo.OrderEventRepo;
import coffeehouse.validation.OrderEventValidationService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {


    private OrderEventRepo repo;
    private OrderEventValidationService validator = new OrderEventValidationService();

    public OrderServiceImpl(OrderEventRepo repo) {
        this.repo = repo;
    }

    @Override
    public void publishEvent(OrderEvent event) {
        /*CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<OrderEvent> query = criteriaBuilder.createQuery(OrderEvent.class);
        Root<OrderEvent> root = query.from(OrderEvent.class);
        query.select(root).where(criteriaBuilder.equal(root.get("order_id"), event.getOrderId()));
        if (validator.validate(entityManager.createQuery(query).getResultList())) {
            repo.save(event);
        }*/
    }

    @Override
    public Order findOrder(int orderId) {
        Order order = new Order();

        /*CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<OrderEvent> query = criteriaBuilder.createQuery(OrderEvent.class);
        Root<OrderEvent> root = query.from(OrderEvent.class);
        query.select(root).where(criteriaBuilder.equal(root.get("order_id"), orderId));

        List<OrderEvent> events = entityManager.createQuery(query).getResultList();
        EventTypes current = EventTypes.REGISTERED;
        for (OrderEvent event : events) {
            if (event.getEventType() == EventTypes.REGISTERED) {
                current = EventTypes.REGISTERED;
            } else if (event.getEventType() == EventTypes.ACCEPTED) {
                current = EventTypes.ACCEPTED;
            } else if (event.getEventType() == EventTypes.READY) {
                current = EventTypes.READY;
            } else if (event.getEventType() == EventTypes.ISSUED) {
                current = EventTypes.ACCEPTED;
            } else if (event.getEventType() == EventTypes.CANCELED) {
                current = EventTypes.CANCELED;
            }
        }
        order.setEvents(events);
        order.setCurrentEvent(current);*/
        return order;
    }
}
