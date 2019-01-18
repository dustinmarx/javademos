package dustin.examples.jdk12.format;

import static java.lang.System.out;

import java.text.NumberFormat;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Demonstrate Compact Number Format support added to
 * JDK 12 as of Early Access Build 24 (see also
 * JDK-8177552: Compact Number Formatting support).
 */
public class CompactNumberFormatDemo
{
   /**
    * Generates standardized map of labels to Compact Number Format
    * instances described by the labels. The instances of {@code NumberFormat}
    * are created with Locale and Style only.
    *
    * @return Mapping of label to an instance of a Compact Number Format
    *    consisting of a Locale and Style that is described by the label.
    */
   private static Map<String, NumberFormat> generateCompactNumberFormats()
   {
      var numberFormats = new LinkedHashMap<String, NumberFormat>();
      numberFormats.put("Default", NumberFormat.getCompactNumberInstance());
      numberFormats.put("US/Long", NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.LONG));
      numberFormats.put("UK/Short", NumberFormat.getCompactNumberInstance(Locale.UK, NumberFormat.Style.SHORT));
      numberFormats.put("UK/Long", NumberFormat.getCompactNumberInstance(Locale.UK, NumberFormat.Style.LONG));
      numberFormats.put("FR/Short", NumberFormat.getCompactNumberInstance(Locale.FRANCE, NumberFormat.Style.SHORT));
      numberFormats.put("FR/Long", NumberFormat.getCompactNumberInstance(Locale.FRANCE, NumberFormat.Style.LONG));
      numberFormats.put("DE/Short", NumberFormat.getCompactNumberInstance(Locale.GERMANY, NumberFormat.Style.SHORT));
      numberFormats.put("DE/Long", NumberFormat.getCompactNumberInstance(Locale.GERMANY, NumberFormat.Style.LONG));
      numberFormats.put("IT/Short", NumberFormat.getCompactNumberInstance(Locale.ITALY, NumberFormat.Style.SHORT));
      numberFormats.put("IT/Long", NumberFormat.getCompactNumberInstance(Locale.ITALY, NumberFormat.Style.LONG));
      return numberFormats;
   }

   /**
    * Generates standardized map of labels to Compact Number Format
    * instances described by the labels. The instances of {@code NumberFormat}
    * are created with Locale and Style only and with the provided number
    * of minimum fractional digits.
    *
    * @return Mapping of label to an instance of a Compact Number Format
    *    consisting of a Locale, Style, and specified minimum number of fractional
    *    digits that is described by the label.
    */
   private static Map<String, NumberFormat> generateCompactNumberFormats(
      final int minimumNumberFractionDigits
   )
   {
      var numberFormats = generateCompactNumberFormats();
      numberFormats.forEach((label, numberFormat) ->
         numberFormat.setMinimumFractionDigits(minimumNumberFractionDigits));
      return numberFormats;
   }

   /**
    * Demonstrates compact number formatting in a variety of locales
    * and number formats against the provided {@code long} value.
    * @param numberToFormat Value of type {@code long} that is to be
    *    formatted using compact number formatting and a variety of
    *    locales and number formats.
    */
   private static void demonstrateCompactNumberFormatting(final long numberToFormat)
   {
      final Map<String, NumberFormat> numberFormats = generateCompactNumberFormats();
      out.println("Demonstrating Compact Number Formatting on long '" + numberToFormat + "':");
      numberFormats.forEach((label, numberFormat) ->
         out.println("\t" +  label + ": " + numberFormat.format(numberToFormat))
      );
   }

   /**
    * Demonstrates compact number formatting in a variety of locales
    * and number formats against the provided {@code double} value.
    * @param numberToFormat Value of type {@code double} that is to be
    *    formatted using compact number formatting and a variety of
    *    locales and number formats.
    */
   private static void demonstrateCompactNumberFormatting(final double numberToFormat)
   {
      final Map<String, NumberFormat> numberFormats = generateCompactNumberFormats();
      out.println("Demonstrating Compact Number Formatting on double '" + numberToFormat + "':");
      numberFormats.forEach((label, numberFormat) ->
         out.println("\t" +  label + ": " + numberFormat.format(numberToFormat))
      );
   }

   /**
    * Demonstrates compact number formatting in a variety of locales
    * and number formats against the provided {@code long} value and
    * with a minimum fractional digits of 1 specified.
    * @param numberToFormat Value of type {@code long} that is to be
    *    formatted using compact number formatting and a variety of
    *    locales and number formats and with a single minimal fractional
    *    digit.
    */
   private static void demonstrateCompactNumberFormattingOneFractionalDigitMinimum(
      final long numberToFormat)
   {
      final Map<String, NumberFormat> numberFormats = generateCompactNumberFormats(1);
      out.println(
         "Demonstrating Compact Number Formatting on long '" + numberToFormat
            + "' with 1 minimum fraction digit:");
      numberFormats.forEach((label, numberFormat) ->
         out.println("\t" +  label + ": " + numberFormat.format(numberToFormat))
      );
   }

   /**
    * Invoke demonstrations of compact number formatting on {@code long}
    * values ranging from 15 to 15,000,000.
    */
   private static void demonstrateOnLongValues()
   {
      demonstrateCompactNumberFormatting(15);
      demonstrateCompactNumberFormatting(150);
      demonstrateCompactNumberFormatting(1_500);
      demonstrateCompactNumberFormatting(15_000);
      demonstrateCompactNumberFormatting(150_000);
      demonstrateCompactNumberFormatting(1_500_000);
      demonstrateCompactNumberFormatting(15_000_000);
   }

   /**
    * Invoke demonstrations of compact number formatting on {@code double}
    * values ranging from 15.0 to 15,000,000.0.
    */
   private static void demonstrateOnDoubleValues()
   {
      demonstrateCompactNumberFormatting(15.0);
      demonstrateCompactNumberFormatting(150.0);
      demonstrateCompactNumberFormatting(1_500.0);
      demonstrateCompactNumberFormatting(15_000.0);
      demonstrateCompactNumberFormatting(150_000.0);
      demonstrateCompactNumberFormatting(1_500_000.0);
      demonstrateCompactNumberFormatting(15_000_000.0);
   }

   /**
    * Invoke demonstrations of compact number formatting on {@code double}
    * values ranging from 15.0 to 15,000,000.0 and with a minimum fractional
    * digits of 1.
    */
   private static void demonstrateOnLongValuesWithSingleMinimumFractionDigit()
   {
      demonstrateCompactNumberFormattingOneFractionalDigitMinimum(15);
      demonstrateCompactNumberFormattingOneFractionalDigitMinimum(150);
      demonstrateCompactNumberFormattingOneFractionalDigitMinimum(1_500);
      demonstrateCompactNumberFormattingOneFractionalDigitMinimum(15_000);
      demonstrateCompactNumberFormattingOneFractionalDigitMinimum(150_000);
      demonstrateCompactNumberFormattingOneFractionalDigitMinimum(1_500_000);
      demonstrateCompactNumberFormattingOneFractionalDigitMinimum(15_000_000);
   }

   /**
    * Main demonstration executable.
    * @param arguments Command-line arguments: none expected.
    */
   public static void main(final String[] arguments)
   {
      demonstrateOnLongValues();
      demonstrateOnDoubleValues();
      demonstrateOnLongValuesWithSingleMinimumFractionDigit();
   }
}
