package com.revature.beans.repositories;

import com.revature.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmailAndPassword(String email, String password);

    Optional<User> findByEmail(String email);

    List<User> findByFirstNameAndLastName(String firstName, String lastName);

    @Query(value = "SELECT * FROM users u WHERE u.first_name ~* :name OR u.last_name ~* :name", nativeQuery = true)
    List<User> findByFirstNameOrLastName(@Param("name") String name);
}
