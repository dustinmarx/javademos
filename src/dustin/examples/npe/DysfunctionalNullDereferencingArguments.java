package dustin.examples.npe;

/**
 * Dysfunctional class intended solely for demonstration
 * of difficulty of knowing where {@code NullPointerException}
 * is thrown when numerous potential {@code null} arguments
 * to a constructor (or method) might result in a
 * {@code NullPointerException} when dereferenced.
 *
 * This class is intentionally NOT declared {@code public} in
 * an attempt to keep its ugliness from getting any further
 * than this demonstration package {@code dustin.examples.npe}.
 */
final class DysfunctionalNullDereferencingArguments
{
   DysfunctionalNullDereferencingArguments()
   {
   }

   DysfunctionalNullDereferencingArguments(
      final boolean newBoolean,
      final byte newByte,
      final char newChar,
      final short newShort,
      final int newInteger,
      final long newLong,
      final float newFloat,
      final double newDouble)
   {
   }

   public void setValues(
      final boolean newBoolean,
      final byte newByte,
      final char newChar,
      final short newShort,
      final int newInteger,
      final long newLong,
      final float newFloat,
      final double newDouble)
   {
   }
}
