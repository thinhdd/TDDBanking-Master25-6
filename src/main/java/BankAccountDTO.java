/**
 * Created with IntelliJ IDEA.
 * User: thinhdd
 * Date: 6/25/13
 * Time: 1:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class BankAccountDTO {
    String accountNumber;
    double balance;
    public BankAccountDTO(String accountNumber) {
        this.accountNumber=accountNumber;
        //To change body of created methods use File | Settings | File Templates.
    }

    public String getAccountNumber() {
        return this.accountNumber;  //To change body of created methods use File | Settings | File Templates.
    }

    public double getBalance() {
        return this.balance;  //To change body of created methods use File | Settings | File Templates.
    }
}
