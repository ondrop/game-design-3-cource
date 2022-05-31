import java.util.Arrays;

public class MathService {

    public static final double EPSILON = 0.001;
    public static final double EQN_EPS  = 1e-9;

    private static boolean isZero(double x) {

        return (x > -EQN_EPS && x < EQN_EPS);
    }

    public static double[] solve4(double[] coeffs) {
        /* normal form: x^4 + Ax^3 + Bx^2 + Cx + D = 0 */

        double A = coeffs[3] / coeffs[4];
        double B = coeffs[2] / coeffs[4];
        double C = coeffs[1] / coeffs[4];
        double D = coeffs[0] / coeffs[4];

    /*  substitute x = y - A/4 to eliminate cubic term:
	x^4 + px^2 + qx + r = 0 */

        double sqA = A * A;
        double p = - 3.0 / 8 * sqA + B;
        double q = 1.0 / 8 * sqA * A - 1.0 / 2 * A * B + C;
        double r = - 3.0 / 256 * sqA * sqA + 1.0 / 16 * sqA * B - 1.0 / 4 * A * C + D;
        double[] s;

        if (isZero(r)) {
            /* no absolute term: y(y^3 + py + q) = 0 */

            coeffs = new double[]{q, p, 0, 1};
            double[] sTemp = solve3(coeffs);
            s = new double[sTemp.length + 1];
            for (int i = 0; i < sTemp.length; i++) {
                s[i] = sTemp[i];
            }

            s[sTemp.length] = 0;
        } else {
            /* solve the resolvent cubic ... */
            coeffs = new double[]{
                    1.0 / 2 * r * p - 1.0 / 8 * q * q,
                    -r,
                    -1.0 / 2 * p,
                    1
            };

            s = solve3(coeffs);

            /* ... and take the one real solution ... */

            double z = s[0];

            /* ... to build two quadric equations */

            double u = z * z - r;
            double v = 2 * z - p;

            if (isZero(u)) {
                u = 0;
            } else if (u > 0) {
                u = Math.sqrt(u);
            } else {
                return new double[0];
            }

            if (isZero(v)) {
                v = 0;
            } else if (v > 0) {
                v = Math.sqrt(v);
            } else {
                return new double[0];
            }

            coeffs = new double[]{
                    z - u,
                    q < 0 ? -v : v,
                    1
            };

            double[] s1temp = solve2(coeffs);

            coeffs = new double[]{
                    z + u,
                    q < 0 ? v : -v,
                    1
            };

            double[] s2Temp = solve2(coeffs);

            s = Arrays.copyOf(s1temp, s1temp.length + s2Temp.length);
            System.arraycopy(s2Temp, 0, s, s1temp.length, s2Temp.length);
        }

        /* resubstitute */

        double sub = 1.0 / 4 * A;

        for (int i = 0; i < s.length; ++i) {
            s[i] -= sub;
        }

        return s;
    }

    public static double[] solve3(double[] coeffs) {
        /* normal form: x^3 + Ax^2 + Bx + C = 0 */

        double A = coeffs[2] / coeffs[3];
        double B = coeffs[1] / coeffs[3];
        double C = coeffs[0] / coeffs[3];

    /*  substitute x = y - A/3 to eliminate quadric term:
	x^3 +px + q = 0 */

        double sqA = A * A;
        double p = 1.0 / 3 * (- 1.0 / 3 * sqA + B);
        double q = 1.0 / 2 * (2.0 / 27 * A * sqA - 1.0 / 3 * A * B + C);

        /* use Cardano's formula */

        double cbP = p * p * p;
        double D = q * q + cbP;

        double[] s;

        if (isZero(D)) {
            if (isZero(q)) {
                /* one triple solution */
                s = new double[]{0};
            } else {
                /* one single and one double solution */
                double u = Math.cbrt(-q);
                s = new double[]{2 * u, -u};
            }
        } else if (D < 0) {
            /* Casus irreducibilis: three real solutions */
            double phi = 1.0 / 3 * Math.acos(-q / Math.sqrt(-cbP));
            double t = 2 * Math.sqrt(-p);

            s = new double[]{
                    t * Math.cos(phi),
                    -t * Math.cos(phi + Math.PI / 3),
                    -t * Math.cos(phi - Math.PI / 3)
            };
        } else {
            /* one real solution */
            double sqrtD = Math.sqrt(D);
            double u = Math.cbrt(sqrtD - q);
            double v = - Math.cbrt(sqrtD + q);

            s =  new double[]{u + v};
        }

        /* resubstitute */

        double sub = 1.0 / 3 * A;
        for (int i = 0; i < s.length; ++i) {
            s[i] -= sub;
        }

        return s;
    }

    public static double[] solve2(double[] coeffs) {
        /* normal form: x^2 + px + q = 0 */

        double p = coeffs[1] / (2 * coeffs[2]);
        double q = coeffs[0] / coeffs[2];

        double D = p * p - q;

        if (isZero(D)) {
            return new double[]{-p};
        } else if (D < 0) {
            return new double[0];
        } else {
            double sqrtD = Math.sqrt(D);

            return new double[]{
                    sqrtD - p,
                    -sqrtD - p
            };
        }
    }
}
