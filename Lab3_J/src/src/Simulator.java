package src;

public class Simulator {

    static abstract class Racers {

        String name;
        String type;
        int speed;

        abstract double distReduce(int param);

        abstract int timeCounter(int distance);

        Racers() {
        }
    }

    public static void main(String[] args) {
        Race race = null;
        Menu.menu(race);
    }
}
