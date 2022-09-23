package it.musicaltwin.demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "interested_in")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterestedIn {

    @Id
    @GeneratedValue(generator = "id", strategy = GenerationType.TABLE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "gender_id", nullable = false)
    private Genders gender;

    public InterestedIn(Users user, Genders gender) {
        this.user = user;
        this.gender = gender;
    }

}
