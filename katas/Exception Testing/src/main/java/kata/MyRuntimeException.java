package kata;

public class MyRuntimeException
  extends RuntimeException
{
  private static final long serialVersionUID = -7458155052742287475L;

  private int code = 404;

  public MyRuntimeException() {
    super("My custom runtime exception");
  }

  public MyRuntimeException(int code) {
    this.code = code;
  }

  public MyRuntimeException(Throwable cause) {
    super("My custom runtime exception", cause);
  }

  public int getCode() {
    return code;
  }
}
