package Project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random; 


public class between {
	public static void main(String[] args) {
	    // 1. Test için veri oluşturma
	    List<Point> points = new ArrayList<>();
	    Random random = new Random();
	    int pointCount = 200000; // Eklenecek nokta sayısı
	    
	    System.out.println(pointCount + " adet rastgele nokta oluşturuluyor...");
	    for (int i = 0; i < pointCount; i++) {
	        long x = random.nextInt(1000); // 0-999 arası rastgele x koordinatı
	        long y = random.nextInt(1000); // 0-999 arası rastgele y koordinatı
	        points.add(new Point(x, y));
	    }
	    System.out.println("Nokta oluşturma tamamlandı.");
	    System.out.println("------------------------------------");

	    // 2. Seri Çözüm, Süre Ölçümü ve Sonuçları Yazdırma
	    try {
	        SerialConvexHull serialSolver = new SerialConvexHull();
	        long startTime = System.nanoTime();
	        List<Point> serialHull = serialSolver.solve(new ArrayList<>(points));
	        long endTime = System.nanoTime();
	        long duration = (endTime - startTime) / 1_000_000;
	        
	        System.out.println("Seri Çözüm " + serialHull.size() + " nokta buldu.");
	        System.out.println("Seri Çözüm Süresi: " + duration + " ms");
	        System.out.println("Seri Çözüm Dışbükey Zarf Noktaları:");
	        for (Point p : serialHull) {
	            System.out.println(p); // Her bir noktayı yazdır
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    System.out.println("------------------------------------");

	    // 3. Paralel Çözüm, Süre Ölçümü ve Sonuçları Yazdırma
	    try {
	        ParallelConvexHull parallelSolver = new ParallelConvexHull();
	        long startTime = System.nanoTime();
	        List<Point> parallelHull = parallelSolver.solve(new ArrayList<>(points));
	        long endTime = System.nanoTime();
	        long duration = (endTime - startTime) / 1_000_000;

	        System.out.println("Paralel Çözüm " + parallelHull.size() + " nokta buldu.");
	        System.out.println("Paralel Çözüm Süresi: " + duration + " ms");
	        System.out.println("Paralel Çözüm Dışbükey Zarf Noktaları:");
	        for (Point p : parallelHull) {
	            System.out.println(p); // Her bir noktayı yazdır
	        }
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	        Thread.currentThread().interrupt();
	    }
	}
}
