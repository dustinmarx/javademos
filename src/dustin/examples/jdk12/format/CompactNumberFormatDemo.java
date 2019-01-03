package dustin.examples.jdk12.format;

import static java.lang.System.out;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Demonstrate Compact Number Format support added to
 * JDK 12 as of Early Access Build 24 (see also
 * JDK-8177552: Compact Number Formatting support).
 */
public class CompactNumberFormatDemo
{
   private static void demonstrateCompactNumberFormatting(final long numberToFormat)
   {
      final NumberFormat numberFormatDefault
         = NumberFormat.getCompactNumberInstance();
      final NumberFormat numberFormatUsLong
         = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.LONG);
      final NumberFormat numberFormatUkShort
         = NumberFormat.getCompactNumberInstance(Locale.UK, NumberFormat.Style.SHORT);
      final NumberFormat numberFormatUkLong
         = NumberFormat.getCompactNumberInstance(Locale.UK, NumberFormat.Style.LONG);
      final NumberFormat numberFormatFrShort
         = NumberFormat.getCompactNumberInstance(Locale.FRANCE, NumberFormat.Style.SHORT);
      final NumberFormat numberFormatFrLong
         = NumberFormat.getCompactNumberInstance(Locale.FRANCE, NumberFormat.Style.LONG);
      final NumberFormat numberFormatGrShort
         = NumberFormat.getCompactNumberInstance(Locale.GERMANY, NumberFormat.Style.SHORT);
      final NumberFormat numberFormatGrLong
         = NumberFormat.getCompactNumberInstance(Locale.GERMANY, NumberFormat.Style.LONG);
      final NumberFormat numberFormatItShort
         = NumberFormat.getCompactNumberInstance(Locale.ITALY, NumberFormat.Style.SHORT);
      final NumberFormat numberFormatItLong
         = NumberFormat.getCompactNumberInstance(Locale.ITALY, NumberFormat.Style.LONG);

      out.println("Demonstrating Compact Number Formatting on '" + numberToFormat + "':");
      out.println("\tDefault:  " + numberFormatDefault.format(numberToFormat));
      out.println("\tUS/Long:  " + numberFormatUsLong.format(numberToFormat));
      out.println("\tUK/Short: " + numberFormatUkShort.format(numberToFormat));
      out.println("\tUK/Long:  " + numberFormatUkLong.format(numberToFormat));
      out.println("\tFR/Short: " + numberFormatFrShort.format(numberToFormat));
      out.println("\tFR/Long:  " + numberFormatFrLong.format(numberToFormat));
      out.println("\tGR/Short: " + numberFormatGrShort.format(numberToFormat));
      out.println("\tGR/Long:  " + numberFormatGrLong.format(numberToFormat));
      out.println("\tIT/Short: " + numberFormatItShort.format(numberToFormat));
      out.println("\tIT/Long:  " + numberFormatItLong.format(numberToFormat));
   }

   /**
    * Main demonstration executable.
    * @param arguments Command-line arguments: none expected.
    */
   public static void main(final String[] arguments)
   {
      demonstrateCompactNumberFormatting(15);
      demonstrateCompactNumberFormatting(150);
      demonstrateCompactNumberFormatting(1500);
      demonstrateCompactNumberFormatting(15000);
      demonstrateCompactNumberFormatting(150000);
      demonstrateCompactNumberFormatting(1500000);
      demonstrateCompactNumberFormatting(15000000);
   }
}
