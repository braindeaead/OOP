package Bank;

import Bank.Account.Account;
import Bank.Client.Client;
import Bank.Transaction.Transaction;

import java.util.*;

public class FirstBank extends Bank implements IBank {

    private static FirstBank Fbank = null;
    private Map<Client, ArrayList<Account>> clients;
    private UUID bankId;
    private Double percent = 0.05;
    private Double commission = 0.03;
    private Double creditLimit = 50000.0;
    private Double susWithdrawLimit = 10000.0;
    private Double susRemittanceLimit = 5000.0;

    private FirstBank() {
        bankId = UUID.randomUUID();
        clients = new HashMap<>();
    }

    public static FirstBank getFBank() {
        if (Fbank == null) {
            Fbank = new FirstBank();
            banks.add(FirstBank.getFBank());
        }
        return Fbank;
    }

    @Override
    public UUID createClient(String name, String surname) {
        return super.newClient(name, surname, clients);
    }

    @Override
    public Client getClient(UUID id) {
        for (Client client : clients.keySet()) {
            if (client.getId().equals(id))
                return client;
        }

        return null;
    }

    @Override
    public UUID addClientInfo(UUID id, String address) {
        return super.addClientInfo(id, address, clients);
    }

    @Override
    public UUID addClientInfo(UUID id, Integer passport) {
        return super.addClientInfo(id, passport, clients);
    }

    @Override
    public UUID addClientInfo(UUID id, String address, Integer passport) {
        return super.addClientInfo(id, address, passport, clients);
    }

    @Override
    public UUID newDebitAccount(UUID clientId) {
        return super.newDebit(clientId, bankId, clients, percent);
    }

    @Override
    public UUID newDepositAccount(UUID clientId, Double amount, Integer term) {
        return super.newDeposit(clientId, bankId, clients, amount, term);
    }

    @Override
    public UUID newCreditAccount(UUID clientId) {
        return super.newCredit(clientId, bankId, clients, creditLimit, commission);
    }

    @Override
    public boolean withdraw(Double amount, UUID accountId) {
        if (super.withdraw(accountId, amount, susWithdrawLimit, clients))
            succeed.add(new Transaction(accountId, accountId, amount));
        return super.withdraw(accountId, amount, susWithdrawLimit, clients);
    }

    @Override
    public boolean deposit(Double amount, UUID accountId) {
        if (super.deposit(accountId, amount, clients))
            succeed.add(new Transaction(accountId, accountId, amount));
        return super.deposit(accountId, amount, clients);
    }

    @Override
    public Double getStatus(UUID accountID) {
        for (Client client : clients.keySet()) {
            for (Account account : clients.get(client)) {
                if (account.getId().equals(accountID))
                    return account.getStatus();
            }
        }
        return null;
    }

    @Override
    public boolean remittance(UUID from, UUID where, Double amount) {
        if (super.remittance(from, where, amount, susRemittanceLimit, clients))
            succeed.add(new Transaction(from, where, amount));
        return super.remittance(from, where, amount, susRemittanceLimit, clients);
    }

    @Override
    public boolean cancelTransaction(UUID id, Integer type) {
        return super.cancelTransaction(id, bankId, type, clients);
    }

    @Override
    public void iteration() {
        for (Client client : clients.keySet())
            for (Account account : clients.get(client))
                account.percent();
    }

    @Override
    public UUID getId() {
        return bankId;
    }

    @Override
    public Double getCommission() {
        return commission;
    }
}
