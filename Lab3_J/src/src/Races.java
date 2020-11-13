package src;
import java.util.ArrayList;

class Race {

    private int distance;
    private ArrayList<LandRacers> LandParticipants;
    private ArrayList<AirRacers> AirParticipant;

    Race(int distance) {
        this.distance = distance;
    }

    void LandRegister(LandRacers participant) {
        LandParticipants.add(participant);
    }

    void AirRegister(AirRacers participant) {
        AirParticipant.add(participant);
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

    private int distance;
    private ArrayList<LandRacers> participants;

    LandRacers startRace() {
        LandRacers winner = null;
        int bestTime = Integer.MAX_VALUE;

        for (LandRacers racer : participants) {
           if (landTimeCounter(racer) < bestTime) {
               winner = racer;
               bestTime = landTimeCounter(racer);
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

    AirRacers startRace() {
        AirRacers winner = null;
        int bestTime = Integer.MAX_VALUE;

        for (AirRacers racer : participants) {
            if (airTimeCounter(racer) < bestTime) {
                winner = racer;
                bestTime = airTimeCounter(racer);
            }
        }

        return winner;

    }

    public AirRace(int distance) {
        super(distance);
    }
}
