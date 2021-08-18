package dustin.examples.formatting;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.System.out;

/**
 * Demonstrative example of thread safe nature of a
 * {@link ThreadLocal}-wrapped instance of {@link SimpleDateFormat}.
 * Adapted from example at
 * https://www.callicoder.com/java-simpledateformat-thread-safety-issues/.
 */
public class SimpleDateFormatThreadLocalThreadSafeDemonstration
{
    /** Date/Time Format. */
    private static final ThreadLocal<SimpleDateFormat> SIMPLE_DATE_FORMAT
        = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"));

    /** DateTime string to be parsed. */
    private static final String DATE_TIME_STRING = "2021-07-12T13:25:45";

    public static void main(final String[] arguments)
    {
        final ExecutorService executorService = Executors.newFixedThreadPool(10);
        final Runnable task = () -> parseDate(DATE_TIME_STRING);

        for (int i = 0; i < 100; i++)
        {
            executorService.submit(task);
        }

        executorService.shutdown();
    }

    /**
     * Parse the supplied date/time string and write parsed {@link Date} to standard
     * output or write exception details to standard output if the supplied date/time
     * string cannot be parsed.
     *
     * @param dateTimeStringToParse Date/time string to be parsed.
     */
    private static void parseDate(final String dateTimeStringToParse)
    {
        try
        {
            final Date date = SIMPLE_DATE_FORMAT.get().parse(dateTimeStringToParse);
            out.println("Parsed date/time " + date + " from string '" + dateTimeStringToParse + "'.");
        }
        catch (ParseException parseException)
        {
            out.println("ERROR: ParseException while parsing string '" + dateTimeStringToParse + "': "
                    + parseException);
        }
        catch (Exception exception)
        {
            out.println("ERROR: Exception while parsing string '" + dateTimeStringToParse + "': " + exception);
        }
    }
}
