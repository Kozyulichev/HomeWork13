import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class Car implements Runnable {
    private static int CARS_COUNT;

    static {
        CARS_COUNT = 0;
    }

    private Race race;
    private int speed;
    private String name;
    private CyclicBarrier br;
    private static AtomicInteger ai = new AtomicInteger(0);

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, CyclicBarrier br) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        this.br = br;
    }
    // CyclicBarrier br = new CyclicBarrier(race.getStages().size());

    @Override
    public void run() {
        // CyclicBarrier br = new CyclicBarrier(CARS_COUNT);
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            // br.await();
            System.out.println(this.name + " готов");
            br.await();
            br.await();
            for (int i = 0; i < race.getStages().size(); i++) {
                race.getStages().get(i).go(this);
            }
            if (ai.incrementAndGet() == 1){
                System.out.println(name + " WIN ");
            }
            br.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

