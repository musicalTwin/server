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
@Table(name = "users_songs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersSongs {
    
    @Id
    @Column(name = "song_id")
    @GeneratedValue(generator = "id", strategy = GenerationType.TABLE)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "song_name", nullable = false)
    private String songName;

    public UsersSongs(String userId, String songName) {
        this.userId = userId;
        this.songName = songName;
    }
    
}
