package cl.rhacs.springboot.agenda.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "contacts")
public class Contact {

    // Attributes
    // -----------------------------------------------------------------------------------------

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(min = 2, max = 50)
    @Column(name = "first_name")
    private String firstName;

    @Size(min = 2, max = 50)
    @Column(name = "last_name")
    private String lastName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "contact", targetEntity = PhoneNumber.class)
    private Set<PhoneNumber> phoneNumbers;

    @Email
    @Column(name = "email")
    private String email;

    @ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH }, fetch = FetchType.LAZY, targetEntity = Tag.class)
    @JoinTable(name = "contact_tag", joinColumns = @JoinColumn(name = "contact_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "contact", targetEntity = Note.class)
    private Set<Note> notes;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    // Constructors
    // -----------------------------------------------------------------------------------------

    /**
     * Creates a new and empty {@link Contact}
     */
    public Contact() {

    }

    /**
     * Creates a new {@link Contact} given a first name and a last name
     *
     * @param firstName the first name to set
     * @param lastName  the last name to set
     */
    public Contact(final String firstName, final String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Getters
    // -----------------------------------------------------------------------------------------

    /**
     * @return the contact's id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the contact's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return the contact's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return the list of phone numbers
     */
    public Set<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    /**
     * @return the contact's email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the tags
     */
    public Set<Tag> getTags() {
        return tags;
    }

    /**
     * @return the notes
     */
    public Set<Note> getNotes() {
        return notes;
    }

    /**
     * @return the creation date of the contact
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * @return the date at which the contact information was last changed
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    // Setters
    // -----------------------------------------------------------------------------------------

    /**
     * @param firstName the first name to set
     */
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /**
     * @param lastName the last name to set
     */
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /**
     * @param phoneNumbers the phone numbers to set
     */
    public void setPhoneNumbers(final Set<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    /**
     * @param email the email address to set
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * @param tags the tags to set
     */
    public void setTags(final Set<Tag> tags) {
        this.tags = tags;
    }

    /**
     * @param notes the notes to set
     */
    public void setNotes(final Set<Note> notes) {
        this.notes = notes;
    }

    // Inheritances (Object)
    // -----------------------------------------------------------------------------------------

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((notes == null) ? 0 : notes.hashCode());
        result = prime * result + ((phoneNumbers == null) ? 0 : phoneNumbers.hashCode());

        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        final Contact other = (Contact) obj;

        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;

        if (notes == null) {
            if (other.notes != null)
                return false;
        } else if (!notes.equals(other.notes))
            return false;

        if (phoneNumbers == null) {
            if (other.phoneNumbers != null)
                return false;
        } else if (!phoneNumbers.equals(other.phoneNumbers))
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "Contact [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", phoneNumbers="
                + phoneNumbers + ", email=" + email + ", tags=" + tags + ", notes=" + notes + ", createdAt=" + createdAt
                + ", updatedAt=" + updatedAt + "]";
    }

}
