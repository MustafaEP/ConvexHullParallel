package Project;

import java.util.Objects;

//Noktaları temsil eden sınıf. Sıralanabilmesi için Comparable arayüzünü uygular.
class Point implements Comparable<Point> {
 final long x, y; // Sağlamlık için long kullanıyoruz.

 public Point(long x, long y) {
     this.x = x;
     this.y = y;
 }

 @Override
 public int compareTo(Point other) {
     // Önce x koordinatına göre karşılaştır.
     if (this.x != other.x) {
         return Long.compare(this.x, other.x);
     }
     // Eğer x'ler eşitse, y koordinatına göre karşılaştır.
     return Long.compare(this.y, other.y);
 }

 // Eşitlik ve hash code metodları, koleksiyonlarda doğru çalışması için önemlidir.
 @Override
 public boolean equals(Object o) {
     if (this == o) return true;
     if (o == null || getClass() != o.getClass()) return false;
     Point point = (Point) o;
     return x == point.x && y == point.y;
 }

 @Override
 public int hashCode() {
     return Objects.hash(x, y);
 }

 @Override
 public String toString() {
     return "(" + x + ", " + y + ")";
 }
}