package kata;

public class MyCheckedException
  extends Exception
{
  private static final long serialVersionUID = -2159675459515657722L;

  public MyCheckedException() {
    super("My custom checked exception");
  }
}
