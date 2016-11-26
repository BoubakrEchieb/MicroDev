package chess.microdev.devfest.tn.chess.models;

import java.net.URI;

/**
 * Created by be on 26/11/16.
 */

public class User {
    private String username;
    private String password;
    private String email;
    private URI image;

    public User() {
    }

    public User(String username, String password, String email, URI image) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public URI getImage() {
        return image;
    }

    public void setImage(URI image) {
        this.image = image;
    }
}
