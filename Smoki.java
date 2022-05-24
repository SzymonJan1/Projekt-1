import java.util.Collections;
import java.util.Random;

public class Smoki extends Agent {

    public Smoki(String name, int positionX, int positionY, int health) {

        super(name, positionX, positionY, health);

    }

    void Shift_X_Y() {

        if(positionX == 0 && positionY == 0) {  //lewy gorny rog mapy
            waysForX.add(0);
            waysForX.add(1);
            waysForY.add(0);
            waysForY.add(1);
        }
        else if(positionX == Symulacja.x-1 && positionY == 0) { //prawy gorny rog mapy
            waysForX.add(Symulacja.x-1);
            waysForX.add(Symulacja.x-2);
            waysForY.add(0);
            waysForY.add(1);
        }
        else if(positionX == 0 && positionY == Symulacja.y-1) { //lewy dolny rog mapy
            waysForX.add(0);
            waysForX.add(1);
            waysForY.add(Symulacja.y-1);
            waysForY.add(Symulacja.y-2);
        }
        else if(positionX == Symulacja.x-1 && positionY == Symulacja.y-1) { //prawy dolny rog mapy
            waysForX.add(Symulacja.x-1);
            waysForX.add(Symulacja.x-2);
            waysForY.add(Symulacja.y-1);
            waysForY.add(Symulacja.y-2);
        }
        else if(positionX > 0 && positionX < Symulacja.x-1 && positionY == 0) { //przy gornej krawedzi mapy
            waysForX.add(positionX);
            waysForX.add(positionX-1);
            waysForX.add(positionX+1);
            waysForY.add(0);
            waysForY.add(1);
        }
        else if(positionX > 0 && positionX < Symulacja.x-1 && positionY == Symulacja.y-1) { //przy dolnej krawedzi mapy
            waysForX.add(positionX);
            waysForX.add(positionX-1);
            waysForX.add(positionX+1);
            waysForY.add(Symulacja.y-1);
            waysForY.add(Symulacja.y-2);
        }
        else if(positionX == 0 && positionY > 0 && positionY < Symulacja.y-1) { // przy krawedzi z lewej strony mapy
            waysForX.add(0);
            waysForX.add(1);
            waysForY.add(positionY);
            waysForY.add(positionY-1);
            waysForY.add(positionY+1);
        }
        else if(positionX == Symulacja.x-1 && positionY > 0 && positionY < Symulacja.y-1) { //przy krawedzi z prawej strony mapy
            waysForX.add(Symulacja.x-1);
            waysForX.add(Symulacja.x-2);
            waysForY.add(positionY);
            waysForY.add(positionY-1);
            waysForY.add(positionY+1);
        }
        else if(positionX > 0 && positionX < Symulacja.x-1 && positionY > 0 && positionY < Symulacja.y-1) {
            waysForX.add(positionX);
            waysForX.add(positionX-1);
            waysForX.add(positionX+1);
            waysForY.add(positionY);
            waysForY.add(positionY-1);
            waysForY.add(positionY+1);
        }
        Collections.shuffle(waysForX);
        Collections.shuffle(waysForY);
        // niech pierwsze elementy przetasowanych list beda nowymi wspolrzednymi

        int wayX, wayY;
        wayX = waysForX.get(0);
        wayY = waysForY.get(0);

        if(Symulacja.map[wayX][wayY] == null) { //na wybranej pozycji nie ma innego agenta
            health--;
            if(health == 0) {
                Symulacja.map[positionX][positionY] = null;
                name = "0";
                positionX = -1;     // zmieniamy wartosci obiektu do usunięcia
                positionY = -1;
                Symulacja.deathCount++;
                Symulacja.initialSmoki--;
            }
            else {
                Symulacja.map[wayX][wayY] = name;
                Symulacja.map[positionX][positionY] = null;
                positionX = wayX;
                positionY = wayY;
            }
        }
        else {
            String nazwa;
            switch(Symulacja.map[wayX][wayY].charAt(0)) {
                case 'l':
                    Symulacja.map[wayX][wayY] = name;
                    Symulacja.map[positionX][positionY] = null;
                    for (int i = 0; i < Symulacja.agents.size(); i++) { // zmieniamy wartosci obiektu do usunięcia
                        if(Symulacja.agents.get(i).positionX == wayX && Symulacja.agents.get(i).positionY == wayY) {
                            Symulacja.agents.get(i).name = "0";
                            Symulacja.agents.get(i).positionX = -1;
                            Symulacja.agents.get(i).positionY = -1;
                        }
                    }
                    positionX = wayX;
                    positionY = wayY;
                    Symulacja.deathCount++;
                    Symulacja.initialLudzie--;
                    if(health != 5) health = 5;
                    break;
                case 's':
                    Random random = new Random();
                    nazwa = "s" + Symulacja.birthCount;
                    int pozycjaX = random.nextInt(Symulacja.x);
                    int pozycjaY = random.nextInt(Symulacja.y);
                    for (int j = 0; j < Symulacja.agents.size(); j++) {
                        if (Symulacja.agents.get(j).positionX == pozycjaX && Symulacja.agents.get(j).positionY == pozycjaY) {
                            pozycjaX = random.nextInt(Symulacja.x);
                            pozycjaY = random.nextInt(Symulacja.y);
                            j = -1;
                        }
                    }
                    Symulacja.agents.add(new Smoki(nazwa, pozycjaX, pozycjaY, 5));
                    Symulacja.map[pozycjaX][pozycjaY] = nazwa;
                    Symulacja.birthCount++;
                    Symulacja.initialSmoki++;
                    health--;
                    if(health == 0) {
                        Symulacja.map[positionX][positionY] = null;
                        name = "0";
                        positionX = -1;     // zmieniamy wartosci obiektu do usunięcia
                        positionY = -1;
                        Symulacja.deathCount++;
                        Symulacja.initialSmoki--;
                    }
                    break;
                default:
                    health--;
                    if(health == 0) {
                        Symulacja.map[positionX][positionY] = null;
                        name = "0";
                        positionX = -1;     // zmieniamy wartosci obiektu do usunięcia
                        positionY = -1;
                        Symulacja.deathCount++;
                        Symulacja.initialSmoki--;
                        break;
                    }
            }
        }
        waysForX.clear();
        waysForY.clear();
    }
}
