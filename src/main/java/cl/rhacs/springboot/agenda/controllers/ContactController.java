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
     * Retrieve the details for the specified {@link Contact}
     *
     * @param id the id to look for
     * @return the contact detail, null if doesn't exists
     */
    @GetMapping(path = { "/{id:^\\d+$}" })
    public ResponseEntity<Contact> getContact(@PathVariable Long id) {
        Optional<Contact> contact = contactRepository.findById(id);

        if (contact.isPresent()) {
            return new ResponseEntity<>(contact.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Shows the list of {@link PhoneNumber}s owned by the contact
     *
     * @param id the id of the contact
     * @return a list of phone numbers
     */
    @GetMapping(path = { "/{id:^\\d+$}/phoneNumbers" })
    public ResponseEntity<List<PhoneNumber>> getContactPhoneNumbers(@PathVariable Long id) {
        Optional<Contact> contact = contactRepository.findById(id);

        if (contact.isPresent()) {
            Set<PhoneNumber> phoneNumbers = contact.get().getPhoneNumbers();

            if (!phoneNumbers.isEmpty())
                return new ResponseEntity<>(new ArrayList<>(phoneNumbers), HttpStatus.OK);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Shows the list of {@link Tag}s assigned to a {@link Contact}
     *
     * @param id the id of the contact
     * @return a list of tags
     */
    @GetMapping(path = { "/{id:^\\d+$}/tags" })
    public ResponseEntity<List<Tag>> getContactTags(@PathVariable Long id) {
        Optional<Contact> contact = contactRepository.findById(id);

        if (contact.isPresent()) {
            Set<Tag> tags = contact.get().getTags();

            if (!tags.isEmpty())
                return new ResponseEntity<>(new ArrayList<>(tags), HttpStatus.OK);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Shows the list of {@link Note}s created for the {@link Contact}
     *
     * @param id the id of the contact
     * @return a list of notes
     */
    @GetMapping(path = { "/{id:^\\d+$}/notes" })
    public ResponseEntity<List<Note>> getContactNotes(@PathVariable Long id) {
        Optional<Contact> contact = contactRepository.findById(id);

        if (contact.isPresent()) {
            Set<Note> notes = contact.get().getNotes();

            if (!notes.isEmpty())
                return new ResponseEntity<>(new ArrayList<>(contact.get().getNotes()), HttpStatus.OK);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
    public ResponseEntity<Contact> addContact(@RequestBody Contact contact) {
        Contact savedContact = contactRepository.save(contact);
        return new ResponseEntity<>(savedContact, HttpStatus.OK);
    }

    // Put Mappings
    // -----------------------------------------------------------------------------------------

    /**
     * Edit an existing {@link Contact}
     *
     * @param id      the id of the contact
     * @param contact the new information to set
     * @return the modified contact
     */
    @PutMapping(path = "/{id:^\\d+$}")
    public ResponseEntity<Contact> updateContact(@PathVariable Long id, @RequestBody Contact contact) {
        Optional<Contact> foundContact = contactRepository.findById(id);

        if (foundContact.isPresent()) {
            Contact updatedContact = contactRepository.save(contact);
            return new ResponseEntity<>(updatedContact, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Delete Mappings
    // -----------------------------------------------------------------------------------------

    /**
     * Delete a {@link Contact}
     *
     * @param id the contact's id
     * @return OK status if deletion was succesfull, NOT_FOUND otherwise
     */
    @DeleteMapping(path = "/{id:^\\d+$}")
    public ResponseEntity<Contact> deleteContact(@PathVariable Long id) {
        Optional<Contact> contact = contactRepository.findById(id);

        if (contact.isPresent()) {
            contactRepository.delete(contact.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
