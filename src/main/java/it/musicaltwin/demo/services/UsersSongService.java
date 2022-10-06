package it.musicaltwin.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.musicaltwin.demo.entities.Users;
import it.musicaltwin.demo.entities.UsersSongs;
import it.musicaltwin.demo.repositories.UsersSongRepository;

@Service
public class UsersSongService {

    private final UsersSongRepository usersSongRepository;

    @Autowired
    public UsersSongService(UsersSongRepository usersSongRepository) {
        this.usersSongRepository = usersSongRepository;
    }

    public List<String> getTopSongs(String userId) {
        return usersSongRepository.getTopSongs(userId);
    }

    public Boolean checkIfUserInDb(Users user) {
        return !usersSongRepository.findAllIdFromUserId(user.getId()).isEmpty();
    }

    public void removeUserFromDatabase(Users user) {
        usersSongRepository.deleteAllById(usersSongRepository.findAllIdFromUserId(user.getId()));
    }

    public void addToDatabase(UsersSongs usersSongs) {
        usersSongRepository.save(usersSongs);
    }
    
}
