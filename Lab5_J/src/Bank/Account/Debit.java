package Bank.Account;

import java.util.*;

public class Debit implements Account {

    private UUID id;
    private UUID bankId;
    private Double status;
    private Double percent;
    private Double monthLast;
    private Integer days;

    public Debit(Double status, Double percent, UUID bankId) {
        id = UUID.randomUUID();
        this.bankId = bankId;
        this.status = status;
        this.percent = percent;
        monthLast = 0.0;
        days = 30;
    }

    @Override
    public boolean withdraw(Double amount) {
        if (status >= amount) {
            this.status -= amount;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void deposit(Double amount) {
        this.status += amount;
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
        if (days != 0) {
            monthLast += status * percent;
            days--;
        } else {
            status += monthLast + status * percent;
            monthLast = 0.0;
            days = 30;
        }
    }
}
