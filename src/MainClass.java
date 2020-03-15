import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;

public class MainClass {
    public static final int CARS_COUNT = 4;
    public static final int length1 = 60;
    public static final int length2 = 40;
    public static final CountDownLatch countStart = new CountDownLatch(CARS_COUNT);
    public static final ArrayBlockingQueue<Car>  tunnel = new ArrayBlockingQueue(CARS_COUNT / 2);
    public static final CountDownLatch fin = new CountDownLatch(CARS_COUNT);
    public static void main(String[] args) {


        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(length1), new Tunnel(), new Road(length2));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }

        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }

        try {
            MainClass.countStart.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");

        try {
            fin.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}

