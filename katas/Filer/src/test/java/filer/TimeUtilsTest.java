package filer;

import static filer.TimeUtils.durationForHumans;
import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.time.LocalDateTime;

import org.junit.Test;

public class TimeUtilsTest
{
  @Test
  public void shouldReportTimeInSeconds() {
    final LocalDateTime startTime = LocalDateTime.now();
    final LocalDateTime endTime = startTime.plusSeconds(4);
    assertEquals("4 seconds", durationForHumans(Duration.between(startTime, endTime)));
  }

  @Test
  public void shouldReportTimeInMinutesAndSeconds() {
    final LocalDateTime startTime = LocalDateTime.now();
    final LocalDateTime endTime = startTime.plusSeconds(4).plusMinutes(2);
    assertEquals("2 minutes, 4 seconds", durationForHumans(Duration.between(startTime, endTime)));
  }

  @Test
  public void shouldReportTimeInDaysOnly() {
    final LocalDateTime startTime = LocalDateTime.now();
    final LocalDateTime endTime = startTime.plusDays(8);
    assertEquals("8 days", durationForHumans(Duration.between(startTime, endTime)));
  }

  @Test
  public void shouldReportTimeInDaysHoursMinutesAndSeconds() {
    final LocalDateTime startTime = LocalDateTime.now();
    final LocalDateTime endTime = startTime.plusDays(8).plusHours(3).plusMinutes(12).plusSeconds(2);
    assertEquals("8 days, 3 hours, 12 minutes, 2 seconds", durationForHumans(Duration.between(startTime, endTime)));
  }

  @Test
  public void shouldReportTimeInSingularForm() {
    final LocalDateTime startTime = LocalDateTime.now();
    final LocalDateTime endTime = startTime.plusDays(1).plusHours(1).plusMinutes(1).plusSeconds(1);
    assertEquals("1 day, 1 hour, 1 minute, 1 second", durationForHumans(Duration.between(startTime, endTime)));
  }
}
