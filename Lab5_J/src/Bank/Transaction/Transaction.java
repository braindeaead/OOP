package Bank.Transaction;

import java.util.*;

public class Transaction implements ITransaction {

    private UUID id;
    private UUID where;
    private UUID from;
    private Double amount;

    public Transaction(UUID from, UUID where, Double amount) {
        id = UUID.randomUUID();
        this.from = from;
        this.where = where;
        this.amount = amount;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public ITransaction getTransaction() {
        return this;
    }

    @Override
    public UUID getWhere() {
        return where;
    }

    @Override
    public UUID getFrom() {
        return from;
    }

    @Override
    public Double getAmount() {
        return amount;
    }

}
