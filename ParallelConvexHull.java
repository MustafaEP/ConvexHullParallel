package Project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class ParallelConvexHull {

    public List<Point> solve(List<Point> points) throws InterruptedException {
        // Ön işleme
        if (points.size() <= 3) {
            return new ArrayList<>(new HashSet<>(points));
        }

        // --- AŞAMA 1: SIRALAMA (Seri) ---
        Collections.sort(points);

        // Her thread'in sonucunu yazacağı ayrı listeler oluşturulur
        List<Point> lowerHull = new ArrayList<>();
        List<Point> upperHull = new ArrayList<>();

        // --- AŞAMA 2: GÖREVLERİ VE THREAD'LERİ OLUŞTURMA (Paralel) ---
        // Görevleri oluştur
        LowerHullTask lowerTask = new LowerHullTask(points, lowerHull);
        UpperHullTask upperTask = new UpperHullTask(points, upperHull);

        // Thread'leri oluştur ve görevleri ata
        Thread lowerThread = new Thread(lowerTask);
        Thread upperThread = new Thread(upperTask);

        // Thread'leri başlat
        lowerThread.start();
        upperThread.start();

        // --- AŞAMA 3: THREAD'LERİN BİTMESİNİ BEKLEME VE BİRLEŞTİRME (Senkronizasyon) ---
        // Ana thread, diğer iki thread'in işini bitirmesini bekler.
        lowerThread.join();
        upperThread.join();

        // Sonuçları birleştir
        lowerHull.remove(lowerHull.size() - 1);
        upperHull.remove(upperHull.size() - 1);
        lowerHull.addAll(upperHull);

        return lowerHull;
    }

    // Test için main metodu
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
        points.add(new Point(3, 3));

        ParallelConvexHull parallelSolver = new ParallelConvexHull();
        try {
            List<Point> hull = parallelSolver.solve(points);
            System.out.println("Paralel Çözümle Dışbükey Zarf Noktaları:");
            for (Point p : hull) {
                System.out.println(p);
            }
        } catch (InterruptedException e) {
            // join() metodu kesintiye uğrarsa bu hata fırlatılır.
            e.printStackTrace();
            Thread.currentThread().interrupt(); // Thread'in kesinti durumunu koru
        }
    }
}