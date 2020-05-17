package cl.rhacs.springboot.agenda.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.rhacs.springboot.agenda.exceptions.ContactNotFoundException;
import cl.rhacs.springboot.agenda.exceptions.InformationNotFoundException;
import cl.rhacs.springboot.agenda.models.Contact;
import cl.rhacs.springboot.agenda.models.Note;
import cl.rhacs.springboot.agenda.models.PhoneNumber;
import cl.rhacs.springboot.agenda.models.Tag;
import cl.rhacs.springboot.agenda.repositories.ContactRepository;

@RestController
@RequestMapping(path = "/contacts")
public class ContactController {

    // Attributes
    // -----------------------------------------------------------------------------------------

    @Autowired
    private ContactRepository contactRepository;

    // GET Mappings
    // -----------------------------------------------------------------------------------------

    /**
     * Get all the contacts on the agenda
     *
     * @return the list of contacts
     */
    @GetMapping(path = { "", "/" })
    public ResponseEntity<List<Contact>> getAllContacts() {
        return new ResponseEntity<>(contactRepository.findAll(), HttpStatus.OK);
    }

    /**
     * Retrieve the specified {@link Contact} detail
     *
     * @param id the contact id
     * @return the contact detail
     * @throws ContactNotFoundException when the contact does not exists
     */
    @GetMapping(path = { "/{id:^\\d+$}" })
    public ResponseEntity<Contact> getContact(@PathVariable final Long id) throws ContactNotFoundException {
        final Optional<Contact> contact = contactRepository.findById(id);

        if (!contact.isPresent()) {
            throw new ContactNotFoundException(String.format("Contact with id %d not found", id));
        }

        return new ResponseEntity<>(contact.get(), HttpStatus.OK);
    }

    /**
     * Shows a list of {@link PhoneNumber}s of the specified {@link Contact}
     *
     * @param id the contact id
     * @return the list of phone numbers
     * @throws ContactNotFoundException     when the contact does not exists
     * @throws InformationNotFoundException when the contact does not have phone
     *                                      numbers
     */
    @GetMapping(path = { "/{id:^\\d+$}/phoneNumbers" })
    public ResponseEntity<List<PhoneNumber>> getContactPhoneNumbers(@PathVariable final Long id)
            throws ContactNotFoundException, InformationNotFoundException {
        final Optional<Contact> contact = contactRepository.findById(id);

        if (contact.isPresent()) {
            final Set<PhoneNumber> phoneNumbers = contact.get().getPhoneNumbers();

            if (!phoneNumbers.isEmpty())
                return new ResponseEntity<>(new ArrayList<>(phoneNumbers), HttpStatus.OK);

            throw new InformationNotFoundException(String.format("Contact with id %d does not have a phone number", id));
        }

        throw new ContactNotFoundException(String.format("Contact with id %d not found", id));
    }

    /**
     * Shows a list of {@link Tag}s assigned to the specified {@link Contact}
     *
     * @param id the contact id
     * @return a list of tags
     * @throws ContactNotFoundException     when the contact does not exists
     * @throws InformationNotFoundException when the contact does not have tags
     *                                      assigned
     */
    @GetMapping(path = { "/{id:^\\d+$}/tags" })
    public ResponseEntity<List<Tag>> getContactTags(@PathVariable final Long id)
            throws ContactNotFoundException, InformationNotFoundException {
        final Optional<Contact> contact = contactRepository.findById(id);

        if (contact.isPresent()) {
            final Set<Tag> tags = contact.get().getTags();

            if (!tags.isEmpty())
                return new ResponseEntity<>(new ArrayList<>(tags), HttpStatus.OK);

            throw new InformationNotFoundException(
                    String.format("Contact with id %d does not have an assigned tag", id));
        }

        throw new ContactNotFoundException(String.format("Contact with id %d not found", id));
    }

    /**
     * Shows a list of {@link Note}s for the specified {@link Contact}
     *
     * @param id the contact id
     * @return a list of notes
     * @throws ContactNotFoundException     when the contact does not exists
     * @throws InformationNotFoundException when the contact does not have notes
     *                                      assigned
     */
    @GetMapping(path = { "/{id:^\\d+$}/notes" })
    public ResponseEntity<List<Note>> getContactNotes(@PathVariable final Long id)
            throws ContactNotFoundException, InformationNotFoundException {
        final Optional<Contact> contact = contactRepository.findById(id);

        if (contact.isPresent()) {
            final Set<Note> notes = contact.get().getNotes();

            if (!notes.isEmpty())
                return new ResponseEntity<>(new ArrayList<>(contact.get().getNotes()), HttpStatus.OK);

            throw new InformationNotFoundException(
                    String.format("Contact with id %d does not have an assigned note", id));
        }

        throw new ContactNotFoundException(String.format("Contact with id %d not found", id));
    }

    // Post Mappings
    // -----------------------------------------------------------------------------------------

    /**
     * Add a {@link Contact} to the Agenda
     *
     * @param contact the contact to add
     * @return the contact if everything is good
     */
    @PostMapping(path = { "", "/" })
    public ResponseEntity<Contact> addContact(@RequestBody final Contact contact) {
        final Contact savedContact = contactRepository.save(contact);
        return new ResponseEntity<>(savedContact, HttpStatus.OK);
    }

    // Put Mappings
    // -----------------------------------------------------------------------------------------

    /**
     * Edits an existing {@link Contact}
     *
     * @param id      the contact id
     * @param contact the contact information
     * @return the updated contact
     * @throws ContactNotFoundException when the contact does not exists
     */
    @PutMapping(path = "/{id:^\\d+$}")
    public ResponseEntity<Contact> updateContact(@PathVariable final Long id, @RequestBody final Contact contact)
            throws ContactNotFoundException {
        final Optional<Contact> foundContact = contactRepository.findById(id);

        if (foundContact.isPresent()) {
            if (contact.getId() != null && (foundContact.get().getId() == contact.getId())) {
                final Contact updatedContact = contactRepository.save(contact);
                return new ResponseEntity<>(updatedContact, HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        throw new ContactNotFoundException(String.format("Contact with id %d does not exists", id));
    }

    // Delete Mappings
    // -----------------------------------------------------------------------------------------

    /**
     * Deletes an existing {@link Contact}
     *
     * @param id the contact id
     * @return HttpStatus OK
     * @throws ContactNotFoundException when the contact does not exists
     */
    @DeleteMapping(path = "/{id:^\\d+$}")
    public ResponseEntity<Contact> deleteContact(@PathVariable final Long id) throws ContactNotFoundException {
        final Optional<Contact> contact = contactRepository.findById(id);

        if (contact.isPresent()) {
            contactRepository.delete(contact.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }

        throw new ContactNotFoundException(String.format("Contact with id %d does not exists", id));
    }

}
