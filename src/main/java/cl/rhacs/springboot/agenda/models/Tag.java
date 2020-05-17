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
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "tags")
public class Tag {

    // Attributes
    // -----------------------------------------------------------------------------------------

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @NotBlank
    @NotEmpty
    @Size(min = 4, max = 25)
    @Column(name = "name", unique = true)
    private String name;

    @JsonIgnore
    @ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH }, fetch = FetchType.LAZY, targetEntity = Contact.class)
    private Set<Contact> contacts;

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
     * Creates a new and empty {@link Tag}
     */
    public Tag() {

    }

    /**
     * Creates a new {@link Tag} given a name
     *
     * @param name the name to set
     */
    public Tag(final String name) {
        this.name = name;
    }

    // Getters
    // -----------------------------------------------------------------------------------------

    /**
     * @return the tag id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the tag name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the contacts that have the tag
     */
    public Set<Contact> getContacts() {
        return contacts;
    }

    /**
     * @return the creation date
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * @return the date at which the tag was last updated
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    // Setters
    // -----------------------------------------------------------------------------------------

    /**
     * @param name the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @param contacts the contacts to set
     */
    public void setContacts(final Set<Contact> contacts) {
        this.contacts = contacts;
    }

    // Inheritances (Object)
    // -----------------------------------------------------------------------------------------

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());

        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        final Tag other = (Tag) obj;

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

        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "Tag [id=" + id + ", name=" + name + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
    }

}
