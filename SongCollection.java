import java.util.Objects;

/**
 * The Song class represents a single song with an artist, title, and lyrics.
 * Author: [Zach Nadeau]
 */
public class song implements Comparable<song> {
    // Private fields
    private final String artist;
    private final String title;
    private final String lyrics;

    /**
     * Constructs a Song object with the specified artist, title, and lyrics.
     * 
     * @param artist The artist of the song.
     * @param title  The title of the song.
     * @param lyrics The lyrics of the song.
     */
    public song(String artist, String title, String lyrics) {
        this.artist = artist;
        this.title = title;
        this.lyrics = lyrics;
    }

    /**
     * Returns the artist of the song.
     * 
     * @return The artist of the song.
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Returns the title of the song.
     * 
     * @return The title of the song.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the lyrics of the song.
     * 
     * @return The lyrics of the song.
     */
    public String getLyrics() {
        return lyrics;
    }

    /**
     * Returns a string representation of the song in the format:
     * ARTIST, "TITLE".
     * 
     * @return A string representation of the song.
     */
    @Override
    public String toString() {
        return artist + ", \"" + title + "\"";
    }

    /**
     * Compares this song to another song alphabetically by artist and then by
     * title. The comparison is case-insensitive.
     * 
     * @param other The other song to compare to.
     * @return A negative integer, zero, or a positive integer if this song is
     *         less than, equal to, or greater than the specified song.
     */
    @Override
    public int compareTo(song other) {
        int artistComparison = this.artist.compareToIgnoreCase(other.artist);
        if (artistComparison != 0) {
            return artistComparison;
        }
        return this.title.compareToIgnoreCase(other.title);
    }

    /**
     * Overrides equals to compare songs based on artist, title, and lyrics.
     * 
     * @param o The object to compare.
     * @return true if the songs are identical; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        song song = (song) o;
        return artist.equalsIgnoreCase(song.artist) &&
               title.equalsIgnoreCase(song.title) &&
               lyrics.equals(song.lyrics);
    }

    /**
     * Generates a hash code for the song based on artist, title, and lyrics.
     * 
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(artist.toLowerCase(), title.toLowerCase(), lyrics);
    }

    /**
     * Unit Testing for the Song class.
     * This method tests all public methods of the Song class.
     */
    public static void main(String[] args) {
        // Test data
        song song1 = new song("Artist One", "Title One", "Lyrics of the first song");
        song song2 = new song("Artist Two", "Title Two", "Lyrics of the second song");
        song song3 = new song("Artist One", "Title Two", "Lyrics of the third song");

        // Testing getters
        System.out.println("Artist: " + song1.getArtist()); // Expected: Artist One
        System.out.println("Title: " + song1.getTitle()); // Expected: Title One
        System.out.println("Lyrics: " + song1.getLyrics()); // Expected: Lyrics of the first song

        // Testing toString
        System.out.println("String Representation: " + song1); // Expected: Artist One, "Title One"

        // Testing compareTo
        System.out.println("Compare song1 and song2: " + song1.compareTo(song2)); // Expected: < 0
        System.out.println("Compare song1 and song3: " + song1.compareTo(song3)); // Expected: < 0

        // Testing equals
        song song4 = new song("Artist One", "Title One", "Lyrics of the first song");
        System.out.println("song1 equals song4: " + song1.equals(song4)); // Expected: true
        System.out.println("song1 equals song2: " + song1.equals(song2)); // Expected: false

        // Testing hashCode
        System.out.println("song1 hashCode: " + song1.hashCode()); // Expected: consistent hash
        System.out.println("song4 hashCode: " + song4.hashCode()); // Expected: same as song1
    }
}
