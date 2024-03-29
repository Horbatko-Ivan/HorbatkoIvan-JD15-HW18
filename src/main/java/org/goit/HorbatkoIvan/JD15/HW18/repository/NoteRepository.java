package org.goit.HorbatkoIvan.JD15.HW18.repository;

import java.util.List;
import java.util.Optional;
import org.goit.HorbatkoIvan.JD15.HW18.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

@SuppressWarnings("checkstyle:MissingJavadocType")
public interface NoteRepository extends JpaRepository<Note, Long> {

  List<Note> findAllByUserId(Long userId);

  Optional<Note> findByIdAndUserId(Long id, Long userId);

  void deleteByIdAndUserId(Long id, Long userId);
}
