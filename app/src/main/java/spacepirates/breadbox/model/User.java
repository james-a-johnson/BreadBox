package spacepirates.breadbox.model;

/**
 * Extra layer of abstraction for organizing different types of users
 */
public abstract class User {
    private final String username;
    private final UserType type;

    User (String username, UserType type) {
        this.username = username;
        this.type = type;
    }

    String getUsername() {
        return username;
    }

    UserType getType() {
        return type;
    }
}
