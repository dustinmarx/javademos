package dustin.examples.npe;

/**
 * Dysfunctional class intended solely for demonstration
 * of difficulty of knowing where {@code NullPointerException}
 * is thrown when numerous potential {@code null}-returning
 * methods are chained together.
 *
 * This class is intentionally NOT declared {@code public} in
 * an attempt to keep its ugliness from getting any further
 * than this demonstration package {@code dustin.examples.npe}.
 */
final class DysfunctionalLocation
{
   Planet getPlanet()
   {
      return new Planet();
   }

   static class Planet
   {
      Continent getContinent()
      {
         return new Continent();
      }
   }

   static class Continent
   {
      Nation getNation()
      {
         return new Nation();
      }
   }

   static class Nation
   {
      Province getProvince()
      {
         return null;
      }
   }

   /**
    * Represents a province, state, shire or other geographical
    * division that contains one or more cities and constitutes
    * a nation along with one or more additional provinces. Not
    * all nations may have a provincial division.
    */
   static class Province
   {
      City getCity()
      {
         return new City();
      }
   }

   static class City
   {
      Street getStreet()
      {
         return new Street();
      }
   }

   static class Street
   {
      Address getAddress()
      {
         return new Address();
      }
   }

   static class Address
   {
   }
}
