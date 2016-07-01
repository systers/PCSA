package com.peacecorps.pcsa.get_help_now;

/**
 * Holds contact information for a particular location
 *
 * @author Buddhiprabha Erabadda
 * @since 07-08-2015
 */
public class LocationDetails {
    private String locationName;    // name of the current location of the volunteer
    private String pcmoContact;     //contact number of Peace Corps Medical Officer
    private String ssmContact;      //contact number of Safety and Security Manager
    private String sarlContact;     //contact number of Sexual Assault Response Liason

    public LocationDetails(String locationName, String pcmo_contact, String ssm_contact, String sarl_contact){
        this.locationName = locationName;
        this.pcmoContact = pcmo_contact;
        this.ssmContact = ssm_contact;
        this.sarlContact = sarl_contact;
    }

    /**
     * @return name of the current location of the volunteer
     */
    public String getLocationName() {
        return locationName;
    }

    /**
     * @param locationName set name of the current location of the volunteer
     */
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    /**
     * @return contact number of Peace Corps Medical Officer
     */
    public String getPcmoContact() {
        return pcmoContact;
    }

    /**
     * @param pcmoContact set contact number of Peace Corps Medical Officer
     */
    public void setPcmoContact(String pcmoContact) {
        this.pcmoContact = pcmoContact;
    }

    /**
     * @return contact number of Safety and Security Manager
     */
    public String getSsmContact() {
        return ssmContact;
    }

    /**
     * @param ssmContact set contact number of Safety and Security Manager
     */
    public void setSsmContact(String ssmContact) {
        this.ssmContact = ssmContact;
    }

    /**
     * @return contact number of Sexual Assault Response Liason
     */
    public String getSarlContact() {
        return sarlContact;
    }

    /**
     * @param sarlContact set contact number of Sexual Assault Response Liason
     */
    public void setSarlContact(String sarlContact) {
        this.sarlContact = sarlContact;
    }
}
