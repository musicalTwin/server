package it.musicaltwin.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users_artists")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersArtists {
    
    @Id
    @Column(name = "artist_id")
    @GeneratedValue(generator = "id", strategy = GenerationType.TABLE)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "artist_name", nullable = false)
    private String artistName;

    public UsersArtists(String userId, String artistName) {
        this.userId = userId;
        this.artistName = artistName;
    }
    
}
