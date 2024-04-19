package main.java.OrganizationObject;
import java.util.Objects;
public class Coordinates {
        private long x; //Значение поля должно быть больше -915 сделать проверку на это условие
        private int y;

        //Конструктор класс Coordinates
        public Coordinates(long x, Integer y) {
            if ((x == 0) || (x <= -915)) {
                throw new IllegalArgumentException("Значение x не может быть 0 и должно быть больше -915");
            }
            this.x = x;
            this.y = y;
        }
        public long getX() {
            return x;
        }
        public void setX(long x) {
            this.x = x;
        }
        public int getY() {
            return y;
        }
        public void setY(int y) {
            this.y = y;
        }
        @Override
    public boolean equals (Object object) {
            if (this == object) return  true;
            if (object == null || getClass() != object.getClass()) return false;
            Coordinates that = (Coordinates) object;
            return Double.compare(y, that.y) == 0 && Objects.equals(x, that.x);
        }
        @Override
    public int hashCode() {
            return Objects.hash(x, y);
        }
        @Override
    public String toString() {
            return "Координаты: (" + "x=" + x + ", y=" + y + ')';
        }
    }


