package com.iuh.contactapp.Contact.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ContactReponsitory extends JpaRepository<Contact,Long> {

    @Query("SELECT c FROM Contact c where c.phoneNumber=:phone and c.user.id=:id")
    List<Contact> findByNumberPhone(String phone,Long id);

    @Query("SELECT c FROM Contact c where c.user.id=:id")
    Page<Contact> findContact(Pageable pageable,Long id);

}
