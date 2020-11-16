package src;

abstract class AirRacers extends Simulator.Racers {

    AirRacers() {
        type = "Air";
    }
}

class MagicCarpet extends AirRacers {

    public MagicCarpet() {
        super();
        name = "Magic Carpet";
        speed = 10;
    }

    double distReduce(int distance) {
        if (distance < 1000)
            return distance;
        else if (distance < 5000)
            return distance * 0.97;
        else if (distance < 10000)
            return distance * 0.9;
        else
            return distance * 0.95;
    }

    int timeCounter(int distance) {
        return (int) (distReduce(distance) / speed);
    }


}

class Mortar extends AirRacers {

    public Mortar() {
        super();
        name = "Mortar";
        speed = 8;
    }

    double distReduce(int distance) {
        return distance * 0.94;
    }

    int timeCounter(int distance) {
        return (int) (distReduce(distance) / speed);
    }
}

class Broom extends AirRacers {

    public Broom() {
        super();
        name = "Broom";
        speed = 20;
    }

    double distReduce(int distance) {
        return distance * (1 - (0.1 * distance / 1000));
    }

    int timeCounter(int distance) {
        return (int) (distReduce(distance) / speed);
    }
}
