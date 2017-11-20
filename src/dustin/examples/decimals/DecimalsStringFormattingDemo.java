package dustin.examples.decimals;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import static java.lang.System.out;

/**
 * Demonstrates Java String representations of decimal numbers
 * (Floats, Doubles, and BigDecimals).
 */
public class DecimalsStringFormattingDemo
{
   private final static DecimalFormat floatFormat = new DecimalFormat("%f");
   private final static DecimalFormat doubleFormat = new DecimalFormat("%d");

   /**
    * Supports rendering of Java numeric types float, double,
    * and BigDecimal in "default" format and in format that
    * avoids use of scientific notation.
    */
   public enum Format
   {
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
      NO_EXPONENT
      {
         @Override
         public String fromFloat(final float floatValue)
         {
            return numberFormat.format(floatValue);
         }
         @Override
         public String fromDouble(final double doubleValue)
         {
            return numberFormat.format(doubleValue);
         }
         @Override
         public String fromBigDecimal(final BigDecimal bigDecimalValue)
         {
            return bigDecimalValue.toPlainString();
         }
      };

      private static final NumberFormat numberFormat = NumberFormat.getInstance();

      static
      {
         numberFormat.setMaximumFractionDigits(Integer.MAX_VALUE);
         numberFormat.setGroupingUsed(false);
      }

      public abstract String fromFloat(final float floatValue);
      public abstract String fromDouble(final double doubleValue);
      public abstract String fromBigDecimal(final BigDecimal bigDecimalValue);
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

   public static void main(final String[] arguments)
   {
      writeFormattedValues(Format.DEFAULT);
      writeFormattedValues(Format.NO_EXPONENT);
   }
}
