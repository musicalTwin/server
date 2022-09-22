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
@Table(name = "genders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Genders {

    @Id
    @GeneratedValue
    @Column(name = "gender_id")
    private Long id;

    @Column(name = "gender_name", nullable = false)
    private String name;

    // public Genders(Long id) {
    // this.id = id;
    // this.name = getName();
    // }

}
