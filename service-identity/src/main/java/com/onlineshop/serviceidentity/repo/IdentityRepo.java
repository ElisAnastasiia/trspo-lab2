package com.onlineshop.serviceidentity.repo;
import com.onlineshop.serviceidentity.repo.model.Identity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentityRepo extends JpaRepository<Identity, Long> { }
