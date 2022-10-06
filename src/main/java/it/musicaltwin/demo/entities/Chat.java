package it.musicaltwin.demo.entities;

import java.sql.Timestamp;

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

    @Column(name = "last_message", nullable = true)
    private String lastMessage;

    @Column(name = "last_time_sent", nullable = true)
    private Timestamp lastTimeSent;

    public Chat(String user1Id, String user2Id) {
        this.user1Id = user1Id;
        this.user2Id = user2Id;
    }

}