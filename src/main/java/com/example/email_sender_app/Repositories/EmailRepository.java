package com.example.email_sender_app.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.email_sender_app.Entities.Email;

public interface EmailRepository extends  JpaRepository<Email, Long>{

}
