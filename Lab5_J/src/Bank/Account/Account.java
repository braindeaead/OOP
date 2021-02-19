package Bank.Account;

import java.util.*;

public interface Account {
    public boolean withdraw(Double amount);
    public void deposit(Double amount);
    public void transWithdraw(Double amount);
    public void transDeposit(Double amount);
    public Double getStatus();
    public UUID getId();
    public UUID getBankID();
    public void percent();
}
