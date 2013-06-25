import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: thinhdd
 * Date: 6/25/13
 * Time: 1:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class Transaction {
    static TransactionDAO transactionDAO;
    public static void setTransactionDAO(TransactionDAO mockTDao) {
        transactionDAO = mockTDao;
        //To change body of created methods use File | Settings | File Templates.
    }

    public static void openTransaction(String accountNumber, double amount, String des, boolean state) {
        TransactionDTO transactionDTO = new TransactionDTO(accountNumber,amount, des, state);
        transactionDAO.save(transactionDTO);
        //To change body of created methods use File | Settings | File Templates.
    }

    public static List<TransactionDTO> getAllTransaction(String accountNumber) {
        return transactionDAO.getAllTransacion(accountNumber);  //To change body of created methods use File | Settings | File Templates.
    }

    public static List<TransactionDTO> getAllTransaction(String accountNumber, long start, long end) {
        return transactionDAO.getAllTransacion(accountNumber, start, end);  //To change body of created methods use File | Settings | File Templates.
    }
}
