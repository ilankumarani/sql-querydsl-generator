package xio.ilan.sql.query.dsl;

import lombok.Builder;

import javax.annotation.processing.Generated;

/**
 * BUsers is a Querydsl bean type
 */
@SuppressWarnings("this-escape")
@Generated("com.querydsl.codegen.BeanSerializer")
@Builder
public class BUsers {

    public BUsers() {
    }

    public BUsers(Boolean active, String email, String fullname, Long id, String password, java.sql.Timestamp registeredat, String username) {
        this.active = active;
        this.email = email;
        this.fullname = fullname;
        this.id = id;
        this.password = password;
        this.registeredat = registeredat;
        this.username = username;
    }

    private Boolean active;

    private String email;

    private String fullname;

    private Long id;

    private String password;

    private java.sql.Timestamp registeredat;

    private String username;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public java.sql.Timestamp getRegisteredat() {
        return registeredat;
    }

    public void setRegisteredat(java.sql.Timestamp registeredat) {
        this.registeredat = registeredat;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
         return "active = " + active + ", email = " + email + ", fullname = " + fullname + ", id = " + id + ", password = " + password + ", registeredat = " + registeredat + ", username = " + username;
    }

}

