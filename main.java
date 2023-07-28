import java.util.ArrayList;
import java.util.concurrent.Semaphore;

class DiningPhilosophers {
  public static void main(String[] args) {
    // parameterize this later
    int numPhilosophers = 10;
    int apetite = 10;

    // create forks
    ArrayList<Semaphore> forks = new ArrayList();
    for (int i = 0; i < (numPhilosophers == 1 ? 2 : numPhilosophers); i++) {
      forks.add(new Semaphore(1));
    }

    // create Philosophers
    ArrayList<Philosopher> philosophers = new ArrayList();

    for (int i = 0; i < numPhilosophers; i++) {
      philosophers.add(new Philosopher(i + 1, apetite, forks.get(i), forks.get(i == numPhilosophers - 1 ? 0 : i + 1)));
    }

    for (Philosopher p : philosophers) {
      p.start();
    }
  }
}
