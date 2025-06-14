package Project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class SerialConvexHull {

    // Üç noktanın yönelimini hesaplayan yardımcı (static) metot.
    // > 0 ise sola dönüş, < 0 ise sağa dönüş, = 0 ise eşdoğrusal.
    private static long crossProduct(Point a, Point b, Point c) {
        return (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
    }

    public List<Point> solve(List<Point> points) {
        // Adım 1: Ön işleme
        if (points.size() <= 3) {
            return new ArrayList<>(new HashSet<>(points));
        }

        // Adım 1: Noktaları sırala
        Collections.sort(points);

        // Adım 2: Alt zarfı oluştur
        List<Point> lowerHull = new ArrayList<>();
        for (Point p : points) {
            while (lowerHull.size() >= 2 &&
                   crossProduct(lowerHull.get(lowerHull.size() - 2), lowerHull.get(lowerHull.size() - 1), p) <= 0) {
                lowerHull.remove(lowerHull.size() - 1);
            }
            lowerHull.add(p);
        }

        // Adım 3: Üst zarfı oluştur
        List<Point> upperHull = new ArrayList<>();
        for (int i = points.size() - 1; i >= 0; i--) {
            Point p = points.get(i);
            while (upperHull.size() >= 2 &&
                   crossProduct(upperHull.get(upperHull.size() - 2), upperHull.get(upperHull.size() - 1), p) <= 0) {
                upperHull.remove(upperHull.size() - 1);
            }
            upperHull.add(p);
        }

        // Adım 4: Zarfları birleştir ve tekrarlı noktaları kaldır
        lowerHull.remove(lowerHull.size() - 1);
        upperHull.remove(upperHull.size() - 1);
        lowerHull.addAll(upperHull);

        return lowerHull;
    }

    // Kodu test etmek için ana metot
    public static void main(String[] args) {
        // Test etmek için birkaç nokta oluşturalım
        List<Point> points = new ArrayList<>();
        points.add(new Point(0, 3));
        points.add(new Point(1, 1));
        points.add(new Point(2, 2));
        points.add(new Point(4, 4));
        points.add(new Point(0, 0));
        points.add(new Point(1, 2));
        points.add(new Point(3, 1));
        points.add(new Point(7, 3));

        SerialConvexHull convexHullSolver = new SerialConvexHull();
        List<Point> hull = convexHullSolver.solve(points);

        System.out.println("Dışbükey Zarf Noktaları:");
        for (Point p : hull) {
            System.out.println(p);
        }
    }
}
