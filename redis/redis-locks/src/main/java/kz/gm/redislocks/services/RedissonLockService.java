package kz.gm.redislocks.services;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Daddy: GM
 * BirthDate: 08.07.2021
 */
@Service
public class RedissonLockService {

    @Autowired
    private RedissonClient client;

    public void doSomething() {
        RLock importLock = client.getLock("import-lock");

        boolean isLocked = importLock.isLocked();
        if (isLocked) {
            System.out.println(" - is Locked: " + isLocked);
        }

        importLock.lock();
        try {
            System.out.println("Starting method | Locked | " + new Date(System.currentTimeMillis()));
            Thread.sleep(30000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            importLock.unlock();
            System.out.println("Ending method | Unlocked | " + new Date(System.currentTimeMillis()));
        }

    }
}
