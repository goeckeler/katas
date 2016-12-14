package bank;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class RemoteAuditServiceTest {
    @Mock
    private HttpService httpService;

    private RemoteAuditService subject;
    
    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        subject = new RemoteAuditService(httpService);
    }

    @Test
    public void recordTransactionShouldSendRequest() {
        when(httpService.doPost(anyString(), anyString())).thenReturn(204);
        
        subject.recordTransaction(new Transaction(
                AccountNumber.of(1234),
                LocalDate.of(2015, 12, 31),
                new Money(100, 0, Currency.EUR)));
        
        verify(httpService).doPost(eq("https://auditservice/record"),
                argThat(allOf(
                        containsString("1234"),
                        containsString("31-12-2015"),
                        containsString("100.00"),
                        containsString("EUR"))));
    }
    
    @Test
    public void recordTransactionShouldThrowExceptionWhenTriesExceeded() {
        when(httpService.doPost(anyString(), anyString())).thenReturn(500);
        
        thrown.expect(AuditServiceException.class);
        
        subject.recordTransaction(new Transaction(
                AccountNumber.of(1234),
                LocalDate.of(2015, 12, 31),
                new Money(100, 0, Currency.EUR)));
    }
}
