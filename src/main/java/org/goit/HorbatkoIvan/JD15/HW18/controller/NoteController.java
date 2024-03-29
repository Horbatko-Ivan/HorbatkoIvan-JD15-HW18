package org.goit.HorbatkoIvan.JD15.HW18.controller;

import jakarta.validation.Valid;
import java.util.List;
import org.goit.HorbatkoIvan.JD15.HW18.model.Note;
import org.goit.HorbatkoIvan.JD15.HW18.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SuppressWarnings("checkstyle:MissingJavadocType")
@RestController
@RequestMapping("/api/users/{userId}/notes")
public class NoteController {

  private final NoteService noteService;

  @Autowired
  public NoteController(NoteService noteService) {
    this.noteService = noteService;
  }

  @GetMapping
  public ResponseEntity<List<Note>> getAllNotesForUser(@PathVariable Long userId) {
    List<Note> notes = noteService.listAllForUser(userId);
    return ResponseEntity.ok(notes);
  }

  @PostMapping
  public ResponseEntity<Note> addNoteForUser(@PathVariable Long userId, @Valid @RequestBody Note note) {
    Note savedNote = noteService.addNoteForUser(userId, note);
    return new ResponseEntity<>(savedNote, HttpStatus.CREATED);
  }

  @GetMapping("/{noteId}")
  public ResponseEntity<Note> getNoteByIdForUser(@PathVariable Long userId, @PathVariable Long noteId) {
    Note note = noteService.getNoteByIdForUser(userId, noteId);
    return ResponseEntity.ok(note);
  }

  @PutMapping("/{noteId}")
  public ResponseEntity<Note> updateNoteForUser(@PathVariable Long userId, @PathVariable Long noteId, @Valid @RequestBody Note noteDetails) {
    Note updatedNote = noteService.updateNoteForUser(userId, noteId, noteDetails);
    return ResponseEntity.ok(updatedNote);
  }

  @DeleteMapping("/{noteId}")
  public ResponseEntity<Void> deleteNoteForUser(@PathVariable Long userId, @PathVariable Long noteId) {
    noteService.deleteNoteForUser(userId, noteId);
    return ResponseEntity.ok().build();
  }
}
