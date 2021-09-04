package com.epam.taxi.db.entity;

import java.util.Objects;

public class Account extends Entity {
    private static final long serialVersionUID = 8466257860784246236L;

    private String login;
    private String email;
    private String password;
    private boolean role;
    private long phoneNumber;

    private Account() {
        role = false;
    }

    public static Account createAccount() {
        return new Account();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public boolean getRole() {
        return role;
    }

    public void updateRole(boolean role) {
        this.role = role;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return role == account.role && phoneNumber == account.phoneNumber && login.equals(account.login) && email.equals(account.email) && password.equals(account.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, email, password, role, phoneNumber);
    }

    @Override
    public String toString() {
        return "Account{" +
                "login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}