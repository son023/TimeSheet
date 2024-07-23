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

    @Query("SELECT u FROM User u " +
            "JOIN u.branch b " +
            "JOIN u.position p " +
            "JOIN u.role r " +
            "JOIN u.type t " +
            "WHERE (:userName IS NULL OR :userName = '' OR u.userName LIKE %:userName%) " +
            "AND (:branchName IS NULL OR :branchName = '' OR b.branchName LIKE %:branchName%) " +
            "AND (:positionName IS NULL OR :positionName = '' OR p.positionName LIKE %:positionName%) " +
            "AND (:roleName IS NULL OR :roleName = '' OR r.roleName LIKE %:roleName%) " +
            "AND (:typeName IS NULL OR :typeName = '' OR t.typeName LIKE %:typeName%)")
    List<User> searchUsers(
            @Param("userName") String userName,
            @Param("branchName") String branchName,
            @Param("positionName") String positionName,
            @Param("roleName") String roleName,
            @Param("typeName") String typeName);
}
