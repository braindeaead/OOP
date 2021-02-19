package Bank;

import Bank.Account.Account;
import Bank.Client.Client;
import Bank.Transaction.Transaction;

import java.util.*;

public interface IBank {

    public UUID createClient(String name, String surname);
    public Client getClient(UUID id);
    public UUID addClientInfo(UUID id, String address);
    public UUID addClientInfo(UUID id, Integer passport);
    public UUID addClientInfo(UUID id, String address, Integer passport);
    public UUID newDebitAccount(UUID clientId);
    public UUID newDepositAccount(UUID clientId, Double amount, Integer term);
    public UUID newCreditAccount(UUID clientId);
    public boolean withdraw(Double amount, UUID accountId);
    public boolean deposit(Double amount, UUID accountID);
    public Double getStatus(UUID accountID);
    public boolean remittance(UUID from, UUID where, Double amount);
    public boolean cancelTransaction(UUID id, Integer type);
    public void iteration();
    public UUID getId();
    public Double getCommission();
}