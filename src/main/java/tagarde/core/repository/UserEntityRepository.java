package tagarde.core.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tagarde.core.domain.auth.user.UserEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Integer> {

    @Query(value = "SELECT U FROM UserEntity U WHERE U.email = :email")
    Optional<UserEntity> fetchUserByEmail(@Param("email") final String email);

    @Query(value = "SELECT U FROM UserEntity U WHERE U.id = :id")
    Optional<UserEntity> fetchUserById(@Param("id") final UUID id);

    @Query(value = "SELECT EXISTS(SELECT U FROM UserEntity U WHERE U.email = :email) ")
    boolean isEmailRegistered(@Param("email") final String email);

}
