package adrianromanski;

import java.util.ArrayList;

    public class Album {
        private String artistName;
        private String albumName;
        private ArrayList<Song> songsList;

        public Album(String artistName, String albumName) {
            this.artistName = artistName;
            this.albumName = albumName;
            this.songsList = new ArrayList<Song>();
        }

        public String getArtistName() {
            return artistName;
        }

        public String getAlbumName() {
            return albumName;
        }

        public void addSong(Song song) {
            songsList.add(song);
        }

        public int countSongs(Album album) {
            int count = 0;
            for (int i = 0; i < album.songsList.size(); i++) {
                count += 1;
            }
            return count;
        }

        public void printSongs() {
            for (int i = 0; i < songsList.size(); i++) {
                System.out.println((i + 1) + "# Song is " + songsList.get(i).getTitle()
                        + " Duration " + songsList.get(i).getDuration());
            }
        }

        public Song findSong(String songName) {
            for (int i = 0; i < songsList.size(); i++) {
                if (songsList.get(i).getTitle().equals(songName)) {
                    Song song = songsList.get(i);
                    return song;
                }
            }
            return null;
        }
    }

