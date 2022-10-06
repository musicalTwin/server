package it.musicaltwin.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.musicaltwin.demo.entities.Users;
import it.musicaltwin.demo.entities.UsersArtists;
import it.musicaltwin.demo.repositories.UsersArtistsRepository;

@Service
public class UsersArtistsService {
    
    private final UsersArtistsRepository usersArtistsRepository;

    @Autowired
    public UsersArtistsService(UsersArtistsRepository usersArtistsRepository) {
        this.usersArtistsRepository = usersArtistsRepository;
    }

    public List<String> getTopArtists(String userId) {
        return usersArtistsRepository.getTopArtistsOfUser(userId);
    }

    public Boolean checkIfUserInDb(Users user) {
        return !usersArtistsRepository.findAllIdFromUserId(user.getId()).isEmpty();
    }

    public void removeUserFromDatabase(Users user) {
        List<Long> ids = usersArtistsRepository.findAllIdFromUserId(user.getId());
        usersArtistsRepository.deleteAllById(ids);
    }

    public void addToDatabase(UsersArtists usersArtists) {
        usersArtistsRepository.save(usersArtists);
    }
    
}
