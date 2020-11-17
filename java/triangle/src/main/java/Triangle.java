import java.util.HashSet;
import java.util.Set;

class Triangle {

    private double s1;
    private double s2;
    private double s3;

    private Set<Double> setOfSides = new HashSet<>();

    Triangle(double side1, double side2, double side3) throws TriangleException {
        this.s1 = side1;
        this.s2 = side2;
        this.s3 = side3;

        setOfSides.add(s1);
        setOfSides.add(s2);
        setOfSides.add(s3);

        if (!isTriangle())
            throw new TriangleException();
    }

    private boolean isTriangle() {
        return setOfSides.stream().mapToDouble(i -> i).min().getAsDouble() > 0
                && s1 + s2 > s3
                && s1 + s3 > s2
                && s2 + s3 > s1;
    }

    boolean isEquilateral() {
        return setOfSides.size() == 1;
    }

    boolean isIsosceles() {
        return setOfSides.size() <= 2;
    }

    boolean isScalene() {
        return setOfSides.size() == 3;
    }

}
