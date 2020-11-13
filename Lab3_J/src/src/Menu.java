package src;
import java.util.Scanner;

public class Menu {

    private static void raceCreationProtocol(Scanner in, Race race) {
        System.out.println("Race for what vehicle type u want to create? ");
        System.out.println("1. Land race. ");
        System.out.println("2. Air race. ");
        System.out.println("3. Mixed race");

        int action = in.nextInt();

        switch (action) {
            case 1:
                System.out.println("Enter a distance of a race: ");
                action = in.nextInt();
                race = new LandRace(action);
            case 2:
                System.out.println("Enter a distance of a race: ");
                action = in.nextInt();
                race = new AirRace(action);
            case 3:
                System.out.println("Enter a distance of a race: ");
                action = in.nextInt();
                race = new Race(action);
            default:
                System.out.println("Please, choose one of the mentioned options. ");
                raceCreationProtocol(in, race);
        }
    }

    private static void raceRegisterProtocol(Scanner in, Race race) {
        System.out.println("Which type of racer u want to register for race? ");
        System.out.println("1. Land. ");
        System.out.println("2. Air. ");
        System.out.println("3. Exit. ");

        int action = in.nextInt();

        switch (action) {
            case 1:
                if (race instanceof AirRace) {
                    System.out.println("You can't register this type of vehicle on this race. ");
                    raceRegisterProtocol(in, race);
                }
                else {
                    System.out.println("Which land racer you want to register? ");
                    System.out.println("1. Bactrian camel. ");
                    System.out.println("2. Speedy camel. ");
                    System.out.println("3. Centaur. ");
                    System.out.println("4. All-Terrain Boots. ");

                    action = in.nextInt();

                    switch (action) {
                        case 1:
                    }
                }
            case 2:

        }
    }

    static void menu() {
        System.out.println("What do u want to do? ");
        System.out.println("1. Create a race. ");
        System.out.println("2. Register a racer for a race. ");
        System.out.println("3. Start a recently created race. ");

        Scanner in = new Scanner(System.in);
        int action = in.nextInt();
        Race currentRace = null;

        switch (action) {
            case 1:
                raceCreationProtocol(in, currentRace);
            case 2:

            default:
                }
        }
    }
