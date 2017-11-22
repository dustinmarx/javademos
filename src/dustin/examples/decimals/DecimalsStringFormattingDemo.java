package dustin.examples.decimals;

import java.math.BigDecimal;
import java.text.NumberFormat;

import static java.lang.System.out;

/**
 * Demonstrates Java String representations of decimal numbers
 * (Floats, Doubles, and BigDecimals).
 */
public class DecimalsStringFormattingDemo
{
   /**
    * Supports rendering of Java numeric types float, double,
    * and BigDecimal in "default" format and in format that
    * avoids use of scientific notation.
    */
   public enum Format
   {
      /** No specific formatting; apply Java default String formatting. */
      DEFAULT
      {
         @Override
         public String fromFloat(final float floatValue)
         {
            return String.valueOf(floatValue);
         }
         @Override
         public String fromDouble(final double doubleValue)
         {
            return String.valueOf(doubleValue);
         }
         @Override
         public String fromBigDecimal(final BigDecimal bigDecimalValue)
         {
            return bigDecimalValue.toString();
         }
      },
      /** Preclude representation of Java decimal numbers in scientific notation. */
      NO_EXPONENT
      {
         @Override
         public String fromFloat(final float floatValue)
         {
            return getFormatter().format(floatValue);
         }
         @Override
         public String fromDouble(final double doubleValue)
         {
            return getFormatter().format(doubleValue);
         }
         @Override
         public String fromBigDecimal(final BigDecimal bigDecimalValue)
         {
            return bigDecimalValue.toPlainString();
         }
      };

      /**
       * Present provided float as a String formatted per my format.
       *
       * @param floatValue Float-typed number to be rendered.
       */
      public abstract String fromFloat(final float floatValue);
      /**
       * Present provided double as a String formatted per my format.
       *
       * @param doubleValue Double-typed number to be rendered.
       */
      public abstract String fromDouble(final double doubleValue);
      /**
       * Present provided BigDecimal as a String formatted per my format.
       *
       * @param bigDecimalValue BigDecimal object to be rendered.
       */
      public abstract String fromBigDecimal(final BigDecimal bigDecimalValue);

      /** 
       * Provide an instance of {@code NumberFormat} configured to 
       * display the maximum fractional digits and to not group. 
       * 
       * @return Instance of {@code NumberFormat} to be used to provide 
       *    a representation of the decimal number without scientific notation. 
       */ 
      private static NumberFormat getFormatter()
      {
         // Used to prevent numbers from being represented in scientific notation.
         final NumberFormat numberFormat = NumberFormat.getInstance();
         numberFormat.setMaximumFractionDigits(Integer.MAX_VALUE);
         numberFormat.setGroupingUsed(false);
         return numberFormat;
      }
   }

   /**
    * Writes floats in the provided format and in the
    * provided range to standard output.
    *
    * @param start Float to start writing.
    * @param threshold Float past which to not write anymore.
    * @param delta Delta for each increment of floats to be written.
    * @param label Label for header.
    * @param format Format for print out.
    */
   private static void writeFloatsToOutput(
      final float start,
      final float threshold,
      final float delta,
      final String label,
      final Format format)
   {
      out.println(generateHeader(label));
      float floatValue = start;
      do
      {
         out.println("= " + format.fromFloat(floatValue));
         floatValue += delta;
      }
      while (floatValue < threshold);
   }

   /**
    * Writes doubles in the provided format and in the
    * provided range to standard output.
    *
    * @param start Double to start writing.
    * @param threshold Double past which to not write anymore.
    * @param delta Delta for each increment of doubles to be written.
    * @param label Label for header.
    * @param format Format for print out.
    */
   private static void writeDoublesToOutput(
      final double start,
      final double threshold,
      final double delta,
      final String label,
      final Format format)
   {
      out.println(generateHeader(label));
      double doubleValue = start;
      do
      {
         out.println("= " + format.fromDouble(doubleValue));
         doubleValue += delta;
      }
      while (doubleValue < threshold);
   }

   /**
    * Writes BigDecimals in the provided format and in the
    * provided range to standard output.
    *
    * @param start BigDecimal to start writing.
    * @param threshold BigDecimal past which to not write anymore.
    * @param delta Delta for each increment of BigDecimals to be written.
    * @param label Label for header.
    * @param format Format for print out.
    */
   private static void writeBigDecimalsToOutput(
      final BigDecimal start,
      final BigDecimal threshold,
      final BigDecimal delta,
      final String label,
      final Format format)
   {
      out.println(generateHeader(label));
      BigDecimal decimal = start;
      do
      {
         out.println("= " + format.fromBigDecimal(decimal));
         decimal = decimal.add(delta);
      }
      while (decimal.compareTo(threshold) < 0);
   }

   /**
    * Generate a header string.
    *
    * @param title Title of header.
    * @return String with title in header.
    */
   private static String generateHeader(final String title)
   {
      final String headerLine = generateHeaderLine(title);
      final StringBuilder header = new StringBuilder();
      header.append(headerLine).append("\n");
      header.append("= ").append(title).append(" =").append("\n");
      header.append(headerLine);
      return header.toString();
   }

   /**
    * Generate a line for header.
    *
    * @param text Text for which header line will be used.
    * @return Generated header line.
    */
   private static String generateHeaderLine(final String text)
   {
      final int labelSize = text.length();
      final StringBuilder line = new StringBuilder();
      for (int index = 0; index < labelSize + 4; index++)
      {
         line.append("=");
      }
      return line.toString();
   }

   /**
    * Using provided instance of {@link Format}, write out ranges of
    * floats, doubles, and BigDecimals selected specifically to
    * demonstrate Java default rendering of these types in
    * scientific notation and how to render them without scientific
    * notation.
    *
    * @param format Format to be applied to Java decimal numeric types.
    */
   private static void writeFormattedValues(final Format format)
   {
      writeFloatsToOutput(
         0.00085f, 0.002f, 0.0001f, "Small Floats (" + format + ")", format);
      writeFloatsToOutput(
         9_999_995f, 10_000_005f, 1f, "Large Floats (" + format + ")", format);

      writeDoublesToOutput(
         0.00085d, 0.002d, 0.0001d, "Small Doubles (" + format + ")", format);
      writeDoublesToOutput(
         9_999_995d, 10_000_005d, 1d, "Large Doubles (" + format + ")", format);

      writeBigDecimalsToOutput(
         new BigDecimal("0.00000085"),
         new BigDecimal("0.000002"),
         new BigDecimal("0.0000001"),
         "Small BigDecimals (" + format + ")",
         format);
      writeBigDecimalsToOutput(
         new BigDecimal("99999950000000000000000000000000000000000000000000"),
         new BigDecimal("100000050000000000000000000000000000000000000000000"),
         new BigDecimal("10000000000000000000000000000000000000000000"),
         "Large BigDecimals (" + format + ")",
         format);
   }

   /**
    * Main executable that demonstrates writing Java decimal numeric
    * types with their default rendering that includes scientific
    * notation in select cases and writing Java decimal numeric types
    * with customized rendering that precludes use of scientific
    * notation in the presentation of the same numbers.
    *
    * @param arguments Command-line arguments (none expected).
    */
   public static void main(final String[] arguments)
   {
      writeFormattedValues(Format.DEFAULT);
      writeFormattedValues(Format.NO_EXPONENT);
   }
}
