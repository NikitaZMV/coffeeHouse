package coffeehouse.exception;

public class EventAlredyExistException extends CoffeeHouseException{
    public EventAlredyExistException() {
        super("ивент с таким статусом уже существует");
    }
}
