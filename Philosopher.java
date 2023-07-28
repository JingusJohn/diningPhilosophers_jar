import java.util.Random;
import java.util.concurrent.Semaphore;

class Philosopher extends Thread {
  private int id;
  private int apetite;
  private Semaphore leftFork;
  private Semaphore rightFork;
  private Random rng = new Random();
  
  public Philosopher(int id, int apetite, Semaphore leftFork, Semaphore rightFork) {
    this.id = id;
    this.apetite = apetite;
    this.leftFork = leftFork;
    this.rightFork = rightFork;
  }

  public void eat() throws InterruptedException{
    System.out.println("Philosopher " + id + " is eating");
    Thread.sleep(rng.nextInt(100));
    apetite--;
  }

  public void think() {
    System.out.println("Philosopher " + id + " is thinking");
  }

  @Override
  public void run() {
    while (apetite > 0) {
      think();

      try {
        // even philosophers acquire left fork first
        if (id % 2 == 0) {
          leftFork.acquire();
          rightFork.acquire();
        } else {
          rightFork.acquire();
          leftFork.acquire();
        }
        eat();
      } catch (InterruptedException e) {
        System.out.println(e);
      }

      if (id % 2 == 0) {
        leftFork.release();
        rightFork.release();
      } else {
        rightFork.release();
        leftFork.release();
      }
    }
  }
}
