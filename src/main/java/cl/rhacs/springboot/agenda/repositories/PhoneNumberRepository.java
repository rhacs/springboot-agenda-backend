package cl.rhacs.springboot.agenda.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.rhacs.springboot.agenda.models.Contact;
import cl.rhacs.springboot.agenda.models.PhoneNumber;

@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Long> {

    public List<PhoneNumber> findAllByContact(final Contact contact);

    public Optional<PhoneNumber> findByNumber(final Long number);

}
