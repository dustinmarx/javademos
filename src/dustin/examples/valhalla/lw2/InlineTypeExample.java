package dustin.examples.valhalla.lw2;

/**
 * Example of a Project Valhalla LW2 prototype "inline type"
 * adapted from the OpenJDK LW2 Wiki page
 * (https://wiki.openjdk.java.net/display/valhalla/LW2).
 *
 * The "extends" clause is commented out because an inline type
 * cannot extend anything other that {@link Object}. When that
 * {@code extends} clause is uncommented, the compiler error states:
 * <pre>
 * C:\java\examples\valhalla\src\dustin\examples\valhalla\lw2\InlineTypeExample.java:8: error: Inline type may not extend another inline type or class
 *  public inline class InlineTypeExample
 *                ^
 * </pre>
 */
public inline class InlineTypeExample
//   extends Parent
   implements Comparable<InlineTypeExample?>
{
   int someIntegerValue;

   public InlineTypeExample(int newIntegerValue)
   {
      this.someIntegerValue = newIntegerValue;
        /*
          implicitly generates "defaultvalue" followed by "withfield" bytecodes

          cannot pass "this" from here
         */
   }

   // Because existing API can accept "null", we use the indirect projection
   public int compareTo(InlineTypeExample? other)
   {
      if (other == null)
      {
         return -1;
      }
      return someIntegerValue - other.someIntegerValue;
   }

//   /**
//    * This method is commented out because it won't compile.
//    * The compiler error seen when this method is not commented out is:
//    * <pre>
//    * C:\java\examples\valhalla\src\dustin\examples\valhalla\lw2\InlineTypeExample.java:45: error: cannot assign a value to final variable someIntegerValue
//    *        someIntegerValue = newIntegerValue;
//    *        ^
//    * </pre>
//    */
//   public void setIntegerValue(final int newIntegerValue)
//   {
//      someIntegerValue = newIntegerValue;
//   }
}
