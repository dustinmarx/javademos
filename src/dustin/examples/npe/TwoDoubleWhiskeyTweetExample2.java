package dustin.examples.npe;

/**
 * Another (simpler) demonstration to answer Tweet-ed question
 * "How it will work if bytecode doesn't contain variable names?"
 * (https://twitter.com/2doublewhiskey/status/1180365953240055809).
 *
 * This example is closer to the original question than the
 * {@link TwoDoubleWhiskeyTweetExample} example.
 */
public class TwoDoubleWhiskeyTweetExample2
{
   public static void main(final String[] arguments)
   {
      final Object a = null;
      a.toString(); //NPE
   }
}
