/**
 * Created with IntelliJ IDEA.
 * User: thinhdd
 * Date: 6/25/13
 * Time: 1:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class BankAccount {
    static  BankAccountDAO bankAccountDAO;
    public static void setBankAccountDAO(BankAccountDAO mockDao) {
        bankAccountDAO=mockDao;
        //To change body of created methods use File | Settings | File Templates.
    }

    public static BankAccountDTO openAccount(String accountNumber) {
        BankAccountDTO account = new BankAccountDTO(accountNumber);
        bankAccountDAO.save(account);
        return account;
        //To change body of created methods use File | Settings | File Templates.
    }

    public static BankAccountDTO getAccount(String accountNumber) {
        return bankAccountDAO.getAccount(accountNumber);  //To change body of created methods use File | Settings | File Templates.
    }

    public static void doDeposit(String accountNumber, double amount, String des) {
        BankAccountDTO accountDTO = bankAccountDAO.getAccount(accountNumber);
        accountDTO.setBalance(amount);
        bankAccountDAO.save(accountDTO);
        //To change body of created methods use File | Settings | File Templates.
    }
}
