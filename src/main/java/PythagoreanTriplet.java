import java.util.*;

public class PythagoreanTriplet {
    private final int a;
    private final int b;
    private final int c;

    public PythagoreanTriplet(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof PythagoreanTriplet pt) {
            return a == pt.a && b == pt.b && c == pt.c;
        } else {
            return false;
        }
    }

    public static TripletsList makeTripletsList() {
        return new TripletsList();
    }

    public static class TripletsList {
        private int sum;
        private Integer maxFactor = null;

        public TripletsList withFactorsLessThanOrEqualTo(int maxFactor) {
            this.maxFactor = maxFactor;
            return this;
        }

        public TripletsList thatSumTo(int sum) {
            this.sum = sum;
            if (maxFactor == null) {
                maxFactor = sum;
            }
            return this;
        }

        public int gcd(int a, int b) {
            while (b != 0) {
                int temp = b;
                b = a % b;
                a = temp;
            }
            return a;
        }

        public List<PythagoreanTriplet> build() {
            List<PythagoreanTriplet> triplets = new ArrayList<>();
            for (int a = 1; a <= sum; a++) {
                for (int b = a + 1; b <= sum; b++) {
                    int c = sum - a - b;
                    int gcd = gcd(c, gcd(a, b));
                    if (c > 0 && a * a + b * b == c * c && c <= maxFactor && gcd <= maxFactor) {
                        triplets.add(new PythagoreanTriplet(a, b, c));
                    }
                }
            }
            return triplets;
        }
    }
}