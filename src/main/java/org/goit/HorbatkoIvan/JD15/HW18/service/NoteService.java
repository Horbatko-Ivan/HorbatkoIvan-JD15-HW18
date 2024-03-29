package org.goit.HorbatkoIvan.JD15.HW18.service;

import java.util.List;
import org.goit.HorbatkoIvan.JD15.HW18.exception.NoteNotFoundException;
import org.goit.HorbatkoIvan.JD15.HW18.exception.UserNotFoundException;
import org.goit.HorbatkoIvan.JD15.HW18.model.Note;
import org.goit.HorbatkoIvan.JD15.HW18.model.User;
import org.goit.HorbatkoIvan.JD15.HW18.repository.NoteRepository;
import org.goit.HorbatkoIvan.JD15.HW18.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@SuppressWarnings("checkstyle:MissingJavadocType")
@Service
public class NoteService {

  private final NoteRepository noteRepository;
  private final UserRepository userRepository;

  @Autowired
  public NoteService(NoteRepository noteRepository, UserRepository userRepository) {
    this.noteRepository = noteRepository;
    this.userRepository = userRepository;
  }

  public List<Note> listAllForUser(Long userId) {
    validateUser(userId);
    return noteRepository.findAllByUserId(userId);
  }

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public Note addNoteForUser(Long userId, Note note) {
    User user = validateUser(userId);
    note.setUser(user);
    return noteRepository.save(note);
  }

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public Note getNoteByIdForUser(Long userId, Long noteId) {
    validateUser(userId);
    return noteRepository.findByIdAndUserId(noteId, userId)
        .orElseThrow(() -> new NoteNotFoundException("Note not found with id: " + noteId));
  }

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public Note updateNoteForUser(Long userId, Long noteId, Note noteDetails) {
    validateUser(userId);
    Note note = noteRepository.findByIdAndUserId(noteId, userId)
        .orElseThrow(() -> new NoteNotFoundException("Note not found with id: " + noteId));

    note.setTitle(noteDetails.getTitle());
    note.setContent(noteDetails.getContent());
    return noteRepository.save(note);
  }

  public void deleteNoteForUser(Long userId, Long noteId) {
    validateUser(userId);
    noteRepository.deleteByIdAndUserId(noteId, userId);
  }

  private User validateUser(Long userId) {
    return userRepository.findById(userId)
        .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
  }
}
