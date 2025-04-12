class PetrolPump {
    int petrol;
    int distance;
    PetrolPump(int petrol, int distance) {
        this.petrol = petrol;
        this.distance = distance;
    }
}
public class CircularTour {
    public static int findStartingPoint(PetrolPump[] pumps) {
        int start = 0, deficit = 0, balance = 0;
        for (int i = 0; i < pumps.length; i++) {
            balance += pumps[i].petrol - pumps[i].distance;
            if (balance < 0) {
                start = i + 1;
                deficit += balance;
                balance = 0;
            }
        }
        return (balance + deficit >= 0) ? start : -1;
    }
    public static void main(String[] args) {
        PetrolPump[] pumps = {
                new PetrolPump(4, 6),
                new PetrolPump(6, 5),
                new PetrolPump(7, 3),
                new PetrolPump(4, 5)
        };
        int startIndex = findStartingPoint(pumps);
        System.out.println("Starting pump index: " + startIndex);
    }
}
