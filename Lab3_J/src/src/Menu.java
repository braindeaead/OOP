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
                System.out.println("Land race was successfully created");
                menu(race);
            case 2:
                System.out.println("Enter a distance of a race: ");
                action = in.nextInt();
                race = new AirRace(action);
                System.out.println("Air race was successfully created. ");
                menu(race);
            case 3:
                System.out.println("Enter a distance of a race: ");
                action = in.nextInt();
                race = new Race(action);
                System.out.println("Mixed race was successfully created. ");
                menu(race);
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
                            race.landRegister(new BactrianCamel());
                            System.out.println("Bactrian Camel was successfully registered. ");
                            raceRegisterProtocol(in, race);
                        case 2:
                            race.landRegister(new SpeedyCamel());
                            System.out.println("Speedy Camel was successfully registered. ");
                            raceRegisterProtocol(in, race);
                        case 3:
                            race.landRegister(new Centaur());
                            System.out.println("Centaur was successfully registered. ");
                            raceRegisterProtocol(in, race);
                        case 4:
                            race.landRegister(new AllTerrainBoots());
                            System.out.println("All-Terrain Boots were successfully registered. ");
                            raceRegisterProtocol(in, race);
                        default:
                            System.out.println("Please choose one of the mentioned options. ");
                            raceRegisterProtocol(in, race);
                    }
                }
            case 2:
                if (race instanceof LandRace) {
                    System.out.println("You can't register this type of vehicle on this race. ");
                    raceRegisterProtocol(in, race);
                }
                else {
                    System.out.println("Which air racer you want to register? ");
                    System.out.println("1. Magic carpet. ");
                    System.out.println("2. Mortar. ");
                    System.out.println("3. Broom. ");

                    action = in.nextInt();

                    switch (action) {
                        case 1:
                            race.airRegister(new MagicCarpet());
                            System.out.println("Magic Carpet was successfully registered. ");
                            raceRegisterProtocol(in, race);
                        case 2:
                            race.airRegister(new Mortar());
                            System.out.println("Mortar was successfully registered. ");
                            raceRegisterProtocol(in, race);
                        case 3:
                            race.airRegister(new Broom());
                            System.out.println("Broom was successfully registered. ");
                            raceRegisterProtocol(in, race);
                        default:
                            System.out.println("Please choose one of the mentioned options. ");
                            raceRegisterProtocol(in, race);
                    }
                }
            case 3:
                menu(race);
            default:
                System.out.println("Please, choose one of the mentioned options. ");
        }
    }

    static void menu(Race race) {
        System.out.println("What do u want to do? ");
        System.out.println("1. Create a race. ");
        System.out.println("2. Register a racer for a race. ");
        System.out.println("3. Start a recently created race. ");

        Scanner in = new Scanner(System.in);
        int action = in.nextInt();


        switch (action) {
            case 1:
                if (!(race == null)) {
                    System.out.println("You have already created a race. Manage it. ");
                    menu(race);
                }
                else {
                    raceCreationProtocol(in, race);
                }
            case 2:
                if (race == null) {
                    System.out.println("You haven't created a race yet. ");
                    menu(race);
                }
                else {
                    raceRegisterProtocol(in, race);
                    menu(race);
                }
            case 3:
                if (race == null) {
                    System.out.println("You haven't created a race yet. ");
                    menu(race);
                }
                else {
                    Simulator.Racers winner = race.startRace(race.landParticipants, race.airParticipants);
                    if (winner instanceof LandRacers) {
                        System.out.println("The winner is: " + ((LandRacers) winner).name + ".");
                    }
                    else {
                        System.out.println("The winner is: " + ((AirRacers) winner).name + ".");
                    }
                    break;
                }
            default:
                System.out.println("Please, choose one of the mentioned options. ");
                menu(race);
                }
                System.exit(0);
        }
    }
