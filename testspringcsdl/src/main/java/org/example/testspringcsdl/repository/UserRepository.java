package org.example.testspringcsdl.repository;

import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;

import org.example.testspringcsdl.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByUserName(String userName);

    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.userName = ?1")
    void deleteByUsername(String username);

    @Query(value = "SELECT * FROM users u WHERE u.user_name = ?1", nativeQuery = true)
    User findByUsername(String userName);

    Optional<User> findByUserName(String userName);

    @Query("SELECT u FROM User u "
            + "WHERE (:userName IS NULL OR :userName = '' OR LOWER(u.userName) LIKE LOWER(CONCAT('%', :userName, '%'))) "
            + "AND (:branchId IS NULL OR u.branch.id = :branchId) "
            + "AND (:positionId IS NULL OR u.position.id = :positionId) "
            + "AND (:roleId IS NULL OR u.role.id = :roleId) "
            + "AND (:typeId IS NULL OR u.type.id = :typeId)")
    List<User> searchUsers(
            @Param("userName") String userName,
            @Param("branchId") Long branchId,
            @Param("positionId") Long positionId,
            @Param("roleId") Long roleId,
            @Param("typeId") Long typeId);
}
