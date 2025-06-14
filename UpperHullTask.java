package Project;

import java.util.ArrayList;
import java.util.List;

// Üst zarfı hesaplayan görev
public class UpperHullTask implements Runnable {
    private final List<Point> points;
    private final List<Point> upperHull;

    public UpperHullTask(List<Point> points, List<Point> upperHull) {
        this.points = points;
        this.upperHull = upperHull;
    }

    @Override
    public void run() {
        for (int i = points.size() - 1; i >= 0; i--) {
            Point p = points.get(i);
            while (upperHull.size() >= 2 &&
                   crossProduct(upperHull.get(upperHull.size() - 2), upperHull.get(upperHull.size() - 1), p) <= 0) {
                upperHull.remove(upperHull.size() - 1);
            }
            upperHull.add(p);
        }
    }

    // Bu yardımcı metodu SerialConvexHull'den kopyalıyoruz
    private static long crossProduct(Point a, Point b, Point c) {
        return (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
    }
}