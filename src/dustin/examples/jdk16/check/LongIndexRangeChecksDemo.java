package dustin.examples.jdk16.check;

import java.util.Objects;
import java.util.function.Supplier;

import static java.lang.System.out;

/**
 * Demonstrates three new methods added to {@link Objects} class
 * with JDK 16 Early Access Build 25 for range/index checks on
 * <code>long</code> values. These methods correspond to these
 * integer versions added by JDK 9:
 * <ul>
 *    <li>{@link Objects#checkIndex(int, int)}</li>
 *    <li>{@link Objects#checkFromToIndex(int, int, int)}</li>
 *    <li>{@link Objects#checkFromIndexSize(int, int, int)}</li>
 * </ul>
 *
 * https://bugs.openjdk.java.net/browse/JDK-8255150
 * https://bugs.openjdk.java.net/browse/JDK-8135248
 */
public class LongIndexRangeChecksDemo
{
   /**
    * Demonstrates {@link Objects#checkIndex(long, long)}'s throwing
    * of {@link IndexOutOfBoundsException} when checked index is
    * outside of allowed range.
    */
   public void demoCheckIndexException()
   {
      final long desiredIndex = 7;
      final long allowedLength = 5;
      executeDemonstration( "checkIndex Exception",
         () -> Objects.checkIndex(desiredIndex, allowedLength));
   }

   /**
    * Demonstrates {@link Objects#checkFromToIndex(long, long, long)}'s
    * throwing {@link IndexOutOfBoundsException} when checked "from" and
    * "to" indexes are not supported within the supplied capacity.
    */
   public void demoCheckFromToIndexException()
   {
      final long fromIndex = 2;
      final long toIndex = 6;
      final long allowedLength = 3;
      executeDemonstration( "checkFromToIndex Exception",
         () -> Objects.checkFromToIndex(fromIndex, toIndex, allowedLength));
   }

   /**
    * Demonstrates {@link Objects#checkFromIndexSize(long, long, long)}'s
    * throwing {@link IndexOutOfBoundsException} when checked "from" and
    * checked "size" add to more than the allowed length allows.
    */
   public void demoCheckFromIndexSizeException()
   {
      final long fromIndex = 2;
      final long size = 6;
      final long allowedLength = 3;
      executeDemonstration( "checkFromIndexSize Exception",
         () -> Objects.checkFromIndexSize(fromIndex, size, allowedLength));
   }

   /**
    * Demonstrates {@link Objects#checkFromIndexSize(long, long, long)}'s
    * throwing {@link IndexOutOfBoundsException} when checked "from" and
    * checked "size" lead to overflow error.
    */
   public void demoCheckFromIndexSizeExceptionOnOverflow()
   {
      final long fromIndex = 2;
      final long size = Long.MAX_VALUE;
      final long allowedLength = 3;
      executeDemonstration( "checkFromIndexSize (Overflow) Exception",
         () -> Objects.checkFromIndexSize(fromIndex, size, allowedLength));
   }

   /**
    * Executes the supplied method after writing the supplied 'demoName'
    * to standard output. Catches and logs to standard output any
    * {@link IndexOutOfBoundsException}s thrown by executed method.
    *
    * @param demoName Name of demonstration to be written to standard output.
    * @param demoToExecute Supplier that will be executed.
    */
   private void executeDemonstration(final String demoName, final Supplier<?> demoToExecute)
   {
      final String headerSeparator = "=".repeat(demoName.length() + 6);
      out.println("\n" + headerSeparator);
      out.println("== " + demoName + " ==");
      out.println(headerSeparator);
      try
      {
         demoToExecute.get();
      }
      catch (IndexOutOfBoundsException ioobEx)
      {
         ioobEx.printStackTrace(out);
      }
   }

   /**
    * Main executable method for demonstrating new {@code long}
    * methods {@link Objects#checkIndex(long, long)},
    * {@link Objects#checkFromToIndex(long, long, long)}, and
    * {@link Objects#checkFromIndexSize(long, long, long)}.
    *
    * @param arguments Command-line arguments; none expected.
    */
   public static void main(final String[] arguments)
   {
      final LongIndexRangeChecksDemo instance = new LongIndexRangeChecksDemo();
      instance.demoCheckIndexException();
      instance.demoCheckFromToIndexException();
      instance.demoCheckFromIndexSizeException();
      instance.demoCheckFromIndexSizeExceptionOnOverflow();
   }
}
