import java.util.Collections;
import java.util.Random;

public class Ludzie extends Agent {

    public Ludzie(String name, int positionX, int positionY, int health) {

        super(name, positionX, positionY, health);
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
        } else if(positionX > 0 && positionX < Symulacja.x-1 && positionY > 0 && positionY < Symulacja.y-1) {
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

            if (Symulacja.map[wayX][wayY] == null) { //na wybranej pozycji nie ma innego agenta
                Symulacja.map[wayX][wayY] = name;
                Symulacja.map[positionX][positionY] = null;
                positionX = wayX;
                positionY = wayY;
            } else {
                String nazwa;
                switch (Symulacja.map[wayX][wayY].charAt(0)) {
                    case 'k':
                        nazwa = "k" + Symulacja.birthCount;
                        Symulacja.map[positionX][positionY] = nazwa;
                        Symulacja.agents.add(new Kosmici(nazwa, positionX, positionY, 0));
                        name = "0";
                        positionX = -1;     // zmieniamy wartosci obiektu do usunięcia
                        positionY = -1;
                        Symulacja.birthCount++;
                        Symulacja.deathCount++;
                        Symulacja.initialKosmici++;
                        Symulacja.initialLudzie--;
                        break;
                    case 's':
                        Symulacja.map[positionX][positionY] = null;
                        name = "0";
                        positionX = -1;     // zmieniamy wartosci obiektu do usunięcia
                        positionY = -1;
                        Symulacja.deathCount++;
                        Symulacja.initialLudzie--;
                        nazwa = Symulacja.map[wayX][wayY];
                        for (int j = 0; j < Symulacja.agents.size(); j++) {
                            if (Symulacja.agents.get(j).name.equals(nazwa)) {
                                if (Symulacja.agents.get(j).health != 5) Symulacja.agents.get(j).health = 5;
                            }
                        }
                        break;
                    case 'l':
                        Random random = new Random();
                        Symulacja.birthCount++;
                        Symulacja.initialLudzie++;
                        nazwa = "l" + Symulacja.birthCount;
                        int pozycjaX = random.nextInt(Symulacja.x);
                        int pozycjaY = random.nextInt(Symulacja.y);
                        for (int j = 0; j < Symulacja.agents.size(); j++) {
                            if (Symulacja.agents.get(j).positionX == pozycjaX && Symulacja.agents.get(j).positionY == pozycjaY) {
                                pozycjaX = random.nextInt(Symulacja.x);
                                pozycjaY = random.nextInt(Symulacja.y);
                                j = -1;
                            }
                        }
                        Symulacja.agents.add(new Ludzie(nazwa, pozycjaX, pozycjaY, 2));
                        Symulacja.map[pozycjaX][pozycjaY] = nazwa;
                        break;
                    case 'w':
                        if (health == 1) health++;
                        break;
                    case 'r':
                        health--;
                        if (health == 0) {
                            Symulacja.map[positionX][positionY] = null;
                            name = null;
                            positionX = -1;     // zmieniamy wartosci obiektu do usunięcia
                            positionY = -1;
                            Symulacja.deathCount++;
                            Symulacja.initialLudzie--;
                        }
                        nazwa = Symulacja.map[wayX][wayY];
                        for (int j = 0; j < Symulacja.agents.size(); j++) {
                            if (Symulacja.agents.get(j).name.equals(nazwa)) {
                                if (Symulacja.agents.get(j).health == 1) Symulacja.agents.get(j).health++;
                            }
                        }
                        break;
                }
                waysForX.clear();
                waysForY.clear();
            }

    }
}
