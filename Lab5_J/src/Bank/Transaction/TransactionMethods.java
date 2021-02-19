package Bank.Transaction;

import java.util.*;

public class TransactionMethods {

    private ArrayList<Transaction> transactions;

    public TransactionMethods() {
        transactions = new ArrayList<Transaction>();
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public Transaction getTransaction(UUID id) {
        for (Transaction transaction : transactions) {
            if (transaction.getId().equals(id))
                return transaction;
        }
        return null;
    }

    public void deleteTransaction(UUID id) {
        for (Transaction transaction : transactions) {
            if (transaction.getId().equals(id)) {
                transactions.remove(transaction);
            }
        }
    }

    public boolean contains(UUID id) {
        for (Transaction transaction : transactions) {
            if (transaction.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

}
