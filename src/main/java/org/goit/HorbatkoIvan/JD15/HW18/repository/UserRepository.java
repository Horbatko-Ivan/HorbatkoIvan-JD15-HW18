package org.goit.HorbatkoIvan.JD15.HW18.repository;

import org.goit.HorbatkoIvan.JD15.HW18.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

@SuppressWarnings("checkstyle:MissingJavadocType")
public interface UserRepository extends JpaRepository<User, Long> {

}
