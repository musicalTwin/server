package it.musicaltwin.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "matches")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Matches {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "matched")
    private boolean matched;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "card_id", nullable = false)
    private Cards card;
}