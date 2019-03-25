package ATM;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * A customer with username, password, list of their accounts, primary chequing account, and net total.
 */
class SystemUser_Customer extends SystemUser {

    private static final String user_type = SystemUser_Customer.class.getName();
    private final ArrayList<Account> accounts;
    private Account primary;
    private Inventory goods = new Inventory();
    SystemUser_Customer(String username, String password) {
        super(username, password);
        this.accounts = new ArrayList<>();
//        this.age = age;
    }
    //TODO: personal details
//    private int age;

    public String getUser_type() {
        return user_type;
    }

    /**
     * By default, first chequing account added is the primary account.
     *
     * @param account to be added
     */
    void addAccount(Account account) {
        this.accounts.add(account);
        // If a user has only one checking account, it will be the default destination for any deposits.
        if (primary == null && account instanceof Account_Asset_Chequing) {
            primary = account;
        }
    }

    ArrayList<Account> getAccounts() {
        return accounts;
    }

    boolean hasAccount(Account account) {
        for (Account a : this.accounts) {
            if (a.equals(account)) {
                return true;
            }

        }
        return false;
    }

    boolean hasMoreThanOneChequing() {
        int i = 0;
        for (Account a : this.accounts) {
            if (a instanceof Account_Asset_Chequing) {
                i++;
            }
        }
        return i > 1;
    }

    // The total of their debt account balances subtracted from the total of their asset account balances.
    private double netTotal() {
        double sum = 0;
        for (Account a : this.accounts) {
            sum += a.getBalance();
        }
        return sum;
    }

    // add a line of request in the alerts.text
    private void requestHelp(String s) throws IOException {
        String content = s + "\n";
        File file = new File("phase1/alerts.txt");
        FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(content);
        bw.close(); // Be sure to close BufferedWriter
    }

    // add a line of string in alert that request for new account
    void requestAccount(String accountType) throws IOException {
        String n = new Date() + ": Requesting to create " + accountType + " account from " + this.getUsername() + ".";
        requestHelp(n);
    }

    //TODO truman: toString interferes with serialization in Gson.fromJson
//    @Override
//    public String toString() {
//        StringBuilder returnMessage = new StringBuilder();
//        returnMessage.append("\n\u001B[1mPrimary\t\tAccount Type\t\tCreation Date\t\t\t\t\tBalance\t\tMost Recent Transaction" +
//                "\u001B[0m");
//        for (Account account : getAccounts()) {
//            if (account == primary) {
//                returnMessage.append("\nX\t\t\t").append(account);
//            } else {
//                returnMessage.append("\n\t\t\t").append(account);
//            }
//
//        }
//
//        returnMessage.append("\n\n\u001B[1mYour net total is \u001B[0m$").append(netTotal());
//
//        return returnMessage.toString();
//    }

    @Override
    public String toString() {
        return "Customer with username \"" + getUsername() + "\" and password \"" + getPassword() + "\"";
    }

    Account getPrimary() {
        return primary;
    }

//    int getAge() {return age;}

    void setPrimary(Account primary) {
        if (primary instanceof Account_Asset_Chequing) {
            this.primary = primary;
            System.out.println("Account is successfully set to primary.");
        } else {
            throw new IllegalArgumentException("Only chequing account can be set to primary.");
        }
    }
}
