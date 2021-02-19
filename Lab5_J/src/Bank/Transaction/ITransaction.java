package Bank.Transaction;

import java.util.*;

public interface ITransaction {

    public UUID getId();
    public ITransaction getTransaction();
    public UUID getWhere();
    public UUID getFrom();
    public Double getAmount();
}
