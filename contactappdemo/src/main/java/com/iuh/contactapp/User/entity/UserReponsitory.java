package com.iuh.contactapp.User.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReponsitory extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.numberphone = :numberphone")
    public User getBynumberphone(@Param("numberphone") String numberphone);
}
