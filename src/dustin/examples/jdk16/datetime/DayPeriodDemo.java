package dustin.examples.jdk16.datetime;

import static java.lang.System.out;

import java.time.Instant;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;

/**
 * Demonstrates Day Period support added via JDK-8247781 ("Day periods support")
 * and introduced via JDK 16 Early Access Build 25 (18 November 2020).
 *
 * https://bugs.openjdk.java.net/browse/JDK-8247781
 * http://unicode.org/reports/tr35/tr35-dates.html#dayPeriods
 */
public class DayPeriodDemo
{
   /** Default Locale if country/language are not otherwise specified. */
   private final static Locale DEFAULT_LOCAL = Locale.US;

   /**
    * Writes the current day period out to standard output.
    *
    * This is based on the example included with the Release Notes
    * (https://jdk.java.net/16/release-notes).
    */
   public void printCurrentDayPeriod()
   {
      final String currentDayPeriodStr
         = DateTimeFormatter.ofPattern("B").format(LocalTime.now());
      out.println("Pattern 'B' (time now): \"" + currentDayPeriodStr + "\"");
   }

   /**
    * Prints representation of supplied {@link ZonedDateTime} with hour,
    * day period, and time zone details to standard output.
    *
    * @param zonedDateTime Date/time that will be written to standard output
    *    and will include hour, day period, and zone details.
    */
   public void printHourDayPeriodAndZone(final ZonedDateTime zonedDateTime)
   {
      final String dateTimeStr
         = DateTimeFormatter.ofPattern("hh B, zzzz").format(zonedDateTime);
      out.println("Hour/Day Period/Zone: \"" + dateTimeStr + "\"");
   }

   /**
    * Prints representation of supplied {@link ZonedDateTime} with hour,
    * minutes, day period, and time zone details to standard output.
    *
    * @param zonedDateTime Date/time that will be written to standard output
    *    and will include hour, minutes, day period, and zone details.
    */
   public void printHourDayMinutePeriodAndZone(final ZonedDateTime zonedDateTime)
   {
      final String dateTimeStr
         = DateTimeFormatter.ofPattern("K:mm B z").format(zonedDateTime);
      out.println("Hour/Minute/Day Period/Zone: \"" + dateTimeStr + "\"");
   }

   /**
    * Prints Day Period phraseology for each of 24 hours of day with
    * arbitrary minutes, seconds, and nanoseconds to standard output.
    */
   public void printDayPeriodsByHour()
   {
      out.println("===== Hours With Non-Zero Minutes/Seconds/Nanoseconds =====");
      final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("hh B");
      for (int hour = 0; hour < 24; hour++)
      {
         final OffsetDateTime dateTime
            = Instant.now().atOffset(ZoneOffset.UTC).withHour(hour);
         out.println("Hour " + hour + ": \"" + dateTimeFormat.format(dateTime) + "\"");
      }
   }

   /**
    * Prints Day Period phraseology for each of 24 hours of day with
    * zero minutes, zero seconds, and zero nanoseconds to standard output.
    */
   public void printDayPeriodsByWholeHours()
   {
      out.println("===== Exact Hours =====");
      final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("hh B");
      for (int hour = 0; hour < 24; hour++)
      {
         final OffsetDateTime dateTime = OffsetDateTime.of(
            2020, 11, 23, hour, 0, 0, 0, ZoneOffset.UTC);
         out.println("Hour " + hour + ": \"" + dateTimeFormat.format(dateTime) + "\"");
      }
   }

   /**
    * Demonstrates day period formatting.
    *
    * @param arguments Command line-arguments; first argument is language
    *    for the Locale and second argument is country for the Locale
    *    ("en US" or "es DO" for example). If these are not provided or if
    *    the provided parameters are unexpected, "en US" will be used.
    */
   public static void main(final String[] arguments)
   {
      Locale localeToSet = null;
      if (arguments.length > 1)
      {
         localeToSet = new Locale(arguments[0], arguments[1]);
      }

      if (localeToSet == null)
      {
         out.println(
            "WARNING: Unable to determine Locale language/country based on provided command-line arguments "
               + Arrays.toString(arguments) + "; will use " + DEFAULT_LOCAL + " by default.");
         localeToSet = DEFAULT_LOCAL;
      }

      out.println("Setting Locale to " + localeToSet);
      Locale.setDefault(localeToSet);

      final DayPeriodDemo instance = new DayPeriodDemo();
      instance.printCurrentDayPeriod();

      final ZonedDateTime now = Instant.now().atZone(ZoneId.systemDefault());
      instance.printHourDayPeriodAndZone(now);
      instance.printHourDayMinutePeriodAndZone(now);
      instance.printDayPeriodsByHour();
      instance.printDayPeriodsByWholeHours();
   }
}
