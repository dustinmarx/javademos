/*
 * Code example featured on "Inspired by Actual Events" blog
 * (https://marxsoftware.blogspot.com/).
 */
package dustin.examples.jdk15;

import static java.lang.System.out;

/**
 * Demonstrates "absExact" methods added to {@link Math}
 * and {@link StrictMath} with <b>JDK 15 Early Access Build b18</b>
 * (JDK-8241374: https://bugs.openjdk.java.net/browse/JDK-8241374).
 */
public class AbsoluteExactness
{
   public void demonstrateMathAbsInteger(final int integer)
   {
      out.println("Math.abs(" + integer + "): " + Math.abs(integer));
   }

   public void demonstrateMathAbsLong(final long longNumber)
   {
      out.println("Math.abs(" + longNumber + "L): " + Math.abs(longNumber));
   }

   public void demonstrateStrictMathAbsInteger(final int integer)
   {
      out.println("StrictMath.abs(" + integer + "): " + StrictMath.abs(integer));
   }

   public void demonstrateStrictMathAbsLong(final long longNumber)
   {
      out.println("StrictMath.abs(" + longNumber + "L): " + StrictMath.abs(longNumber));
   }

   /**
    * Main executable function that demonstrates the new "absExact"
    * methods added to {@link Math} and {@link StrictMath} with JDK 15.
    *
    * @param arguments Command-line arguments; none expected.
    */
   public static void main(final String[] arguments)
   {
      final AbsoluteExactness instance = new AbsoluteExactness();

      // Demonstrate pre-JDK 15 Math/StrictMath "abs" functions on minimum values.
      instance.demonstrateMathAbsInteger(Integer.MIN_VALUE+1);
      instance.demonstrateMathAbsInteger(Integer.MIN_VALUE);
      instance.demonstrateMathAbsLong(Long.MIN_VALUE+1);
      instance.demonstrateMathAbsLong(Long.MIN_VALUE);
      instance.demonstrateStrictMathAbsInteger(Integer.MIN_VALUE+1);
      instance.demonstrateStrictMathAbsInteger(Integer.MIN_VALUE);
      instance.demonstrateStrictMathAbsLong(Long.MIN_VALUE+1);
      instance.demonstrateStrictMathAbsLong(Long.MIN_VALUE);
   }
}
