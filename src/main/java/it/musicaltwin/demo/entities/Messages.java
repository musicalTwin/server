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
@Table(name = "messages")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Messages {
    
    @Id
    @GeneratedValue(generator = "id")
    @Column(name = "message_id")
    private Long id;

    @Column(name = "chat_id", nullable = false)
    private Long chatId;

    @Column(name = "sender_id", nullable = false)
    private String senderId;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "date_time", nullable = false)
    private Timestamp dateTime;

}