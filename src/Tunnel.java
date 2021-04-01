import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    private static int TUNEL_PLACE = 2;
    private Semaphore sema = new Semaphore(TUNEL_PLACE + 1);
    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }
    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                sema.acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                sema.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}