import java.util.Collections;

public class MiesozerneRosliny extends Agent {

    public MiesozerneRosliny(String name, int iteration, int positionX, int positionY, int health) {

        super(name, iteration, positionX, positionY, health);
    }

    void Shift_X_Y() {

        if (positionX == 0 && positionY == 0) {  //lewy gorny rog mapy
            waysForX.add(0);
            waysForX.add(1);
            waysForY.add(0);
            waysForY.add(1);
        } else if (positionX == Symulacja.x - 1 && positionY == 0) { //prawy gorny rog mapy
            waysForX.add(Symulacja.x - 1);
            waysForX.add(Symulacja.x - 2);
            waysForY.add(0);
            waysForY.add(1);
        } else if (positionX == 0 && positionY == Symulacja.y - 1) { //lewy dolny rog mapy
            waysForX.add(0);
            waysForX.add(1);
            waysForY.add(Symulacja.y - 1);
            waysForY.add(Symulacja.y - 2);
        } else if (positionX == Symulacja.x - 1 && positionY == Symulacja.y - 1) { //prawy dolny rog mapy
            waysForX.add(Symulacja.x - 1);
            waysForX.add(Symulacja.x - 2);
            waysForY.add(Symulacja.y - 1);
            waysForY.add(Symulacja.y - 2);
        } else if (positionX > 0 && positionX < Symulacja.x - 1 && positionY == 0) { //przy gornej krawedzi mapy
            waysForX.add(positionX);
            waysForX.add(positionX - 1);
            waysForX.add(positionX + 1);
            waysForY.add(0);
            waysForY.add(1);
        } else if (positionX > 0 && positionX < Symulacja.x - 1 && positionY == Symulacja.y - 1) { //przy dolnej krawedzi mapy
            waysForX.add(positionX);
            waysForX.add(positionX - 1);
            waysForX.add(positionX + 1);
            waysForY.add(Symulacja.y - 1);
            waysForY.add(Symulacja.y - 2);
        } else if (positionX == 0 && positionY > 0 && positionY < Symulacja.y - 1) { // przy krawedzi z lewej strony mapy
            waysForX.add(0);
            waysForX.add(1);
            waysForY.add(positionY);
            waysForY.add(positionY - 1);
            waysForY.add(positionY + 1);
        } else if (positionX == Symulacja.x - 1 && positionY > 0 && positionY < Symulacja.y - 1) { //przy krawedzi z prawej strony mapy
            waysForX.add(Symulacja.x - 1);
            waysForX.add(Symulacja.x - 2);
            waysForY.add(positionY);
            waysForY.add(positionY - 1);
            waysForY.add(positionY + 1);
        } else { //pozostale lokacje
            waysForX.add(positionX);
            waysForX.add(positionX - 1);
            waysForX.add(positionX + 1);
            waysForY.add(positionY);
            waysForY.add(positionY - 1);
            waysForY.add(positionY + 1);
        }
        Collections.shuffle(waysForX);
        Collections.shuffle(waysForY);
        // niech pierwsze elementy przetasowanych list beda nowymi wspolrzednymi

        int wayX, wayY;
        wayX = waysForX.get(0);
        wayY = waysForY.get(0);

        if (Symulacja.map[wayX][wayY].isEmpty()) { //na wybranej pozycji nie ma innego agenta
            health--;
            if (health == 0) {
                Symulacja.map[positionX][positionY] = null;
                Symulacja.deathCount++;
                Symulacja.initialRosliny--;
            }
        }
        else {
            String nazwa;
            switch (Symulacja.map[wayX][wayY].charAt(0)) {
                case 'w':
                    Symulacja.map[wayX][wayY] = null;
                    Symulacja.deathCount++;
                    Symulacja.initialWrozki--;
                    if (health == 1) health++;
                    break;
                case 'l':
                    nazwa = Symulacja.map[wayX][wayY];
                    for (int j = 0; j < Symulacja.agents.size(); j++) {
                        if (Symulacja.agents.get(j).name.equals(nazwa)) {
                            Symulacja.agents.get(j).health--;
                            if (Symulacja.agents.get(j).health == 0) {
                                Symulacja.map[wayX][wayY] = null;
                                Symulacja.deathCount++;
                                Symulacja.initialLudzie--;
                            }
                        }
                    }
                    if (health == 1) health++;
                    break;
                default:
                    health--;
                    if(health == 0) {
                        Symulacja.map[positionX][positionY] = null;
                        Symulacja.deathCount++;
                        Symulacja.initialRosliny--;
                        break;
                    }
            }
        }
        iteration++;
        waysForX.clear();
        waysForY.clear();
    }
}
