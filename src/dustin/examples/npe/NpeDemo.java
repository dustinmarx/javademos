package dustin.examples.npe;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.lang.System.out;

/**
 * Demonstrate {@code NullPointerException} nuances.
 */
public class NpeDemo
{
   /** Field that is intentionally {@code null} for demonstration purposes. */
   private final String nullInstanceField = null;

   /**
    * Writes the {@code toString()} representation of the provided
    * instance to standard output.
    *
    * @param objectToPrint Object whose {@code toString()} method
    *    will be invoked to write the resultant string to
    *    standard output.
    * @throws NullPointerException Thrown if the provided
    *    object is {@code null}.
    */
   public void printObject(final Object objectToPrint)
   {
      // Normally, it'd be safer to use String.valueOf(objectToPrint),
      // but we intentionally want the potential here for a
      // NullPointerException to be thrown.
      out.println(objectToPrint.toString());
   }

   /**
    * Demonstrates first example from JDK-8218628:
    * <pre>
    *    boolean[] za1 = null;
    *    za1[0]
    *    "java.lang.NullPointerException: while trying to load from a null byte (or boolean) array loaded from local variable 'za1'
    * </pre>
    */
   private void demonstrateFirstExampleIndexAccessOnNullBooleanArray()
   {
      try
      {
         final boolean[] za1 = null;
         final boolean firstBoolean = za1[0];
      }
      catch (NullPointerException npe)
      {
         out.println(generateHeader("#1: Element [0] on null boolean array"));
         out.println(extractNpeStackTrace(npe));
      }
   }

   /**
    * Demonstrates second example from JDK-8218628:
    * <pre>
    *    boolean[] za1 = null;
    *    za1.length
    *    "java.lang.NullPointerException: while trying to get the length of a null array loaded from local variable 'za1'"
    * </pre>
    */
   private void demonstrateSecondExampleLengthOnNullBooleanArray()
   {
      try
      {
         boolean[] za1 = null;
         final int size = za1.length;
      }
      catch (NullPointerException npe)
      {
         out.println(generateHeader("#2: .length on null boolean[]"));
         out.println(extractNpeStackTrace(npe));
      }
   }

   /**
    * Demonstrates third example from JDK-8218628:
    * <pre>
    *    fa1[0] = 0
    *    "java.lang.NullPointerException: while trying to store to a null float array loaded from local variable 'fa1'"
    * </pre>
    */
   private void demonstrateThirdExampleAssigningValueToElementOfNullFloatArray()
   {
      try
      {
         float[] fa1 = null;
         fa1[0] = 0;
      }
      catch (NullPointerException npe)
      {
         out.println(generateHeader("#3: Assigning float to null float[]"));
         out.println(extractNpeStackTrace(npe));
      }
   }

   /**
    * Demonstrates fourth example from JDK-8218628:
    * <pre>
    *    nullInstanceField.nullInstanceField
    *    "java.lang.NullPointerException: while trying to read the field 'nullInstanceField' of a null object loaded from field NullPointerExceptionTest.nullInstanceField of an object loaded from local variable 'this'"
    * </pre>
    */
   private void demonstrateFourthExampleAccessInstanceFieldOfNullObject()
   {
      try
      {
         final NpeDemo otherNullInstance = null;
         final String string = otherNullInstance.nullInstanceField;
      }
      catch (NullPointerException npe)
      {
         out.println(generateHeader("#4: Accessing field on null object"));
         out.println(extractNpeStackTrace(npe));
      }
   }

   /**
    * Demonstrates fifth example from JDK-8218628:
    * <pre>
    *    throw null;
    *    "java.lang.NullPointerException: while trying to throw a null exception object loaded from a constant"
    * </pre>
    */
   private void demonstrateFifthExampleThrowingConstantNull()
   {
      try
      {
         throw null;
      }
      catch (NullPointerException npe)
      {
         out.println(generateHeader("#5: throw null;"));
         out.println(extractNpeStackTrace(npe));
      }
   }

   /**
    * Demonstrates sixth example from JDK-8218628:
    * <pre>
    *    nullInstanceField.testCreationViaNew()
    *    "java.lang.NullPointerException: while trying to invoke the method NullPointerExceptionTest.testCreationViaNew(()V) of a null object loaded from field NullPointerExceptionTest.nullInstanceField of an object loaded from local variable 'this'"
    * </pre>
    */
   private void demonstrateSixthExampleMethodInvocationOnNullInstanceField()
   {
      try
      {
         final boolean isStringEmpty = nullInstanceField.isEmpty();
      }
      catch (NullPointerException npe)
      {
         out.println(generateHeader("#6: Method invocation on null instance field"));
         out.println(extractNpeStackTrace(npe));
      }
   }

   /**
    * Demonstrates seventh example from JDK-8218628:
    * <pre>
    *    synchronized (nullInstanceField)
    *    "java.lang.NullPointerException: while trying to enter a null monitor loaded from field NullPointerExceptionTest.nullInstanceField of an object loaded from local variable 'this'"
    * </pre>
    */
   private void demonstrateSeventhExampleSynchronizedNullInstanceField()
   {
      try
      {
         synchronized (nullInstanceField)
         {
         }
      }
      catch (NullPointerException npe)
      {
         out.println(generateHeader("#7: synchronized() on null instance field"));
         out.println(extractNpeStackTrace(npe));
      }
   }
   
   /**
    * Demonstrates {@code NullPointerException} presentation for
    * cases spelled out in JDK-8218628
    * (https://bugs.openjdk.java.net/browse/JDK-8218628).
    */
   public void demonstrateJdk8218628Examples()
   {
      demonstrateFirstExampleIndexAccessOnNullBooleanArray();
      demonstrateSecondExampleLengthOnNullBooleanArray();
      demonstrateThirdExampleAssigningValueToElementOfNullFloatArray();
      demonstrateFourthExampleAccessInstanceFieldOfNullObject();
      demonstrateFifthExampleThrowingConstantNull();
      demonstrateSixthExampleMethodInvocationOnNullInstanceField();
      demonstrateSeventhExampleSynchronizedNullInstanceField();
   }
   
   /**
    * Extracts the stack trace of the provided {@code NullPointerException}
    * as a {@code String}.
    *
    * @param npe {@code NullPointerException} for which string
    *    representation of its stack trace is desired; should not
    *    be {@code null}.
    * @return {@code String} representation of the stack trace
    *    associated with the provided {@code NullPointerException};
    *    will be "null" if {@code null} is provided.
    */
   private String extractNpeStackTrace(final NullPointerException npe)
   {
      if (npe == null)
      {
         return "null";
      }

      final StringWriter sw = new StringWriter();
      final PrintWriter pw = new PrintWriter(sw);
      npe.printStackTrace(pw);
      return sw.toString();
   }

   /**
    * Generates a {@code String} representing a header section
    * featuring the provided title.
    *
    * @param title Title to be featured in the generated header.
    * @return Header that includes provided title.
    */
   private String generateHeader(final String title)
   {
      final int headerSize = title.length() + 4;
      final String headerBar = "=".repeat(headerSize) + "\n";
      return headerBar + "| " + title + " |\n" + headerBar;
   }

   public static void main(final String[] arguments)
   {
      final NpeDemo instance = new NpeDemo();
      instance.demonstrateJdk8218628Examples();
//      instance.printObject("Inspired");
//      instance.printObject("Actual");
//      instance.printObject("Events");
//      instance.printObject(null);
//      instance.printObject("Dustin");
   }
}
