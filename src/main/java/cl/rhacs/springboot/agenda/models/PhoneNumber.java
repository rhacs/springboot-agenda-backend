package cl.rhacs.springboot.agenda.models;

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

@Entity
@Table(name = "phone_numbers")
public class PhoneNumber {

    // Attributes
    // -----------------------------------------------------------------------------------------

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "country_code")
    private int countryCode;

    @Column(name = "region_code")
    private int regionCode;

    @Column(name = "number", unique = true)
    private Long number;

    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH }, fetch = FetchType.LAZY, targetEntity = Contact.class)
    @JoinColumn(name = "contact_id", nullable = false)
    private Contact contact;

    // Constructors
    // -----------------------------------------------------------------------------------------

    /**
     * Creates a new and empty {@link PhoneNumber}
     */
    public PhoneNumber() {

    }

    /**
     * Creates a new {@link PhoneNumber} given a number and a {@link Contact}
     *
     * @param number  the number to set
     * @param contact the contact to set
     */
    public PhoneNumber(final Long number, final Contact contact) {
        this.number = number;
        this.contact = contact;
    }

    /**
     * Creates a new {@link PhoneNumber} given a country code, a region code (for
     * those countries with more than one region/district), a number and a
     * {@link Contact}
     *
     * @param countryCode the country code to set
     * @param regionCode  the region code to set
     * @param number      the number to set
     * @param contact     the contact to set
     */
    public PhoneNumber(final int countryCode, final int regionCode, final Long number, final Contact contact) {
        this(number, contact);
        this.countryCode = countryCode;
        this.regionCode = regionCode;
    }

    // Getters
    // -----------------------------------------------------------------------------------------

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the country's code
     */
    public int getCountryCode() {
        return countryCode;
    }

    /**
     * @return the region's code
     */
    public int getRegionCode() {
        return regionCode;
    }

    /**
     * @return the number
     */
    public Long getNumber() {
        return number;
    }

    /**
     * @return the contact
     */
    public Contact getContact() {
        return contact;
    }

    // Setters
    // -----------------------------------------------------------------------------------------

    /**
     * @param countryCode the country code to set
     */
    public void setCountryCode(final int countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * @param regionCode the region code to set
     */
    public void setRegionCode(final int regionCode) {
        this.regionCode = regionCode;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(final Long number) {
        this.number = number;
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
        result = prime * result + ((number == null) ? 0 : number.hashCode());

        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        final PhoneNumber other = (PhoneNumber) obj;

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

        if (number == null) {
            if (other.number != null)
                return false;
        } else if (!number.equals(other.number))
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "PhoneNumber [id=" + id + ", countryCode=" + countryCode + ", regionCode=" + regionCode + ", number="
                + number + "]";
    }

}
