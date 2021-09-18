package dustin.examples.optionalabuse;

import java.util.Optional;
import java.util.function.Consumer;

import static java.lang.System.out;

/**
 * Demonstrates overuse of {@link Optional}: using {@link Optional}
 * when it adds overhead with little additional value. In particular,
 * demonstrates that it's rarely worth wrapping a returned value from
 * a method call with an {@link Optional} via its
 * {@link Optional#ofNullable(Object)} method if all that is to be
 * done with it is to determine if it's empty or present.
 */
public class UnnecessaryOptionalDemonstrations
{
    /**
     * Method simulating more complicated method that can return {@code null}.
     *
     * @return Always return {@code null} for this contrived example.
     */
    private Object methodPotentiallyReturningNull()
    {
        return null;
    }

    /**
     * Demonstrates using {@link Optional} in exactly the manner {@code null}
     * is often used (conditional on whether the returned value is empty or
     * not versus on whether the returned value is {@code null} or not).
     */
    public void demonstrateOptionalUsedLikeNullUsed()
    {
        final Optional<Object> optionalReturn = Optional.ofNullable(methodPotentiallyReturningNull());
        if (optionalReturn.isEmpty())
        {
            out.println("The returned Object is empty.");
        }
        else
        {
            out.println("The returned Object is NOT empty: " + optionalReturn);
            // code processing non-null return object goes here ...
        }
    }

    /**
     * Demonstrates using {@link Optional} methods {@link Optional#ifPresent(Consumer)}
     * and {@link Optional#isEmpty()} in similar manner to traditional condition based
     * on {@code null} or not {@code null}.
     */
    public void demonstrateOptionalIfPresentAndIsEmpty()
    {
        final Optional<Object> optionalReturn = Optional.ofNullable(methodPotentiallyReturningNull());
        optionalReturn.ifPresent( (it) -> out.println("The returned Object is NOT empty: " + it));
        if (optionalReturn.isEmpty())
        {
            out.println("The returned object is empty.");
        }
    }

    /**
     * Demonstrates approach to conditional based on {@code null} or
     * not {@code null} that is traditional pre-{@link Optional} approach.
     */
    public void demonstrateWithoutOptional()
    {
        final Object returnObject = methodPotentiallyReturningNull();
        if (returnObject == null)
        {
            out.println("The returned Object is null.");
        }
        else
        {
            out.println("The returned object is NOT empty: " + returnObject);
        }
    }

    /**
     * Main executable function that executes this class's demonstration methods.
     *
     * @param arguments Command-line arguments; none expected.
     */
    public static void main(final String[] arguments)
    {
        final UnnecessaryOptionalDemonstrations instance = new UnnecessaryOptionalDemonstrations();
        instance.demonstrateOptionalUsedLikeNullUsed();
        instance.demonstrateOptionalIfPresentAndIsEmpty();
        instance.demonstrateWithoutOptional();
    }
}
