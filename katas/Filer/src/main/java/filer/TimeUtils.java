package filer;

import java.time.Duration;

public class TimeUtils
{
  private static long[] divisors = { 60, 60, 24 };
  private static String[] pluralFormat = { " seconds", " minutes", " hours", " days" };
  private static String[] singularFormat = { " second", " minute", " hour", " day" };
  
  private static TimeUtils singleton = new TimeUtils();
  
  public static String durationForHumans(final Duration duration) {
    return singleton.format(duration);
  }

  public String format(final Duration duration) {
    long seconds = duration.getSeconds();
    return durationForHumans(seconds, 0).substring(2);
  }

  private String durationForHumans(long seconds, int index) {
    if (index == 0 && seconds == 0) return ", 0" + pluralFormat[index];
    
    if (seconds == 0 || index >= divisors.length) {
       return toString(seconds, index);
    }
    
    return durationForHumans(seconds / divisors[index], index + 1) + toString(seconds % divisors[index], index);
  }

  private String toString(long temporal, int index) {
    if (temporal == 0) return "";
    return ", " + temporal + (temporal == 1 ? singularFormat[index] : pluralFormat[index]);
  }
}
