package phase1;

class Account_Debt_CreditCard extends Account_Debt {
    /*
     * These are accounts that display a positive balance when the user owes a money and a negative balance
     * when the user overpays. It is not possible to transfer money out of a credit account.
     * But it is possible to transfer money in.
     *
     */

    /**
     * Balance is set to 0.00 as default if an initial balance is not provided.
     */
    public Account_Debt_CreditCard(Login_Customer owner) {
        super(owner);
    }

    public Account_Debt_CreditCard(double balance, Login_Customer owner) {
        super(balance, owner);
    }

    void setBalance(double initialBalance) {
        balance = initialBalance;
    }
}