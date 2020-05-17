package cl.rhacs.springboot.agenda.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.rhacs.springboot.agenda.models.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    public List<Contact> findByFirstName(final String firstName);

    public List<Contact> findByLastName(final String lastName);

}
