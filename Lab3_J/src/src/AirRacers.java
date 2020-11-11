package src;

class AirRacers extends Simulator.Racers {

    String name;
    int speed;

    double distanceReducer(int distance) {
        return Double.parseDouble(null);
    }

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

    @Override
    double distanceReducer(int distance) {
        if (distance < 1000)
            return distance;
        else if (distance < 5000)
            return distance * 0.97;
        else if (distance < 10000)
            return distance * 0.9;
        else
            return distance * 0.95;
    }
}

class Mortar extends AirRacers {

    public Mortar() {
        super();
        name = "Mortar";
        speed = 8;
    }

    @Override
    double distanceReducer(int distance) {
        return distance * 0.94;
    }
}

class Broom extends AirRacers {

    public Broom() {
        super();
        name = "Broom";
        speed = 20;
    }

    @Override
    double distanceReducer(int distance) {
        return distance * (1 - (0.1 * distance / 1000));
    }
}
