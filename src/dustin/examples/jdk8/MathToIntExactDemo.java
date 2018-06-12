package dustin.examples.jdk8;

import static java.lang.System.out;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * Demonstrates use of JDK 8-introduced static method
 * {@code Math.toIntExact(Long)} and contrasts it with
 * use of {@code Long.intValue()}.
 */
public class MathToIntExactDemo
{
   /**
    * Provides the {@code int} representation of the provided
    * {@code Long} based on an invocation of the provided
    * {@code Long} object's {@code intValue()} method.
    *
    * @param longRepresentation {@code Long} for which {@code int}
    *    value extracted with {@code intValue()} will be returned.
    * @return {@code int} value corresponding to the provided
    *    {@code Long} as provided by invoking the method
    *    {@code intValue()} on that provided {@code Long}.
    * @throws NullPointerException Thrown if the provided long
    *    representation is {@code null}.
    */
   public static void writeLongIntValue(final Long longRepresentation)
   {
      out.print(longRepresentation + " =>       Long.intValue() = ");
      try
      {
         out.println(longRepresentation.intValue());
      }
      catch (Exception exception)
      {
         out.println("ERROR - " + exception);
      }
   }

   /**
    * Provides the {@code int} representation of the provided
    * {@code Long} based on an invocation of {@code Math.toIntExact(Long)}
    * on the provided {@code Long}.
    *
    * @param longRepresentation {@code Long} for which {@code int}
    *    value extracted with {@code Math.toIntExact(Long)} will be
    *    returned.
    * @return {@code int} value corresponding to the provided
    *    {@code Long} as provided by invoking the method
    *    {@code Math.toIntExact)Long} on that provided {@code Long}.
    * @throws NullPointerException Thrown if the provided long
    *    representation is {@code null}.
    * @throws ArithmeticException Thrown if the provided {@code Long}
    *    cannot be represented as an integer without overflow.
    */
   public static void writeIntExact(final Long longRepresentation)
   {
      out.print(longRepresentation + " => Math.toIntExact(Long) = ");
      try
      {
         out.println(Math.toIntExact(longRepresentation));
      }
      catch (Exception exception)
      {
         out.println("ERROR: " + exception);
      }
   }

   /**
    * Generate {@code Long}s from range of integers that start
    * before {@code Integer.MAX_VALUE} and end after that
    * maximum integer value.
    *
    * @return {@code Long}s generated over range includes
    *    {@code Integer.MAX_VALUE}.
    */
   public static List<Long> generateLongInts()
   {
      final Long maximumIntegerAsLong = Long.valueOf(Integer.MAX_VALUE);
      final Long startingLong = maximumIntegerAsLong - 5;
      final Long endingLong = maximumIntegerAsLong + 5;
      return LongStream.range(startingLong, endingLong).boxed().collect(Collectors.toList());
   }

   public static void main (final String[] arguments)
   {
      out.println("Integer.MAX_VALUE = " + Integer.MAX_VALUE);
      final List<Long> longs = generateLongInts();
      for (final Long candidateLong : longs)
      {
         writeLongIntValue(candidateLong);
         writeIntExact(candidateLong);
      }
   }
}
