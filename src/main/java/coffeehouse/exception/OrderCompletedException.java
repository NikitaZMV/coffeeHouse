package coffeehouse.exception;

public class OrderCompletedException extends CoffeeHouseException{
    public OrderCompletedException() {
        super("заказ завершён");
    }
}
