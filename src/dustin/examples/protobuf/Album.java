package dustin.examples.protobuf;

import java.util.ArrayList;
import java.util.List;

/**
 * Music album.
 */
public class Album
{
   private final String title;

   private final List<String> artists;

   private final int releaseYear;

   private final List<String> songsTitles;

   private Album(final String newTitle, final List<String> newArtists,
                 final int newYear, final List<String> newSongsTitles)
   {
      title = newTitle;
      artists = newArtists;
      releaseYear = newYear;
      songsTitles = newSongsTitles;
   }

   public String getTitle()
   {
      return title;
   }

   public List<String> getArtists()
   {
      return artists;
   }

   public int getReleaseYear()
   {
      return releaseYear;
   }

   public List<String> getSongsTitles()
   {
      return songsTitles;
   }

   @Override
   public String toString()
   {
      return "'" + title + "' (" + releaseYear + ") by " + artists + " features songs " + songsTitles;
   }

   /**
    * Builder class for instantiating an instance of
    * enclosing Album class.
    */
   public static class Builder
   {
      private String title;
      private ArrayList<String> artists = new ArrayList<>();
      private int releaseYear;
      private ArrayList<String> songsTitles = new ArrayList<>();

      public Builder(final String newTitle, final int newReleaseYear)
      {
         title = newTitle;
         releaseYear = newReleaseYear;
      }

      public Builder songTitle(final String newSongTitle)
      {
         songsTitles.add(newSongTitle);
         return this;
      }

      public Builder songsTitles(final List<String> newSongsTitles)
      {
         songsTitles.addAll(newSongsTitles);
         return this;
      }

      public Builder artist(final String newArtist)
      {
         artists.add(newArtist);
         return this;
      }

      public Builder artists(final List<String> newArtists)
      {
         artists.addAll(newArtists);
         return this;
      }

      public Album build()
      {
         return new Album(title, artists, releaseYear, songsTitles);
      }
   }
}
