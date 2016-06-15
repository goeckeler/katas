package kata;

public class Squares
{
  private static long[] lookup = {
    0L, 1L, 4L, 9L, 16L, 25L, 36L, 49L, 64L, 81L, 100L, 121L, 144L, 169L, 196L, 225L, 256L, 289L, 324L, 361L, 400L,
    441L, 484L, 529L, 576L, 625L
  };

  public static long square(final long number) {
    if (number < 0) return square(-number);
    if (number <= lookup.length) { return lookup[(int) number]; }

    // use n^2 = (n - 25) * 100 + (n - 50)^2
    return times100(number - 25L) + square(number - 50L);
  }

  static long times100(final long number) {
    final long times64 = number << 6;
    final long times32 = number << 5;
    final long times4 = number << 2;
    return times64 + times32 + times4;
  }
}
