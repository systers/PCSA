package com.peacecorps.pcsa.reporting;

/**
 * @author Buddhiprabha Erabadda
 *
 * Holds contact information for a particular location
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

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getPcmoContact() {
        return pcmoContact;
    }

    public void setPcmoContact(String pcmoContact) {
        this.pcmoContact = pcmoContact;
    }

    public String getSsmContact() {
        return ssmContact;
    }

    public void setSsmContact(String ssmContact) {
        this.ssmContact = ssmContact;
    }

    public String getSarlContact() {
        return sarlContact;
    }

    public void setSarlContact(String sarlContact) {
        this.sarlContact = sarlContact;
    }
}
