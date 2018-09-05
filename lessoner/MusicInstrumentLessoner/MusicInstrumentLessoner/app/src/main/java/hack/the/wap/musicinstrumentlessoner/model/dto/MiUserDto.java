package hack.the.wap.musicinstrumentlessoner.model.dto;

import java.io.Serializable;

public class MiUserDto implements Serializable {
    private String email;
    private String password;
    private String name;

    /**
     * Create MiUserDto
     *
     * @param email    pk
     * @param password nn
     * @param name     nn
     */

    public MiUserDto(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MiUserDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
