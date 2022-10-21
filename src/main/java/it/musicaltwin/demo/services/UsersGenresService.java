package it.musicaltwin.demo.services;

import it.musicaltwin.demo.entities.Users;
import it.musicaltwin.demo.entities.UsersGenres;
import it.musicaltwin.demo.repositories.UsersGenresRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersGenresService {

    private final UsersGenresRepository usersGenresRepository;

    @Autowired
    public UsersGenresService(UsersGenresRepository usersGenresRepository) {
        this.usersGenresRepository = usersGenresRepository;
    }

    public List<String> getListenedGenresId(String userId) {
        return usersGenresRepository.findListenedGenresId(userId);
    }

    public void addToDatabase(UsersGenres usersGenres) {
        usersGenresRepository.save(usersGenres);
    }

    public void removeUserFromDatabase(Users user) {
        List<Long> ids = usersGenresRepository.findAllIdFromUserId(user.getId());
        usersGenresRepository.deleteAllById(ids);
    }

    public Boolean checkIfUserInDb(Users user) {
        List<Long> usersGenres = usersGenresRepository.findAllIdFromUserId(user.getId());
        return !usersGenres.isEmpty();
    }

    public List<UsersGenres> getListenedGenres(String userId) {
        return usersGenresRepository.findListenedGenres(userId);
    }

}
