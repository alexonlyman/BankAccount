package alex_group;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentBank {

    private int transactionId;
    private int accountId;
    private final OperationsRepository operationsRepository;
    private final ReentrantLock lock = new ReentrantLock(true);

    public ConcurrentBank() {
        operationsRepository = new OperationsRepository();
    }


    public BankAccount createAccount(int amount) {
        lock.lock();
        try {
            BankAccount bankAccount = new BankAccount();
            bankAccount.deposit(amount);
            System.out.println("added amount " + amount);
            bankAccount.setId(accountId++);
            System.out.println("account was created with id " + bankAccount.getId());
            operationsRepository.createAccount(bankAccount);
            return bankAccount;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public void transfer(BankAccount inputAcc,BankAccount outputAcc,int amount  ) {
       lock.lock();
        try {
            BankAccount in = operationsRepository.findAccountById(inputAcc.getId());
            BankAccount out = operationsRepository.findAccountById(outputAcc.getId());

            in.setBalance(in.withdraw(amount));
            out.setBalance(out.deposit(amount));

            operationsRepository.saveTransactionId(transactionId++);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
           lock.unlock();
        }
    }

    public int totalBalance() {
       return operationsRepository.viewTotalBalance();
    }

    @Override
    public String toString() {
        return "ConcurrentBank{" +
                "transactionId=" + transactionId +
                '}';
    }
}
