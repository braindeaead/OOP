package src;

abstract class LandRacers extends Simulator.Racers {

    int restInterval;
    int rests;

    LandRacers() {
        type = "Land";
    }
}

class BactrianCamel extends LandRacers {


    public BactrianCamel() {
         super();
         name = "Bactrian Camel";
         speed = 10;
         restInterval = 30;
         rests = 0;
     }

    double distReduce(int rests) {
        if (rests == 1)
            return 5;
        else
            return 8;
    }

    @Override
    int timeCounter(int distance) {
        int time = distance / speed;
        for (int i = 0; i < (time / restInterval); ++i) {
            time += distReduce(i);
        }
        return time;
    }

}

class SpeedyCamel extends LandRacers {

    public SpeedyCamel() {
        super();
        name = "Speedy Camel";
        speed = 40;
        restInterval = 10;
        rests = 0;
    }

    double distReduce(int rests) {
        if (rests == 1)
            return 5;
        else if (rests == 2)
            return 6.5;
        else
            return 8;
    }

    @Override
    int timeCounter(int distance) {
        int time = distance / speed;
        for (int i = 0; i < (time / restInterval); ++i) {
            time += distReduce(i);
        }
        return time;
    }
}

class Centaur extends LandRacers {

    public Centaur() {
        super();
        name = "Centaur";
        speed = 15;
        restInterval = 8;
        rests = 0;
    }

    double distReduce(int rests) {
        return 2;
    }

    @Override
    int timeCounter(int distance) {
        int time = distance / speed;
        for (int i = 0; i < (time / restInterval); ++i) {
            time += distReduce(i);
        }
        return time;
    }
}

class AllTerrainBoots extends LandRacers {

    public AllTerrainBoots() {
        super();
        name = "All-terrain Boots";
        speed = 6;
        restInterval = 60;
        rests = 0;
    }

    double distReduce(int rests) {
        if (rests == 1)
            return 10;
        else
            return 5;
    }

    @Override
    int timeCounter(int distance) {
        int time = distance / speed;
        for (int i = 0; i < (time / restInterval); ++i) {
            time += distReduce(i);
        }
        return time;
    }
}