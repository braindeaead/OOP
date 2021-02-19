package Bank.Account;

import java.util.UUID;

public class Credit implements Account {

    private UUID id;
    private UUID bankId;
    private Double status;
    private Double limit;
    private Double commission;

    public Credit(Double status, Double limit, Double commission, UUID bankId) {
        id = UUID.randomUUID();
        this.limit = limit;
        this.commission = commission;
        this.bankId = bankId;
        this.status = status;
    }

    @Override
    public boolean withdraw(Double amount) {
        if (status >= 0 && status + limit >= amount) {
            status -= amount;
            return true;
        } else if (status < 0 && (amount + amount * commission) <= limit - Math.abs(status)) {
            status -= amount + amount * commission;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void deposit(Double amount) {
        status += amount;
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

    }
}
