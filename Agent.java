import javax.lang.model.type.NullType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

abstract class Agent {

    int iteration;
    int positionX, positionY;
    int health;
    String name;
    List<Integer> waysForX = new ArrayList<>();
    List<Integer> waysForY = new ArrayList<>();

    public Agent(String name, int iteration, int positionX, int positionY, int health) {

        this.name = name;
        this.iteration = Symulacja.iteration;
        this.positionX = positionX;
        this.positionY = positionY;
        this.health = health;
    }
    abstract void Shift_X_Y();
}
