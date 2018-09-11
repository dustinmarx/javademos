package dustin.examples.jdk12.switchexp;

/**
 * Enum representation of a response.
 * 
 * For the demonstration purposes, this enum is originally
 * compiled with only two values ({@link #YES} and {@link #NO}),
 * but then the {@link #MAYBE} is uncommented and the enum
 * is recompiled (by itself and without recompiling client
 * code) and then used in conjunction with client code to
 * demonstrate JEP 325 handling of unexpected enum values
 * in a {@code switch} <em>expression</em>.
 */
public enum Response
{
   YES,
   NO/*,
   MAYBE*/;
}
