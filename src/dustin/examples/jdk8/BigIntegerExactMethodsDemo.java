package dustin.examples.jdk8;

import static java.lang.System.out;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Demonstrates BigInteger's methods for exact narrowing
 * conversions that were added in JDK 8.
 */
public class BigIntegerExactMethodsDemo
{
   /**
    * Writes a simple header with the provided title to
    * standard output.
    *
    * @param title String to be included in simple header that
    *    is written to standard output.
    */
   public static void printHeader(final String title)
   {
      out.println("===== " + title + " =====");
   }

   /**
    * Writes demonstration of {@code BigInteger.byteValueExact()}
    * on numbers before, including, and after Byte.MAX_VALUE
    * to standard output.
    */
   public static void demoBigIntegerToByte()
   {
      printHeader("Byte");
      final List<BigInteger> bytes
         = generateBigIntegersForTest(
            new BigInteger(String.valueOf(Byte.MAX_VALUE)));
      for (final BigInteger bite : bytes)
      {
         out.print(bite + " => ");
         try
         {
            out.println(bite.byteValueExact());
         }
         catch (ArithmeticException tooLargeException)
         {
            out.println(tooLargeException);
         }
      }
   }

   /**
    * Writes demonstration of {@code BigInteger.shortValueExact()}
    * on numbers before, including, and after Short.MAX_VALUE
    * to standard output.
    */
   public static void demoBigIntegerToShort()
   {
      printHeader("Short");
      final List<BigInteger> shorts
         = generateBigIntegersForTest(
         new BigInteger(String.valueOf(Short.MAX_VALUE)));
      for (final BigInteger shorty : shorts)
      {
         out.print(shorty + " => ");
         try
         {
            out.println(shorty.shortValueExact());
         }
         catch (ArithmeticException tooLargeException)
         {
            out.println(tooLargeException);
         }
      }
   }

   /**
    * Writes demonstration of {@code BigInteger.intValueExact()}
    * on numbers before, including, and after Integer.MAX_VALUE
    * to standard output.
    */
   public static void demoBigIntegerToInt()
   {
      printHeader("Int");
      final List<BigInteger> ints
         = generateBigIntegersForTest(
         new BigInteger(String.valueOf(Integer.MAX_VALUE)));
      for (final BigInteger integer : ints)
      {
         out.print(integer + " => ");
         try
         {
            out.println(integer.intValueExact());
         }
         catch (ArithmeticException tooLargeException)
         {
            out.println(tooLargeException);
         }
      }
   }

   /**
    * Writes demonstration of {@code BigInteger.longValueExact()}
    * on numbers before, including, and after Long.MAX_VALUE
    * to standard output.
    */
   public static void demoBigIntegerToLong()
   {
      printHeader("Long");
      final List<BigInteger> longs
         = generateBigIntegersForTest(
         new BigInteger(String.valueOf(Long.MAX_VALUE)));
      for (final BigInteger lengthy : longs)
      {
         out.print(lengthy + " => ");
         try
         {
            out.println(lengthy.longValueExact());
         }
         catch (ArithmeticException tooLargeException)
         {
            out.println(tooLargeException);
         }
      }
   }

   /**
    * Generates small range of consecutive instances of {@code BigInteger}
    * that surround the lesser integral numeric data type's maximum that
    * is provided to me as a parameter.
    *
    * @param lesserTypeMaximumValueAsBigInteger Maximum value of a lesser
    *    integral numeric type that will be in the middle of the range of
    *    consecutive instances of {@code BigInteger} that I return.
    * @return Consecutive instances of {@code BigInteger} that includes
    *    the maximum value of a lesser integral numeric type that was
    *    provided to me.
    */
   public static List<BigInteger> generateBigIntegersForTest(
      final BigInteger lesserTypeMaximumValueAsBigInteger)
   {
      final BigInteger minimum = lesserTypeMaximumValueAsBigInteger.subtract(BigInteger.TWO);
      return Stream.iterate(minimum, bi -> bi.add(BigInteger.ONE)).limit(5).collect(Collectors.toList());
   }

   /**
    * Executable function that demonstrates four "exact" methods
    * added to {@code BigInteger} with JDK 8.
    *
    * @param arguments Command-line arguments: none expected.
    */
   public static void main(final String[] arguments)
   {
      demoBigIntegerToByte();
      demoBigIntegerToShort();
      demoBigIntegerToInt();
      demoBigIntegerToLong();
   }
}
