import java.io.*;
import java.util.*;
import java.util.stream.Stream;

/**
 * The SongCollection class represents a collection of songs loaded from a file.
 * Author: [Your Name]
 * Revised by: [Add Name and Date when making changes]
 */
public class SongCollection {
    private Song[] songs; // Array to store songs

    /**
     * Constructor that reads a file of song data, builds an array of Songs, and sorts the array.
     * 
     * @param fileName The name of the data file.
     * @throws IllegalArgumentException If the file cannot be read or parsed.
     * Author: [Your Name]
     */
    public SongCollection(String fileName) {
        List<Song> songList = new ArrayList<>(); // Temporary list to hold songs
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            String artist = null, title = null, lyrics = null;
            StringBuilder lyricsBuilder = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("ARTIST=")) {
                    if (artist != null && title != null && lyricsBuilder.length() > 0) {
                        // Add the previous song
                        lyrics = lyricsBuilder.toString().trim();
                        songList.add(new Song(artist, title, lyrics));
                    }
                    // Reset for the next song
                    artist = line.substring(7).trim();
                    title = null;
                    lyricsBuilder.setLength(0);
                } else if (line.startsWith("TITLE=")) {
                    title = line.substring(6).trim();
                } else {
                    lyricsBuilder.append(line).append("\n");
                }
            }

            // Add the last song in the file
            if (artist != null && title != null && lyricsBuilder.length() > 0) {
                lyrics = lyricsBuilder.toString().trim();
                songList.add(new Song(artist, title, lyrics));
            }

            // Convert the list to an array and sort it
            songs = songList.toArray(new Song[0]);
            Arrays.sort(songs);

        } catch (IOException e) {
            throw new IllegalArgumentException("Error reading file: " + e.getMessage(), e);
        }
    }

    /**
     * Returns the sorted array of songs.
     * 
     * @return The array of songs.
     * Author: [Your Name]
     */
    public Song[] getAllSongs() {
        return songs;
    }

    /**
     * Unit Testing for the SongCollection class.
     * Author: [Your Name]
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java SongCollection <file_name>");
            return;
        }

        try {
            // Create an instance of SongCollection with the given file name
            SongCollection collection = new SongCollection(args[0]);

            // Get the array of songs
            Song[] list = collection.getAllSongs();

            // Print the total number of songs
            System.out.println("Total number of songs: " + list.length);

            // Print the artist and title of the first 10 songs or fewer
            System.out.println("First 10 songs (or fewer):");
            Stream.of(list).limit(10).forEach(System.out::println);

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
