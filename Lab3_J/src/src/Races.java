package src;
import java.util.ArrayList;
import java.util.HashMap;

class Race {

    private int distance;
    private ArrayList<Simulator.Racers> participants;

    public Race(int distance) {
        this.distance = distance;
    }

    void register(Simulator.Racers participant) {
        participants.add(participant);
    }

    int timeCounter(Simulator.Racers racer) {
        return Integer.parseInt(null);
    }

    Simulator.Racers startRace(int distance, ArrayList<Simulator.Racers> participants) {
        Simulator.Racers winner = null;
        return winner;
    }
}

class LandRace extends Race {

    private int distance;
    private ArrayList<LandRacers> participants;

    void register(LandRacers participant) {
        participants.add(participant);
    }

    private int timeCounter(LandRacers racer) {
       int time = distance / racer.speed;
       for (int i = 0; i < (time / racer.restInterval); ++i) {
           time += racer.restDuration(i);
       }
       return time;
    }

    LandRacers startRace() {
        LandRacers winner = null;
        int bestTime = Integer.MAX_VALUE;

        for (LandRacers racer : participants) {
           if (timeCounter(racer) < bestTime) {
               winner = racer;
               bestTime = timeCounter(racer);
           }
        }

        return winner;
    }

    public LandRace(int distance) {
        super(distance);
    }
}

class AirRace extends Race {

    int distance;
    private ArrayList<AirRacers> participants;

    void register(AirRacers participant) {
        participants.add(participant);
    }

    private int timeCounter(AirRacers racer) {
        int time = (int) (racer.distanceReducer(distance) / racer.speed);
        return time;
    }

    AirRacers startRace() {
        AirRacers winner = null;
        int bestTime = Integer.MAX_VALUE;

        for (AirRacers racer : participants) {
            if (timeCounter(racer) < bestTime) {
                winner = racer;
                bestTime = timeCounter(racer);
            }
        }

        return winner;

    }

    public AirRace(int distance) {
        super(distance);
    }
}
