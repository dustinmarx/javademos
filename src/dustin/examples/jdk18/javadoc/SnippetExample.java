package dustin.examples.jdk18.javadoc;

/**
 * Demonstrates {@code @snippet} Javadoc tag targeted for JDK
 * as part of JEP 413 (AKA Bug JDK-8201533):
 * <ul>
 *     <li>https://openjdk.java.net/jeps/413</li>
 *     <li>https://bugs.openjdk.java.net/browse/JDK-8201533</li>
 * </ul>
 *
 * An instance of this class is obtained via my {@link #newInstance()} method
 * rather than via a constructor.
 *
 * {@snippet :
 *    final SnippetExample instance = SnippetExample.newInstance(); // @highlight substring="newInstance"
 *    instance.processIt();
 * }
 */
public class SnippetExample
{
    /**
     * No-arguments constructor not intended for public use.
     * Use {@link #newInstance()} instead to get an instance of me.
     */
    private SnippetExample()
    {
    }

    /**
     * Preferred approach for obtaining an instance of me.
     *
     * @return Instance of me.
     */
    public SnippetExample newInstance()
    {
        return new SnippetExample();
    }

    /**
     * Performs valuable processing.
     */
    public void processIt()
    {
    }
}
