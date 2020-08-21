package dustin.examples.jdk16;

import static java.lang.System.out;

/**
 * This class intentionally does NOT specify an explicit constructor
 * so that a "default constructor" will be generated and trigger the
 * new JDK 16 warning.
 */
public class DefaultConstructor
{
   private String name;

   public String getName()
   {
      return name;
   }

   public void setName(final String newName)
   {
      name = newName;
   }

   public static void main(final String[] arguments)
   {
      final DefaultConstructor instance = new DefaultConstructor();
      instance.setName(arguments.length > 0 ? arguments[0] : "");
      out.println("Hello " + instance.getName() + "!");
   }
}
