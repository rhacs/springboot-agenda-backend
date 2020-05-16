package cl.rhacs.springboot.agenda.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.rhacs.springboot.agenda.models.Contact;
import cl.rhacs.springboot.agenda.models.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    public List<Note> findAllByContact(final Contact contact);

}
