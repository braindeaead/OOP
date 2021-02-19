package Bank;

import Bank.Account.Account;
import Bank.Account.Credit;
import Bank.Account.Debit;
import Bank.Account.Deposit;
import Bank.Client.Client;
import Bank.Transaction.Transaction;

import java.util.*;

public abstract class Bank {
    public static ArrayList<Transaction> succeed = new ArrayList<>();
    public static ArrayList<IBank> banks = new ArrayList<>();

    public UUID newClient(String name, String surname, Map<Client, ArrayList<Account>> clients) {
        Client newClient = new Client(name, surname);
        clients.put(newClient, new ArrayList<>());
        return newClient.getId();
    }


    public UUID addClientInfo(UUID id, String address, Integer passport, Map<Client, ArrayList<Account>> clients) {
        Client client = getClient(id, clients);
        ArrayList<Account> accounts = clients.get(client);
        clients.remove(client);
        client.updateInfo(address, passport);
        clients.put(client, accounts);
        return client.getId();
    }

    public UUID addClientInfo(UUID id, String address, Map<Client, ArrayList<Account>> clients) {
        Client client = getClient(id, clients);
        ArrayList<Account> accounts = clients.get(client);
        clients.remove(client);
        client.updateInfo(address);
        clients.put(client, accounts);
        return client.getId();
    }

    public UUID addClientInfo(UUID id, Integer passport, Map<Client, ArrayList<Account>> clients) {
        Client client = getClient(id, clients);
        ArrayList<Account> accounts = clients.get(client);
        clients.remove(client);
        client.updateInfo(passport);
        clients.put(client, accounts);
        return client.getId();
    }

    public UUID newDebit(UUID clientId, UUID bankId, Map<Client, ArrayList<Account>> clients, Double percent) {
        Account debit = new Debit(0.0, percent/365, bankId);
        clients.get(getClient(clientId, clients)).add(debit);
        return debit.getId();
    }

    public UUID newDeposit(UUID clientId, UUID bankId, Map<Client, ArrayList<Account>> clients, Double status, Integer term) {
        Account deposit = new Deposit(status, term, bankId);
        clients.get(getClient(clientId, clients)).add(deposit);
        return deposit.getId();
    }

    public UUID newCredit(UUID clientId, UUID bankId, Map<Client, ArrayList<Account>> clients, Double limit, Double commission) {
        Account credit = new Credit(0.0, limit, commission, bankId);
        clients.get(getClient(clientId, clients)).add(credit);
        return credit.getId();
    }

    public boolean withdraw(UUID accountId, Double amount, Double susLimit, Map<Client, ArrayList<Account>> clients) {
        for (Client client : clients.keySet()) {
            for (Account account : clients.get(client)) {
                if (accountId.equals(account.getId()) && !client.isSus()) {
                    return account.withdraw(amount);
                } else if (accountId.equals(account.getId()) && client.isSus() && amount <= susLimit) {
                    return account.withdraw(amount);
                }
            }
        }
        return false;
    }

    public boolean deposit(UUID accountId, Double amount, Map<Client, ArrayList<Account>> clients) {
        for (Client client : clients.keySet()) {
            for (Account account : clients.get(client)) {
                if (account.getId().equals(accountId)) {
                    account.deposit(amount);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean remittance(UUID from, UUID where, Double amount, Double susLimit, Map<Client, ArrayList<Account>> clients) {
        IBank bankFrom;
        IBank bankWhere;
        for (IBank bank : banks) {
            if (bank.getId().equals(getAccount(from,clients).getBankID()))
                bankFrom = bank;
            if (bank.getId().equals(getAccount(where, clients).getBankID()))
                bankWhere = bank;
        }

        if (getAccount(from, clients).getStatus() < amount)
            return false;

        if (withdraw(from, amount, susLimit, clients)) {
            deposit(where, amount, clients);
            return true;
        }

        return false;

    }

    private boolean transWithdraw(Transaction trans, Map<Client, ArrayList<Account>> clients) {
            for (Client client : clients.keySet()) {
                for (Account account : clients.get(client)) {
                    if (account.getId().equals(trans.getWhere())) {
                        account.transWithdraw(trans.getAmount());
                        return true;
                    }
                }
            }
            return false;
    }

    private boolean transDeposit(Transaction trans, Map<Client, ArrayList<Account>> clients) {
        for (Client client : clients.keySet()) {
            for (Account account : clients.get(client)) {
                if (account.getId().equals(trans.getFrom())) {
                    account.transWithdraw(trans.getAmount());
                    return true;
                }
            }
        }
        return false;
    }

    public boolean cancelTransaction(UUID transId, UUID bankId, Integer type, Map<Client, ArrayList<Account>> clients) {
        Transaction trans = null;
        for (Transaction transaction : succeed) {
            if (transaction.getId().equals(transId))
                trans = transaction;
        }
        switch (type) {
            case 1: // withdrawal
                if (!trans.getFrom().equals(bankId))
                    return false;
                else
                    return transDeposit(trans, clients);
            case 2: // deposit
                if (!trans.getWhere().equals(bankId))
                    return false;
                else
                    return transWithdraw(trans, clients);
            case 3: // transfer
                if (!trans.getWhere().equals(bankId) || !trans.getFrom().equals(bankId))
                    return false;
                else
                    return transDeposit(trans, clients) && transWithdraw(trans, clients);
        }

        return false;
    }

    private Client getClient(UUID id, Map<Client, ArrayList<Account>> clients) {
        for (Client client : clients.keySet()) {
            if (client.getId().equals(id))
                return client;
        }
        return null;
    }

    private Account getAccount(UUID id, Map<Client, ArrayList<Account>> clients) {
        for (Client client : clients.keySet()) {
            for (Account account : clients.get(client)) {
                if (account.getId().equals(id))
                    return account;
            }
        }
        return null;
    }
}
