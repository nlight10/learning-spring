package kz.gm.hazelcastlocks.methods;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.cp.lock.FencedLock;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Daddy: GM
 * BirthDate: 07.07.2021
 */
@Component
public class HazelcastMethod {

    private final HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();
    FencedLock importLock = hazelcastInstance.getCPSubsystem().getLock("import-lock");


    //запускаем несколько инстансев на разных портах
    public void doSomething() throws InterruptedException {

        // доп метод
        /*if (importLock.tryLock()) {
            System.out.println("Was free");
        } else  {
            System.out.println("Not free");
        }*/

        boolean isLocked = importLock.isLocked();
        if (isLocked) {
            System.out.println(" - is Locked: " + isLocked);
        }

        importLock.lock();
        try {
            System.out.println("Starting method | Locked | " + new Date(System.currentTimeMillis()));
            // System.out.println(" - Time: " + new Date(System.currentTimeMillis()));
            Thread.sleep(60000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            importLock.unlock();
            System.out.println("Ending method | Unlocked | " + new Date(System.currentTimeMillis()));
        }

    }
}
