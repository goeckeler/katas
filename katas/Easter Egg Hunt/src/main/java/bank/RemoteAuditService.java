package bank;

import java.time.format.DateTimeFormatterBuilder;

import com.google.gson.Gson;

public final class RemoteAuditService implements AuditService {
    private final int MAX_TRIES = 5;
    
    private final HttpService httpService;
    private final Gson gson = new Gson();

    public RemoteAuditService(HttpService httpService) {
        this.httpService = httpService;
    }

    @Override
    public void recordTransaction(Transaction transaction) {
        int status = 0, tries = 0;
        
        while(status != 204 && ++tries < MAX_TRIES) {
            status = httpService.doPost("https://auditservice/record",
                    gson.toJson(TransactionDto.fromTransaction(transaction)));
        }
        
        if(status != 204) {
            throw new AuditServiceException(
                    "Maximum number of tries exceeeded connecting to audit service");
        }
    }

    @SuppressWarnings("unused") // Used by Gson
    private static final class TransactionDto {
        private final String accountNumber;
        private final String transactionDate;
        private final String amount;
        private final String currencyCode;
        private final String bankCodeSec = "2XGC918";
        private final String bankCodeInf = "33-EG1-19398";

        public static TransactionDto fromTransaction(Transaction transaction) {
            return new TransactionDto(transaction);
        }
        
        private TransactionDto(Transaction transaction) {
            accountNumber = transaction.getAccountNumber().toString();
            transactionDate = transaction.getTransactionDate()
                    .format(new DateTimeFormatterBuilder().appendPattern("d-M-y").toFormatter());
            amount = transaction.getAmount().format(5);
            currencyCode = transaction.getAmount().getCurrency().toString();
        }
    }
}
