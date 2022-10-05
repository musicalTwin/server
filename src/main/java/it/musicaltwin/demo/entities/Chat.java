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
@Table(name = "chat")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chat {
    
    @Id
    @GeneratedValue(generator = "id")
    @Column(name = "chat_id")
    private Long id;

    @Column(name = "user1_id", nullable = false)
    private String user1Id;
    
    @Column(name = "user2_id", nullable = false)
    private String user2Id;
}