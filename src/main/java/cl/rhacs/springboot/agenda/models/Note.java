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

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "notes")
public class Note {

    // Attributes
    // -----------------------------------------------------------------------------------------

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(min = 5, max = 250)
    @Column(name = "content", unique = true)
    private String content;

    @JsonIgnore
    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH }, fetch = FetchType.LAZY, targetEntity = Contact.class)
    @JoinColumn(name = "contact_id", nullable = false)
    private Contact contact;

    @JsonIgnore
    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @JsonIgnore
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
     * Creates a new {@link Note} given a content and a {@link Contact}
     *
     * @param content the content to set
     * @param contact the contact to set
     */
    public Note(final String content, final Contact contact) {
        this.content = content;
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
     * @return the content
     */
    public String getContent() {
        return content;
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
     * @param content the content to set
     */
    public void setContent(final String content) {
        this.content = content;
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

        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((content == null) ? 0 : content.hashCode());

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

        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;

        if (content == null) {
            if (other.content != null)
                return false;
        } else if (!content.equals(other.content))
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "Note [id=" + id + ", content=" + content + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
                + "]";
    }

}
