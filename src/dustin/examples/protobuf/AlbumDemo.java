package dustin.examples.protobuf;

import static java.lang.System.out;

import com.google.protobuf.InvalidProtocolBufferException;

import java.util.List;

/**
 * Demonstrates using Protocol Buffers with Album.
 */
public class AlbumDemo
{
   /**
    * Generates instance of Album to be used in demonstration.
    *
    * @return Instance of Album to be used in demonstration.
    */
   public Album generateAlbum()
   {
      return new Album.Builder("Songs from the Big Chair", 1985)
         .artist("Tears For Fears")
         .songTitle("Shout")
         .songTitle("The Working Hour")
         .songTitle("Everybody Wants to Rule the World")
         .songTitle("Mothers Talk")
         .songTitle("I Believe")
         .songTitle("Broken")
         .songTitle("Head Over Heels")
         .songTitle("Listen")
         .build();
   }

   /**
    * Generates an instance of Album based on the provided
    * bytes array.
    *
    * @param binaryAlbum Bytes array that should represent an
    *    AlbumProtos.Album based on Google Protocol Buffers
    *    binary format.
    * @return Instance of Album based on the provided binary form
    *    of an Album; may be {@code null} if an error is encountered
    *    while trying to process the provided binary data.
    */
   public Album instantiateAlbumFromBinary(final byte[] binaryAlbum)
   {
      Album album = null;
      try
      {
         final AlbumProtos.Album copiedAlbumProtos = AlbumProtos.Album.parseFrom(binaryAlbum);
         final List<String> copiedArtists = copiedAlbumProtos.getArtistList();
         final List<String> copiedSongsTitles = copiedAlbumProtos.getSongTitleList();
         album = new Album.Builder(
            copiedAlbumProtos.getTitle(), copiedAlbumProtos.getReleaseYear())
            .artists(copiedArtists)
            .songsTitles(copiedSongsTitles)
            .build();
      }
      catch (InvalidProtocolBufferException ipbe)
      {
         out.println("ERROR: Unable to instantiate AlbumProtos.Album instance from provided binary data - "
            + ipbe);
      }
      return album;
   }

   /**
    * Demonstrates use of Google Protocol Buffers to write an
    * {@code Album} to a Google Protocol Buffers's binary form
    * and to instantiate an {@code Album} from that binary form.
    *
    * @param arguments Command-line arguments: none expected.
    */
   public static void main(final String[] arguments)
   {
      final AlbumDemo instance = new AlbumDemo();
      final Album album = instance.generateAlbum();
      final AlbumProtos.Album albumMessage
         = AlbumProtos.Album.newBuilder()
            .setTitle(album.getTitle())
            .addAllArtist(album.getArtists())
            .setReleaseYear(album.getReleaseYear())
            .addAllSongTitle(album.getSongsTitles())
            .build();
      final byte[] binaryAlbum = albumMessage.toByteArray();
      final Album copiedAlbum = instance.instantiateAlbumFromBinary(binaryAlbum);
      out.println("BEFORE Album (" + System.identityHashCode(album) + "): " + album);
      out.println(" AFTER Album (" + System.identityHashCode(copiedAlbum) + "): " + String.valueOf(copiedAlbum));
   }
}
