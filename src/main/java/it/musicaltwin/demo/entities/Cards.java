package it.musicaltwin.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cards")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cards {

    @Id
    @Column(name = "card_id")
    @GeneratedValue(generator = "id", strategy = GenerationType.TABLE)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    public Cards(Users user) {
        this.user = user;
    }
}
