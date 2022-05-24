import java.util.Collections;

public class Kosmici extends Agent {

    public Kosmici(String name, int positionX, int positionY, int health){

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
            Symulacja.map[wayX][wayY] = name;
            Symulacja.map[positionX][positionY] = null;
            positionX = wayX;
            positionY = wayY;
        }
        else { //na wybranej pozycji znajduje sie inny agent
            String nazwa;
            switch(Symulacja.map[wayX][wayY].charAt(0)) {
                case 'l':    // interakcja z czlowiekiem

                    nazwa = "k" + Symulacja.birthCount;
                    Symulacja.map[wayX][wayY] = nazwa;
                    Symulacja.agents.add(new Kosmici(nazwa, wayX, wayY, 0));
                    for (int i = 0; i < Symulacja.agents.size(); i++) { // zmieniamy wartosci obiektu do usunięcia
                        if(Symulacja.agents.get(i).positionX == wayX && Symulacja.agents.get(i).positionY == wayY) {
                            Symulacja.agents.get(i).name = "0";
                            Symulacja.agents.get(i).positionX = -1;
                            Symulacja.agents.get(i).positionY = -1;
                        }
                    }
                    Symulacja.birthCount++;
                    Symulacja.deathCount++;
                    Symulacja.initialLudzie--;
                    Symulacja.initialKosmici++;
                    break;
                case 'w':    // interakcja z wrozka
                    nazwa = "l" + Symulacja.birthCount;
                    Symulacja.map[positionX][positionY] = nazwa;
                    Symulacja.agents.add(new Ludzie(nazwa, positionX, positionY, 2));
                    name = "0";
                    positionX = -1;     // zmieniamy wartosci obiektu do usunięcia
                    positionY = -1;
                    Symulacja.birthCount++;
                    Symulacja.deathCount++;
                    Symulacja.initialLudzie++;
                    Symulacja.initialKosmici--;
                    break;
            }
        }
        waysForX.clear();
        waysForY.clear();
    }
}
