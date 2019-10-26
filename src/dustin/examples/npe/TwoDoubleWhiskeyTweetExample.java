package dustin.examples.npe;

/**
 * Simple demonstration to answer Tweet-ed question
 * "How it will work if bytecode doesn't contain variable names?"
 * (https://twitter.com/2doublewhiskey/status/1180365953240055809).
 */
public class TwoDoubleWhiskeyTweetExample
{
   public static void main(final String[] arguments)
   {
      final Person person = null;
      person.getName(); //NPE
   }

   public static class Person
   {
      private String name;

      public Person(final String newName)
      {
         name = newName;
      }

      public String getName()
      {
         return name;
      }
   }
}
