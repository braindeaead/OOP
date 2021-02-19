package Bank.Account;

import java.util.*;

public class Deposit implements Account{

    private UUID id;
    private UUID bankId;
    private Double status;
    private Double percent;
    private Double monthLast;
    private Integer days;
    private Integer term;

    public Deposit(Double status, Integer term, UUID bankId) {
        id = UUID.randomUUID();
        this.status = status;
        this.term = term;
        this.bankId = bankId;
        monthLast = 0.0;
        days = 30;

        if (status < 50000)
            percent = 0.03;
        else if (status >= 50000 && status < 100000)
            percent = 0.035;
        else
            percent = 0.04;

    }

    @Override
    public boolean withdraw(Double amount) {
        if (term != 0 && status >= amount) {
            status -= amount;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void deposit(Double amount) {
        status += amount;

        if (status < 50000)
            percent = 0.03;
        else if (status >= 50000 && status < 100000)
            percent = 0.035;
        else
            percent = 0.04;
    }

    @Override
    public void transWithdraw(Double amount) {
        status -= amount;
    }

    @Override
    public void transDeposit(Double amount) {
        status += amount;
    }

    @Override
    public Double getStatus() {
        return status;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public UUID getBankID() {
        return bankId;
    }

    @Override
    public void percent() {
        term--;
        if (days != 0) {
            monthLast += status * percent;
            days--;
        } else {
            status += monthLast + status * percent;
            monthLast = 0.0;
            days = 30;
        }
    }

    public Integer getTerm() {
        return term;
    }
}
