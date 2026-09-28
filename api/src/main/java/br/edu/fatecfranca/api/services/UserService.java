package br.edu.fatecfranca.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.fatecfranca.api.entities.User;
import br.edu.fatecfranca.api.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    public User insert(User obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public User update(Long id, User obj) {
        User entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
        updateData(entity, obj);
        return repository.save(entity);
    }

    private void updateData(User entity, User obj) {
        if (obj.getFullname() != null) entity.setFullname(obj.getFullname());
        if (obj.getUsername() != null) entity.setUsername(obj.getUsername());
        if (obj.getEmail() != null) entity.setEmail(obj.getEmail());
        if (obj.getPassword() != null) entity.setPassword(obj.getPassword());
        if (obj.getIsAdmin() != null) entity.setIsAdmin(obj.getIsAdmin());
    }
}
