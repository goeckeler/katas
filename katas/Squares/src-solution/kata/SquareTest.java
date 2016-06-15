package kata;

import static kata.Squares.square;
import static kata.Squares.times100;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SquareTest
{

  @Test
  public void shouldSquareGreater1x1() {
    assertThat(square(1L), equalTo(1L));
    assertThat(square(2L), equalTo(4L));
    assertThat(square(24L), equalTo(576L));
  }

  @Test
  public void shouldSquareNegativeNumbers() {
    assertThat(square(-2L), equalTo(4L));
    assertThat(square(-24L), equalTo(576L));
    assertThat(square(-77L), equalTo(5929L));
  }

  @Test
  public void shouldSquareLargerNumbers() {
    assertThat(square(62L), equalTo(3844L));
    assertThat(square(44L), equalTo(1936L));
    assertThat(square(77L), equalTo(5929L));
  }

  @Test
  public void shouldMultiplyBy100() {
    assertThat(times100(1L), equalTo(100L));
  }
}
