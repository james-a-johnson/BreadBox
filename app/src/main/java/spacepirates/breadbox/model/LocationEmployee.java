package spacepirates.breadbox.model;

public class LocationEmployee extends BasicUser {

    private Location location;

    /**
     * Constructor that takes in a type. User for calls from below by manager class.
     * Assumes a valid type is passed in. Should only be initialized to Location Manager,
     * or Location Employee.
     *
     * @param user
     * @param t
     * @param locus
     * @param pass
     */
    public LocationEmployee(String user, UserType t, Location locus, String pass) {
            super(user, t, pass);
        location = locus;
    }

    /**
     * Constructor for Location Employee, type is set to UserType.LOCATION_EMPLOYEE.
     *
     * @param user
     * @param locus
     * @param pass
     */
    public LocationEmployee(String user, Location locus, String pass) {
        super(user, UserType.LOCATION_EMPLOYEE, pass);
        location = locus;
    }


    public boolean addItem(DonationItem item) {
        return false;
    }

    public DonationItem sellItem(DonationItem item) {
        return null;
    }

    public int getStatistics() {
        return 0;
    }
}