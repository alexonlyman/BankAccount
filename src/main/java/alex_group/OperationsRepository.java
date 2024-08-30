package alex_group;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class OperationsRepository {
    private final ConcurrentHashMap<Integer, BankAccount> bankAccounts = new ConcurrentHashMap<>();
    private final List<Integer> transactionIdList = Collections.synchronizedList(new ArrayList<>());

    public void createAccount(BankAccount bankAccount) {
        bankAccounts.put(bankAccount.getId(), bankAccount);
    }

    public BankAccount findAccountById(Integer id) {
        return bankAccounts.get(id);

    }


    public void saveTransactionId(Integer id) {
        transactionIdList.add(id);
    }

    public int viewTotalBalance() {
        int totalBalance = 0;
        for (Map.Entry<Integer, BankAccount> entry : bankAccounts.entrySet()) {
            int balance = entry.getValue().getBalance();
            totalBalance += balance;
        }
        return totalBalance;
    }
}
