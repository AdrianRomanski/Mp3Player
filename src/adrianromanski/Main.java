package adrianromanski;

import java.util.*;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Album> myAlbums = new ArrayList<Album>();
    private static LinkedList<Song> mySongsPlaylist = new LinkedList<Song>();

    public static void main(String[] args) {

        // Adding Albums
        addAlbum("Creedence Clearwater Revival", "Pendulum");
        addAlbum("The Police", "Synchronicity");

        // Adding songs Creedence Clearwater Revival
        addSongs("Pendulum", "Pagan Baby", 6.25);
        addSongs("Pendulum", "Sailor's Lament", 3.49);
        addSongs("Pendulum", "Chameleon", 3.21);
        addSongs("Pendulum", "Have You Ever Seen the Rain?", 2.40);
        addSongs("Pendulum", "(Wish I Could) Hideaway", 3.47);
        addSongs("Pendulum", "Born to Move", 5.40);
        addSongs("Pendulum", "Hey Tonight", 2.45);
        addSongs("Pendulum", "It's Just a Thought", 3.56);
        addSongs("Pendulum", "Molina", 2.44);
        addSongs("Pendulum", "Rude Awakening, No. 2", 6.22);

        System.out.println("**********************************************************");
        // Adding songs The Police
        addSongs("Synchronicity", "Synchronicity I", 3.23);
        addSongs("Synchronicity", "Walking in Your Footsteps", 3.56);
        addSongs("Synchronicity", "O My God", 4.02);
        addSongs("Synchronicity", "Mother", 3.05);
        addSongs("Synchronicity", "Miss Gradenko", 2.00);
        addSongs("Synchronicity", "Synchronicity II", 5.02);
        addSongs("Synchronicity", "Every Breath You Take", 4.13);
        addSongs("Synchronicity", "King of Pain", 4.59);
        addSongs("Synchronicity", "Wrapped Around Your Finger", 5.13);
        addSongs("Synchronicity", "Tea in the Sahara", 4.19);
        addSongs("Synchronicity", "Murder By Numbers", 4.31);

        printSongs("Synchronicity","The Police");
        printSongs("Pendulum","Creedence Clearwater Revival");

        addSongToPlaylist("Pendulum","Rude Awakening, No. 2");
        addSongToPlaylist("Pendulum","Have You Ever Seen the Rain?");
        addSongToPlaylist("Pendulum","Molina");
        addSongToPlaylist("Synchronicity", "King of Pain");
        addSongToPlaylist("Synchronicity", "Miss Gradenko");
        addSongToPlaylist("Synchronicity", "Every Breath You Take");
        addSongToPlaylist("Synchronicity", "Tea in the Sahara");

        playListManager(mySongsPlaylist);



    }


    private static void playListManager (LinkedList<Song> playList) {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        boolean goingForward = true; // Tim logic from the previous video
        int count = 0; // help with blocking options before the playlist start
                      // and also track a song number
        ListIterator<Song> listIterator = playList.listIterator();

        if (playList.isEmpty()) {
            System.out.println("No songs in your playlist: ");
        } else {
            printMenu();
            while (!quit) {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        if(count != 0) { // Preventing from starting playlist twice
                            System.out.println("Playlist already started");
                        } else {
                            count += 1;
                            System.out.println("Starting playlist");
                            System.out.println("Listening to: " + count + "# song "
                                    + listIterator.next().toString());
                        }
                        break;
                    case 2:
                        if (count == 0) {
                            System.out.println("Please start the playlist");
                        } else {
                            if (!goingForward) {  // If user previously selected to go backward
                                if (listIterator.hasNext()) { // Checking if we can go next
                                    listIterator.next();  // Setting direction
                                }
                                goingForward = true;  // Because user selected to go forward - next song
                            }
                            if (listIterator.hasNext()) {
                                count += 1;
                                System.out.println("Next >>> " + count + "# song - Listening to: "
                                        + listIterator.next().toString());
                            } else {
                                System.out.println("Reached the end of the playlist");
                            }
                        }
                        break;
                    case 3:
                        if (count == 0) {
                            System.out.println("Please start the playlist");
                        } else {
                            if (goingForward) { // If user previously was going forward
                                if (listIterator.hasPrevious()) { // Checking if we can go previous
                                    listIterator.previous();  // Setting direction
                                }
                                goingForward = false; // Because user selected to go backward - previous song
                            }
                            if (listIterator.hasPrevious()) {
                                count -= 1;
                                System.out.println("Previous <<< " + count + "# song - Listening to: "
                                        + listIterator.previous().toString());
                            } else {
                                System.out.println("Reached the beginnig of the playlist");
                            }
                        }
                        break;
                    case 4:
                        if (count == 0) {
                            System.out.println("Please start the playlist");
                        } else {
                            if (!goingForward) {
                                listIterator.next();
                                System.out.println("Replay song - Listening to: "
                                        + listIterator.previous().toString());
                            } else {
                                listIterator.previous();
                                System.out.println("Replay song - Listening to: "
                                        + listIterator.next().toString());
                            }
                        }
                        break;
                    case 5:
                        if (count == 0) {
                            System.out.println("Please start the playlist");
                        } else {
                            if (!goingForward) {
                                listIterator.next();
                                System.out.println("Deleted song: "
                                        + listIterator.previous().toString());
                                listIterator.remove();
                            } else {
                                listIterator.previous();
                                System.out.println("Deleted song: "
                                        + listIterator.next().toString());
                                listIterator.remove();
                            }
                        }
                        break;
                    case 6:
                        System.out.println("Thank you for using our mp3 player");
                        quit = true;
                        break;
                    case 7:
                        printPlaylist(mySongsPlaylist);
                        break;
                }
            }
        }
    }
    private static void addAlbum(String artistName, String albumName) {
        if (findAlbum(albumName) == null) {
            Album newAlbum = new Album(artistName, albumName);
            myAlbums.add(newAlbum);
        }
    }

    private static Album findAlbum(String albumName) {

        for (int i = 0; i < myAlbums.size(); i++) {
            if (myAlbums.get(i).getAlbumName().equals(albumName)) {
                return myAlbums.get(i);
            }
        }
        return null;
    }

    private static void addSongToPlaylist(String albumName, String songName) {
        if (findAlbum(albumName) != null) {
            Album album = findAlbum(albumName);
            int index = myAlbums.indexOf(album);
            Song song = myAlbums.get(index).findSong(songName);
            mySongsPlaylist.add(song);
        }
    }

    private static void addSongs(String albumName, String songName, double duration) {

        if (findAlbum(albumName) != null) {
            Album album = findAlbum(albumName);
            int index = myAlbums.indexOf(album);
            Song newSong = new Song(songName, duration);
            myAlbums.get(index).addSong(newSong);
            int count = myAlbums.get(index).countSongs(album);
            System.out.println("Song: " + songName + " sucesfully added to album " + albumName
                    + " Artist " + myAlbums.get(index).getArtistName());
        } else {
            System.out.println(albumName + " does not exist");
        }
    }

    private static void printSongs(String albumName, String artistName) {
        // Was using it to check if the songs was added correctly
        if (findAlbum(albumName) != null) {
            Album album = findAlbum(albumName);
            int index = myAlbums.indexOf(album);
            System.out.println("*************************************");
            System.out.println("Printing songs for album " + albumName);
            myAlbums.get(index).printSongs();
        } else {
            System.out.println("Album " + albumName + " does not exist");
        }
    }

    private static void printPlaylist(LinkedList<Song> playList) {
        int count = 0;
        Iterator<Song> iterator = playList.iterator();
        while (iterator.hasNext()) {   //.hasNext Returns true if this list iterator has more elements
                                      // when traversing the list in the forward direction.
            count += 1;
            System.out.println(count + "# " + iterator.next().toString());  //.next() Returns the next element in the list
        }                                                                  // and advances the cursor position.
                                                                          // toString converts object to String value
        System.out.println("The end of Playlist");
    }

    private static void printMenu() {
        System.out.println("Welcome in our MP3 player manager, " +
                "please select action by pressing correct button\n" +
                "1# Start Playlist\n" +
                "2# Play next song\n" +
                "3# Play previous song\n" +
                "4# Repeat current song\n" +
                "5# Delete song from playlist\n" +
                "6# Turn off the Mp3\n" +
                "7# Playlist review");

    }
}