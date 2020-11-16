package src;
import java.util.ArrayList;

abstract class Race {

    abstract void register(Simulator.Racers participant);

    abstract Simulator.Racers startRace();
}

class MixedRace extends Race {

    int distance;
    ArrayList<Simulator.Racers> participants;

    MixedRace(int distance) {
        this.distance = distance;
        participants = new ArrayList<>();
    }

    void register(Simulator.Racers participant) {
        participants.add(participant);
    }

    @Override
    Simulator.Racers startRace() {
        Simulator.Racers winner = null;
        int bestTime = Integer.MAX_VALUE;

        for (Simulator.Racers racer : participants) {
            if (racer.timeCounter(distance) < bestTime) {
                winner = racer;
                bestTime = racer.timeCounter(distance);
            }
        }

        return winner;
    }

}

class LandRace extends Race {

    int distance;
    ArrayList<Simulator.Racers> participants;

    LandRace(int distance) {
        this.distance = distance;
        participants = new ArrayList<>();
    }

    void register(Simulator.Racers participant) {
        participants.add(participant);
    }

    @Override
    Simulator.Racers startRace() {
        Simulator.Racers winner = null;
        int bestTime = Integer.MAX_VALUE;

        for (Simulator.Racers racer : participants) {
            if (racer.timeCounter(distance) < bestTime) {
                winner = racer;
                bestTime = racer.timeCounter(distance);
            }
        }

        return winner;
    }

}

class AirRace extends Race {

    int distance;
    ArrayList<Simulator.Racers> participants;

    AirRace(int distance) {
        this.distance = distance;
        participants = new ArrayList<>();
    }

    void register(Simulator.Racers participant) {
        participants.add(participant);
    }

    @Override
    Simulator.Racers startRace() {
        Simulator.Racers winner = null;
        int bestTime = Integer.MAX_VALUE;

        for (Simulator.Racers racer : participants) {
            if (racer.timeCounter(distance) < bestTime) {
                winner = racer;
                bestTime = racer.timeCounter(distance);
            }
        }

        return winner;
    }
}
