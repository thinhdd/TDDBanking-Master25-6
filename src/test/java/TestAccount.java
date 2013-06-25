import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Calendar;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: thinhdd
 * Date: 6/25/13
 * Time: 1:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestAccount {
    public BankAccountDAO mockDao = mock(BankAccountDAO.class);
    public String accountNumber = "123456";
    public Calendar calendar = mock(Calendar.class);
    @Before
    public void setUp()
    {
        reset(mockDao);
        reset(calendar);
        BankAccount.setBankAccountDAO(mockDao);
        BankAccountDTO.setTimeStamp(calendar);
    }
    @Test
    public void testOpenAccount()
    {
        ArgumentCaptor<BankAccountDTO> ac = ArgumentCaptor.forClass(BankAccountDTO.class);
        BankAccount.openAccount(accountNumber);
        verify(mockDao).save(ac.capture());
        assertEquals(accountNumber, ac.getValue().getAccountNumber());
        assertEquals(0.0, ac.getValue().getBalance());
    }
    @Test
    public void testGetAccount()
    {
        BankAccountDTO account = BankAccount.openAccount(accountNumber);
        when(mockDao.getAccount(accountNumber)).thenReturn(account);
        BankAccountDTO accountRe = BankAccount.getAccount(accountNumber);
        verify(mockDao).getAccount(accountNumber);
        assertEquals(account, accountRe);
    }
    @Test
    public void testCheckTimeStamp()
    {
        ArgumentCaptor<BankAccountDTO> ac = ArgumentCaptor.forClass(BankAccountDTO.class);
        when(calendar.getTimeInMillis()).thenReturn(1000l);
        BankAccount.openAccount(accountNumber);
        verify(mockDao).save(ac.capture());
        assertEquals(ac.getValue().getAccountNumber(), accountNumber);
        assertEquals(ac.getValue().getBalance(), 0.0);
        assertEquals(ac.getValue().getTimeStamp(), 1000l);;
    }
}
