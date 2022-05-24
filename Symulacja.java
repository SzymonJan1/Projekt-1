import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Symulacja {

    static int iteration;
    static int birthCount = 0, deathCount = 0, agentsCount = birthCount - deathCount; //narodzin potrzebny do tworzenia nazw kolejnych obiektow, licznik narodzin, licznik smierci
    static int initialSmoki, initialLudzie, initialKosmici, initialWrozki, initialRosliny; //wartosci poczatkowe
    static int x, y;
    static String[][] map;
    static List<Agent> agents = new ArrayList<>();

    void mapInitialization() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Długość mapy: ");
        x = scanner.nextInt();
        System.out.println("Szerekość mapy: ");
        y = scanner.nextInt();
        map = new String[x][y];
    }

    //generowanie agentow z warunkow poczatkowych
    void addAgents() {
        Random random = new Random();
        String nazwa;//analogicznie do ww
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ilość smoków (Nie może przekraczać " + x * y + "): ");
        initialSmoki = scanner.nextInt();
        System.out.println("Ilość ludzi (Nie może przekraczać " + (x * y - initialSmoki) + "): ");
        initialLudzie = scanner.nextInt();
        System.out.println("Ilość wróżek (Nie może przekraczać " + (x * y - initialSmoki - initialLudzie) + "): ");
        initialWrozki = scanner.nextInt();
        System.out.println("Ilość kosmitów (Nie może przekraczać " + (x * y - initialSmoki - initialLudzie - initialWrozki) + "): ");
        initialKosmici = scanner.nextInt();
        System.out.println("Ilość roślin (Nie może przekraczać " + (x * y - initialSmoki - initialLudzie - initialWrozki - initialKosmici) + "): ");
        initialRosliny = scanner.nextInt();
        //generowanie smokow
        //
        for (int i = 0; i < initialSmoki; i++) {
            //tworzenie nazwa; dla smokow poczatek s i numerki kolejnych smoków
            nazwa = "s";
            StringBuilder sB = new StringBuilder(nazwa);
            sB.append(birthCount);
            nazwa = sB.toString();
            //koniec tworzenia nazwy
            //losowanie pozycji
            int pozycjaX = random.nextInt(x);
            int pozycjaY = random.nextInt(y);
            //sprawdzanie czy podane koordynaty nie są już przypisane do jakiegos agenta
            for (int j = 0; j < agents.size(); j++) {
                if (agents.get(j).positionX == pozycjaX && agents.get(j).positionY == pozycjaY) {
                    pozycjaX = random.nextInt(x);
                    pozycjaY = random.nextInt(y);
                    j = -1;
                }
            }
            //tworzenie obiektu i dodanie jego reprezentacji na mapie
            agents.add(new Smoki(nazwa, pozycjaX, pozycjaY, 5));
            map[pozycjaX][pozycjaY] = nazwa;
            birthCount++;

        }
        //generowanie ludzi
        //
        for (int i = 0; i < initialLudzie; i++) {
            nazwa = "l";
            StringBuilder sB = new StringBuilder(nazwa);
            sB.append(birthCount);
            nazwa = sB.toString();
            int pozycjaX = random.nextInt(x);
            int pozycjaY = random.nextInt(y);
            for (int j = 0; j < agents.size(); j++) {
                if (agents.get(j).positionX == pozycjaX && agents.get(j).positionY == pozycjaY) {
                    pozycjaX = random.nextInt(x);
                    pozycjaY = random.nextInt(y);
                    j = -1;
                }
            }
            agents.add(new Ludzie(nazwa, pozycjaX, pozycjaY, 2));
            map[pozycjaX][pozycjaY] = nazwa;
            birthCount++;
        }
        //generowanie kosmitow
        //
        for (int i = 0; i < initialKosmici; i++) {
            nazwa = "k";
            StringBuilder sB = new StringBuilder(nazwa);
            sB.append(birthCount);
            nazwa = sB.toString();
            int pozycjaX = random.nextInt(x);
            int pozycjaY = random.nextInt(y);
            for (int j = 0; j < agents.size(); j++) {
                if (agents.get(j).positionX == pozycjaX && agents.get(j).positionY == pozycjaY) {
                    pozycjaX = random.nextInt(x);
                    pozycjaY = random.nextInt(y);
                    j = -1;
                }
            }
            agents.add(new Kosmici(nazwa, pozycjaX, pozycjaY, 0));
            map[pozycjaX][pozycjaY] = nazwa;
            birthCount++;
        }
        //generowanie wrozek
        //
        for (int i = 0; i < initialWrozki; i++) {
            nazwa = "w";
            StringBuilder sB = new StringBuilder(nazwa);
            sB.append(birthCount);
            nazwa = sB.toString();
            int pozycjaX = random.nextInt(x);
            int pozycjaY = random.nextInt(y);
            for (int j = 0; j < agents.size(); j++) {
                if (agents.get(j).positionX == pozycjaX && agents.get(j).positionY == pozycjaY) {
                    pozycjaX = random.nextInt(x);
                    pozycjaY = random.nextInt(y);
                    j = -1;
                }
            }
            agents.add(new Wrozki(nazwa, pozycjaX, pozycjaY, 0));
            map[pozycjaX][pozycjaY] = nazwa;
            birthCount++;
        }
        //generowanie roslin
        //
        for (int i = 0; i < initialRosliny; i++) {
            nazwa = "r";
            StringBuilder sB = new StringBuilder(nazwa);
            sB.append(birthCount);
            nazwa = sB.toString();
            int pozycjaX = random.nextInt(x);
            int pozycjaY = random.nextInt(y);
            for (int j = 0; j < agents.size(); j++) {
                if (agents.get(j).positionX == pozycjaX && agents.get(j).positionY == pozycjaY) {
                    pozycjaX = random.nextInt(x);
                    pozycjaY = random.nextInt(y);
                    j = -1;
                }
            }
            agents.add(new MiesozerneRosliny(nazwa, pozycjaX, pozycjaY, 2));
            map[pozycjaX][pozycjaY] = nazwa;
            birthCount++;
        }
    }

    //funkcja odpowiadajaca za dzialanie symulacji
    void simulate() {
        System.out.println("Początek symulacji:");
        for (int k = 0; k < y; k++) {   //drukowanie mapy
            for (int l = 0; l < x; l++) {
                System.out.print(map[l][k] + "    ");
            }
            System.out.println();
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Liczba iteracji: ");
        iteration = scanner.nextInt();
        for (int j = 0; j < iteration; j++) {
            for (int i = 0; i < agents.size(); i++) {
                if(!agents.get(i).name.equals("0")){
                    agents.get(i).Shift_X_Y();
                }
            }
            int currentIteration;
            currentIteration = j + 1;
            for (int n = agents.size()-1; n >= 0; n--) {    //usuwaniee agentów
                if(agents.get(n).name.equals("0")) agents.remove(n);
            }

            System.out.println("Po " + currentIteration + " iteracji:");
            for (int k = 0; k < y; k++) {
                for (int l = 0; l < x; l++) {
                    System.out.print(map[l][k] + "       ");
                }
                System.out.println();
            }
        }
    }
}
