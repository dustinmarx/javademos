package dustin.examples.valhalla.lw2;

import java.lang.reflect.Modifier;
import java.util.Objects;

import static java.lang.System.out;

/**
 * Demonstrates early characteristics of Project Valhalla's
 * LW2 prototype inline types via contract with JDK-provided
 * {@link Integer} and with a custom {@link Integer}-wrapping
 * class.
 */
public class Lw2Demonstration
{
   /**
    * An arbitrary integer value large enough to not be cached.
    *
    * See https://blogs.oracle.com/darcy/boxing-and-caches:-integervalueofint-and-friends
    * for additional background details.
    */
   private final static int A_BIG_INTEGER = 1700000;

   private final InlineTypeExample notSynchronizable = new InlineTypeExample(1);
   private final Integer synchronizable = 1;

   /**
    * Provides metadata extracted from the provided instance of
    * {@link Class} as a single {@link String}.
    *
    * @param classToInvokeInlineMethodsOn Class for which metadata
    *    is to be extracted and returned in {@link String} format;
    *    should NOT be {@code null}.
    * @return Single string representation of metadata extracted
    *    from the provided {@link Class} instance.
    * @throws NullPointerException Thrown if {@code null} is
    *    provided for my sole parameter.
    */
   public static String extractClassMetadata(final Class classToInvokeInlineMethodsOn)
   {
      Objects.requireNonNull("Provided Class must be non-null to extract its metadata.");

      final String className = classToInvokeInlineMethodsOn.getSimpleName();
      final String outputPrefix = "\n" + className + ".class.";
      return outputPrefix + "getName(): " + classToInvokeInlineMethodsOn.getName()
         + outputPrefix + "getSimpleName(): " + classToInvokeInlineMethodsOn.getSimpleName()
         + outputPrefix + "getCanonicalName(): " + classToInvokeInlineMethodsOn.getCanonicalName()
         + outputPrefix + "toGenericString(): " + classToInvokeInlineMethodsOn.toGenericString()
         + outputPrefix + "getTypeName(): " + classToInvokeInlineMethodsOn.getTypeName()
         + outputPrefix + "getComponentType(): " + classToInvokeInlineMethodsOn.getComponentType()
         + outputPrefix + "isInlineClass(): " + classToInvokeInlineMethodsOn.isInlineClass()
         + outputPrefix + "isIndirectType(): " + classToInvokeInlineMethodsOn.isIndirectType()
         + outputPrefix + "isNullableType(): " + classToInvokeInlineMethodsOn.isNullableType()
         + outputPrefix + "isPrimitive(): " + classToInvokeInlineMethodsOn.isPrimitive()
         + outputPrefix + " final?: " + isFinal(classToInvokeInlineMethodsOn);
   }

   /**
    * Provides common method output extracted from the provided
    * {@link Object} as a single {@link String}.
    *
    * @param objectToInvokeCommonMethodsOn Object for which common
    *    method results are to be extracted and returned in
    *    {@link String} format; should NOT be {@code null}.
    * @return Single string representation of metadata extracted
    *    from the provided {@link Object} instance.
    * @throws NullPointerException Thrown if {@code null} is
    *    provided for my sole parameter.
    */
   public static String extractCommonInstanceData(final Object objectToInvokeCommonMethodsOn)
   {
      Objects.requireNonNull("Provided object must be non-null to invoke common methods.");

      final String className = objectToInvokeCommonMethodsOn.getClass().getSimpleName();
      final String outputPrefix = className + ": ";
      return outputPrefix + "toString(): " + objectToInvokeCommonMethodsOn + "\n"
         + outputPrefix + "hashCode(): " + objectToInvokeCommonMethodsOn.hashCode();
   }

   /**
    * Indicates whether provided {@link Class} is
    * {@code final} or not.
    *
    * @param clazz {@link Class} for which it is desired to
    *    know whether it is {@code final} or not.
    * @return {@code true} if the provided {@link Class} is
    *    {@code final} or {@code false} if the provided
    *    {@link Class} is NOT {@code final}.
    */
   private static boolean isFinal(final Class clazz)
   {
      return Modifier.isFinal(clazz.getModifiers());
   }

   /**
    * Demonstration of characteristics of an inline type.
    */
   private static void demonstrateInlineType()
   {
      out.println(extractClassMetadata(InlineTypeExample.class));
      out.println(extractCommonInstanceData(new InlineTypeExample(1)));

      final InlineTypeExample inlineExampleBigNumero = new InlineTypeExample(A_BIG_INTEGER);
      final InlineTypeExample inlineExampleBigNumeroAgain = new InlineTypeExample(A_BIG_INTEGER);
      out.println("Inline Type Example ==: " + (inlineExampleBigNumero == inlineExampleBigNumeroAgain));
   }

   /**
    * Demonstration of characteristics of an Integer type.
    */
   private static void demonstrateIntegerType()
   {
      out.println(extractClassMetadata(Integer.class));
      out.println(extractCommonInstanceData(1));

      final Integer integerExampleBigNumero = A_BIG_INTEGER;
      final Integer integerExampleNumeroAgain = A_BIG_INTEGER;
      out.println("Integer Type Example ==: " + (integerExampleBigNumero == integerExampleNumeroAgain));
   }

   /**
    * Demonstration of characteristics of a custom Integer Wrapper.
    */
   private static void demonstrateCustomIntegerWrapper()
   {
      out.println(extractClassMetadata(IntegerWrapper.class));
      out.println(extractCommonInstanceData(new IntegerWrapper(1)));

      final IntegerWrapper integerWrapperBigNumero = new IntegerWrapper(A_BIG_INTEGER);
      final IntegerWrapper integerWrapperBigNumeroAgain = new IntegerWrapper(A_BIG_INTEGER);
      out.println("Integer Wrapper Example ==: " + (integerWrapperBigNumero == integerWrapperBigNumeroAgain));
   }

//   /**
//    * This method will not compile, so it's commented out.
//    * The following error message is seen when attempting to compile it:
//    * C:\java\examples\valhalla\src\dustin\examples\valhalla\lw2\Lw2Demonstration.java:133: error: incompatible types: <null> cannot be converted to InlineTypeExample
//    *        final InlineTypeExample nullInlineType = null;
//    */
//   private static void demonstrateSettingInlineTypeToNull()
//   {
//      final InlineTypeExample nullInlineType = null;
//   }

//   /**
//    * This method will not compile so it's comment out.
//    * The following error message is seen when attempting to compile it:
//    * C:\java\examples\valhalla\src\dustin\examples\valhalla\lw2\Lw2Demonstration.java:147: error: unexpected type
//    *        synchronized (notSynchronizable)
//    *        ^
//    *    required: reference
//    *    found:    InlineTypeExample
//    */
//   private void demonstrateSynchronizedOnInlineType()
//   {
//      synchronized (notSynchronizable)
//      {
//      }
//   }

   /**
    * Compares and contrasts a Valhalla LW2 "inline type" wrapping an
    * integer value to a direct instance of {@link Integer} and to
    * a custom wrapper class for an {@link Integer}.
    *
    * @param arguments Command-line arguments; none expected.
    */
   public static void main(final String[] arguments)
   {
      demonstrateInlineType();
      demonstrateIntegerType();
      demonstrateCustomIntegerWrapper();
   }
}
