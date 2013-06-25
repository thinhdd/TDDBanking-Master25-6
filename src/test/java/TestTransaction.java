import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Calendar;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: thinhdd
 * Date: 6/25/13
 * Time: 1:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestTransaction {
    public BankAccountDAO mockDao = mock(BankAccountDAO.class);
    public TransactionDAO mockTDao = mock(TransactionDAO.class);
    public Calendar calendar = mock(Calendar.class);
    public String accountNumber = "123456";
    @Before
    public void setUp()
    {
        reset(mockDao);
        reset(mockTDao);
        reset(calendar);
        BankAccount.setBankAccountDAO(mockDao);
        Transaction.setTransactionDAO(mockTDao);
        TransactionDTO.setCalendar(calendar);
    }

    @Test
    public void testDoDeposit()
    {
        ArgumentCaptor<BankAccountDTO> ac = ArgumentCaptor.forClass(BankAccountDTO.class);
        BankAccountDTO accountDTO = BankAccount.openAccount(accountNumber);
        when(mockDao.getAccount(accountNumber)).thenReturn(accountDTO);
        BankAccount.doDeposit(accountNumber, 100.0, "Them 100k");
        verify(mockDao, times(2)).save(ac.capture());
        List<BankAccountDTO> list = ac.getAllValues();
        assertEquals(accountNumber, list.get(1).getAccountNumber());
        assertEquals(100.0, list.get(1).getBalance());
    }
    @Test
    public void testSaveDeposit()
    {
        ArgumentCaptor<TransactionDTO> act = ArgumentCaptor.forClass(TransactionDTO.class);
        BankAccountDTO accountDTO = BankAccount.openAccount(accountNumber);
        when(mockDao.getAccount(accountNumber)).thenReturn(accountDTO);
        when(calendar.getTimeInMillis()).thenReturn(1000l);
        BankAccount.doDeposit(accountNumber, 100.0, "Them 100k");
        verify(mockTDao).save(act.capture());
        assertEquals(accountNumber, act.getValue().getAccountNumber());
        assertEquals(100.0, act.getValue().getAmount());
        assertEquals("Them 100k", act.getValue().getDes());
        assertEquals(1000l, act.getValue().getTimeStamp());
    }
    @Test
    public void testDoWithDraw()
    {
        ArgumentCaptor<BankAccountDTO> ac = ArgumentCaptor.forClass(BankAccountDTO.class);
        BankAccountDTO accountDTO = BankAccount.openAccount(accountNumber);
        when(mockDao.getAccount(accountNumber)).thenReturn(accountDTO);
        BankAccount.doDeposit(accountNumber, 100.0, "Them 100k");
        BankAccount.doWithDraw(accountNumber, 50.0, "Rut 50k");
        verify(mockDao, times(3)).save(ac.capture());
        List<BankAccountDTO> list = ac.getAllValues();
        assertEquals(accountNumber, list.get(2).getAccountNumber());
        assertEquals(50.0, list.get(2).getBalance());
    }
    @Test
    public void testSaveWithDraw()
    {
        ArgumentCaptor<TransactionDTO> act = ArgumentCaptor.forClass(TransactionDTO.class);
        BankAccountDTO accountDTO = BankAccount.openAccount(accountNumber);
        when(mockDao.getAccount(accountNumber)).thenReturn(accountDTO);
        when(calendar.getTimeInMillis()).thenReturn(1000l).thenReturn(2000l);
        BankAccount.doDeposit(accountNumber, 100.0, "Them 100k");
        BankAccount.doWithDraw(accountNumber, 50.0, "Rut 50k");
        verify(mockTDao,times(2)).save(act.capture());
        List<TransactionDTO> list = act.getAllValues();
        assertEquals(accountNumber, list.get(1).getAccountNumber());
        assertEquals(50.0, list.get(1).getAmount());
        assertEquals("Rut 50k", list.get(1).getDes());
        assertEquals(2000l, list.get(1).getTimeStamp());
    }
}
