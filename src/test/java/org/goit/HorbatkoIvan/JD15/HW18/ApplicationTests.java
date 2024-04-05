package org.goit.HorbatkoIvan.JD15.HW18;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Collections;
import org.goit.HorbatkoIvan.JD15.HW18.controller.NoteController;
import org.goit.HorbatkoIvan.JD15.HW18.model.Note;
import org.goit.HorbatkoIvan.JD15.HW18.service.NoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(NoteController.class)
class NoteControllerTests {

  private static final Long userId = 1L;
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private NoteService noteService;

  @Test
  @WithMockUser(username = "user", roles = {"USER"})
  void testListNotesForUser() throws Exception {
    Note note = new Note();
    note.setId(1L);
    note.setTitle("Test Title");
    note.setContent("Test Content");

    given(noteService.listAllForUser(userId)).willReturn(Collections.singletonList(note));

    mockMvc.perform(get("/api/users/{userId}/notes", userId))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].title", is(note.getTitle())))
        .andExpect(jsonPath("$[0].content", is(note.getContent())));
  }

  @Test
  @WithMockUser(username = "user", roles = {"USER"})
  void testGetNoteByIdForUser() throws Exception {
    Note note = new Note();
    note.setId(1L);
    note.setTitle("Test Title");
    note.setContent("Test Content");

    given(noteService.getNoteByIdForUser(userId, note.getId())).willReturn(note);

    mockMvc.perform(get("/api/users/{userId}/notes/{noteId}", userId, note.getId()))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.title", is(note.getTitle())))
        .andExpect(jsonPath("$.content", is(note.getContent())));
  }
}
