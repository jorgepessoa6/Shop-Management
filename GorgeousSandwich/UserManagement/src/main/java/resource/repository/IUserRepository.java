package resource.repository;

import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import resource.model.ROLE;
import resource.model.User;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IUserRepository extends JpaRepository<User, UUID> {

    @Query("SELECT DISTINCT u FROM User u WHERE u.email = :email ")
    User getUserByEmail(@Param("email")String email);
    Optional<User> findUserByEmail(String email);

    @Query("SELECT u.id FROM User u WHERE u.id = :pic AND :role MEMBER OF u.roles")
    UUID findUserByIdWithRole(@Param("pic") UUID pic, @Param("role") ROLE role);
}
