package phase1;

import java.util.ArrayList;

/**
 * A customer's login account, with username, password, list of their accounts, primary account, and net total.
 */
class Login_Customer extends Login {
    private final ArrayList<Account> accounts;
    private Account primary;

    Login_Customer(String username, String password){
        super(username, password, "Customer");
        this.accounts = new ArrayList<>();
    }

    void addAccount(Account account){
        this.accounts.add(account);
        // If a user has only one checking account, it will be the default destination for any deposits.
        if(primary == null && account instanceof Account_Asset_Chequing){
            primary = account;
        }
    }

    void setPrimary(Account primary) {
        this.primary = primary;
    }

    ArrayList<Account> getAccounts() {
        return accounts;
    }

    boolean hasAccount(Account account){
        for(Account a : this.accounts){
            if(a.equals(account)){
                return true;
            }

        }
        return false;
    }

    // The total of their debt account balances subtracted from the total of their asset account balances.
    double netTotal() {
        double sum = 0;
        for (Account a : this.accounts) {
            if (a instanceof Account_Asset) {
                sum += a.getBalance();
            } else {
                sum -= a.getBalance();
            }
        }
        return sum;
    }

    void displayOptions(){
        System.out.println("1. Show summary of all account balances");
        System.out.println("2. Show most recent transaction on any account");
        System.out.println("3. Show date of creation of an account");
        System.out.println("4. See net worth.");
    }
}