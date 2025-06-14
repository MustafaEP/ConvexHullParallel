package Project;

import java.util.ArrayList;
import java.util.List;

// Alt zarfı hesaplayan görev
public class LowerHullTask implements Runnable {
    private final List<Point> points;
    private final List<Point> lowerHull;

    public LowerHullTask(List<Point> points, List<Point> lowerHull) {
        this.points = points;
        this.lowerHull = lowerHull;
    }

    @Override
    public void run() {
        for (Point p : points) {
            while (lowerHull.size() >= 2 &&
                   crossProduct(lowerHull.get(lowerHull.size() - 2), lowerHull.get(lowerHull.size() - 1), p) <= 0) {
                lowerHull.remove(lowerHull.size() - 1);
            }
            lowerHull.add(p);
        }
    }

    // Bu yardımcı metodu SerialConvexHull'den kopyalıyoruz
    private static long crossProduct(Point a, Point b, Point c) {
        return (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
    }
}