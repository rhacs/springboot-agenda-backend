package cl.rhacs.springboot.agenda.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "notes")
public class Note {

    // Attributes
    // -----------------------------------------------------------------------------------------

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(min = 5, max = 150)
    @Column(name = "note", unique = true)
    private String note;

    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH }, fetch = FetchType.LAZY, targetEntity = Contact.class)
    @JoinColumn(name = "contact_id", nullable = false)
    private Contact contact;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    // Constructors
    // -----------------------------------------------------------------------------------------

    /**
     * Creates a new and empty {@link Note}
     */
    public Note() {

    }

    /**
     * Creates a new {@link Note} given a note and a {@link Contact}
     *
     * @param note    the note to set
     * @param contact the contact to set
     */
    public Note(final String note, final Contact contact) {
        this.note = note;
        this.contact = contact;
    }

    // Getters
    // -----------------------------------------------------------------------------------------

    /**
     * @return the note id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the note
     */
    public String getNote() {
        return note;
    }

    /**
     * @return the contact
     */
    public Contact getContact() {
        return contact;
    }

    /**
     * @return the createdAt
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * @return the updatedAt
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    // Setters
    // -----------------------------------------------------------------------------------------

    /**
     * @param note the note to set
     */
    public void setNote(final String note) {
        this.note = note;
    }

    /**
     * @param contact the contact to set
     */
    public void setContact(final Contact contact) {
        this.contact = contact;
    }

    // Inheritances (Object)
    // -----------------------------------------------------------------------------------------

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((contact == null) ? 0 : contact.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((note == null) ? 0 : note.hashCode());

        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        final Note other = (Note) obj;

        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        if (contact == null) {
            if (other.contact != null)
                return false;
        } else if (!contact.equals(other.contact))
            return false;

        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;

        if (note == null) {
            if (other.note != null)
                return false;
        } else if (!note.equals(other.note))
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "Note [id=" + id + ", note=" + note + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
    }

}
