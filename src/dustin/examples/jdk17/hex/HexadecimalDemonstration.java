package dustin.examples.jdk17.hex;

import static java.lang.System.out;

import java.util.HexFormat;

/**
 * Demonstrates hexadecimal parsing and formatting class
 * {@link java.util.HexFormat} introduced with JDK 17.
 */
public class HexadecimalDemonstration
{
   /** Instance of {@link HexFormat} used in this demonstration. */
   private static final HexFormat HEX_FORMAT_UPPER_CASE = HexFormat.of().withUpperCase();

   /**
    * Demonstrates use of {@link HexFormat#toHexDigits(int)}.
    */
   public void demoIntegerToHexadecimal()
   {
      for (int integerValue = 0; integerValue < 17; integerValue++)
      {
         out.println("Hexadecimal representation of integer " + integerValue + ": '"
            + HEX_FORMAT_UPPER_CASE.toHexDigits(integerValue) + "'.");
      }
   }

   /**
    * Demonstrates use of {@link HexFormat#isHexDigit(int)}.
    */
   public void demoIsHex()
   {
      for (char characterValue = 'a'; characterValue < 'i'; characterValue++)
      {
         out.println("Is character '" + characterValue + "' a hexadecimal value? "
            + HexFormat.isHexDigit(characterValue));
      }
      for (char characterValue = 'A'; characterValue < 'I'; characterValue++)
      {
         out.println("Is character '" + characterValue + "' a hexadecimal value? "
            + HexFormat.isHexDigit(characterValue));
      }
   }

   /**
    * Demonstrates string representation of instance of
    * {@link HexFormat}.
    *
    * The {@link HexFormat#toString()} method provides a string
    * that shows the instance's parameters (not class name):
    * "uppercase", "delimiter", "prefix", and "suffix"
    */
   public void demoToString()
   {
      out.println("HexFormat.toString(): " + HEX_FORMAT_UPPER_CASE);
   }

   /**
    * Main demonstration executable.
    * @param arguments Command-line arguments; none expected.
    */
   public static void main(final String[] arguments)
   {
      final HexadecimalDemonstration demoInstance = new HexadecimalDemonstration();
      demoInstance.demoIntegerToHexadecimal();
      demoInstance.demoIsHex();
      demoInstance.demoToString();
   }
}
