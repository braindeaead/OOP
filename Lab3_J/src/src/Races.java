package src;
import java.util.ArrayList;

class Race {

    private int distance;
    ArrayList<LandRacers> landParticipants;
    ArrayList<AirRacers> airParticipants;

    Race(int distance) {
        this.distance = distance;
        landParticipants = new ArrayList<>();
        airParticipants = new ArrayList<>();
    }

    void landRegister(LandRacers participant) {
        landParticipants.add(participant);
    }

    void airRegister(AirRacers participant) {
        airParticipants.add(participant);
    }

    int landTimeCounter(LandRacers racer) {
        int time = distance / racer.speed;
        for (int i = 0; i < (time / racer.restInterval); ++i) {
            time += racer.restDuration(i);
        }
        return time;
    }

    int airTimeCounter(AirRacers racer) {
        int time = (int) (racer.distanceReducer(distance) / racer.speed);
        return time;
    }

    Simulator.Racers startRace(ArrayList<LandRacers> lParticipants, ArrayList<AirRacers> aParticipants) {
        LandRacers lWinner = null;
        AirRacers aWinner = null;
        int lBestTime = Integer.MAX_VALUE, aBestTime = Integer.MAX_VALUE;

        for (LandRacers racer : lParticipants) {
            if (landTimeCounter(racer) < lBestTime) {
                lWinner = racer;
                lBestTime = landTimeCounter(racer);
            }
        }

        for (AirRacers racer : aParticipants) {
            if (airTimeCounter(racer) < aBestTime) {
                aWinner = racer;
                aBestTime = airTimeCounter(racer);
            }
        }

        if (lBestTime < aBestTime)
            return lWinner;
        else
            return aWinner;
    }
}

class LandRace extends Race {

    int distance;

    LandRace(int distance) {
        super(distance);
        this.distance = distance;
    }
}

class AirRace extends Race {

    int distance;

    AirRace(int distance) {
        super(distance);
        this.distance = distance;
    }
}
