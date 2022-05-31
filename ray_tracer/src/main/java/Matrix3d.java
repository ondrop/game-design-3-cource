public class Matrix3d {

    private double[][] points;

    public Matrix3d(double[][] points) {
        this.points = points;
    }

    public double getPoint(int x, int y) {
        return points[x][y];
    }

    public Vector3d getRow(int rowCount) {
        return new Vector3d(points[rowCount][0], points[rowCount][1], points[rowCount][2]);
    }

    public Vector3d getColumn(int columnCount) {
        return new Vector3d(points[0][columnCount], points[1][columnCount], points[2][columnCount]);
    }
}
