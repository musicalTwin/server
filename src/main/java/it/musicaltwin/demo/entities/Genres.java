package it.musicaltwin.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "genres")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Genres {

    @Id
    @GeneratedValue
    @Column(name = "genre_id")
    private Long id;

    @Column(name = "genre_name", nullable = false)
    private String genreName;

}
