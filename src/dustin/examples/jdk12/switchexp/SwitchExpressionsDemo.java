package dustin.examples.jdk12.switchexp;

/**
 * Demonstrate JDK 12 "preview language feature"
 * switch expressions (JEP 325).
 */
public class SwitchExpressionsDemo
{
   /**
    * This example is adopted from the JEP 325 text
    * (http://openjdk.java.net/jeps/325).
    *
    * @param k Value to be switched upon.
    */
   static void howMany(final int k)
   {
      switch (k)
      {
         case 1 -> System.out.println("one");
         case 2 -> System.out.println("two");
         case 3 -> System.out.println("many");
      }
   }

   /**
    * Execute demonstration discussed in JEP 325 using custom
    * method {@link #howMany(int)}.
    */
   public static void demonstrateHowMany()
   {
      howMany(1);
      howMany(2);
      howMany(3);
   }

   /**
    * Main executable function to run demonstrations.
    * 
    * @param arguments Command-line arguments: none expected.
    */
   public static void main(final String[] arguments)
   {
      demonstrateHowMany();
   }
}
