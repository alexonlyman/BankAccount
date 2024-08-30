package alex_group;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {

    private int balance;
    private int id;
    private final Lock lock = new ReentrantLock();

    public BankAccount() {
    }

    public int deposit(int amount) {
        lock.lock();
        try {
            balance += amount;
            return balance;
        } finally {
            lock.unlock();
        }
    }

    public Lock getLock() {
        return lock;
    }

    public  int withdraw(int amount) {
        lock.lock();
        try {
            balance -= amount;
            return balance;
        } finally {
            lock.unlock();
        }

    }

    public  int getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }

    public void setBalance(int balance) {
        lock.lock();
        try {
            this.balance = balance;
        } finally {
            lock.unlock();
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "balance=" + balance +
                ", id=" + id +
                '}';
    }
}
