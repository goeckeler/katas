package bank;

public class AuditServiceException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public AuditServiceException(String message) {
        super(message);
    }
}
