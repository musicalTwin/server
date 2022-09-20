package it.musicaltwin.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users_genres")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersGenres {

    @Id
    @GeneratedValue
    @Column(name = "users_genres_id")
    @JsonIgnore
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private Users user;

    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = false)
    private Genres genre;

}
