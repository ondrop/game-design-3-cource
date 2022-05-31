public class Torus extends AbstractShape {

    private double sweptRadius;
    private double tubeRadius;
    private Vector3d center;

    public Torus(Vector3d center, double sweptRadius, double tubeRadius, Vector3d color, double specular, double reflective) {
        super(color, specular, reflective);
        this.sweptRadius = sweptRadius;
        this.tubeRadius = tubeRadius;
        this.center = center;
    }

    public double getSweptRadius() {
        return sweptRadius;
    }

    public void setSweptRadius(double sweptRadius) {
        this.sweptRadius = sweptRadius;
    }

    public double getTubeRadius() {
        return tubeRadius;
    }

    public void setTubeRadius(double tubeRadius) {
        this.tubeRadius = tubeRadius;
    }

    public Vector3d getCenter() {
        return center;
    }

    public void setCenter(Vector3d center) {
        this.center = center;
    }

    @Override
    public double[] intersect(Vector3d origin, Vector3d direction) {
        // define the coefficients of the quartic equation
        double sumDirectionSqrd = direction.dot(direction);
        double e = origin.dot(origin) - sweptRadius * sweptRadius - tubeRadius * tubeRadius;
        double f = origin.dot(direction);
        double fourASqrd = 4.0 * sweptRadius * sweptRadius;

        double[] coeffs = new double[]{
                e * e - fourASqrd * (this.tubeRadius*this.tubeRadius - origin.getY() * origin.getY()),
                4.0 * f * e + 2.0 * fourASqrd * origin.getY() * direction.getY(),
                2.0 * sumDirectionSqrd * e + 4.0 * f * f + fourASqrd * direction.getY() * direction.getY(),
                4.0 * sumDirectionSqrd * f,
                sumDirectionSqrd * sumDirectionSqrd
        };

        double[] solution = MathService.solve4(coeffs);

        // ray misses the torus
        if (solution.length == 0) {
            return new double[]{Double.POSITIVE_INFINITY};
        }

        // find the smallest root greater than kEpsilon, if any
        // the roots array is not sorted
       /* double minT = Double.POSITIVE_INFINITY;
        for (int i = 0; i < solution.length; i++) {
            double t = solution[i];
            if ((t > MathService.EPSILON) && (t < minT)) {
                minT = t;
            }
        }*/

        return solution;
    }

    @Override
    public Vector3d getNormal(Vector3d point) {
        double paramSquared = sweptRadius * sweptRadius + tubeRadius * tubeRadius;
        double sumSquared = point.dot(point);

        Vector3d tmp = new Vector3d(
                4.0 * point.getX() * (sumSquared - paramSquared),
                4.0 * point.getY() * (sumSquared - paramSquared + 2.0 * sweptRadius * sweptRadius),
                4.0 * point.getZ() * (sumSquared - paramSquared));

        return tmp.normalize();
    }
}
