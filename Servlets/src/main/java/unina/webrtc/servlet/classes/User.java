package unina.webrtc.servlet.classes;

public class User {
    private String name;
    private String surname;
    private String nickname;
    private String email;

    public User() {

    }

    public User(String name, String surname, String nickname, String email) {
        this.name = name;
        this.surname = surname;
        this.nickname = nickname;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}