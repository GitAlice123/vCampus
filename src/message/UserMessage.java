package view.message;

import view.Login.User;

public class UserMessage {
    private User user;
    private User[] users;

    public User getUser() {
        return user;
    }

    public User[] getUsers() {
        return users;
    }

    public UserMessage(User user) {
        this.user = user;
    }

    public UserMessage(User[] users) {
        this.users = users;
    }

    public UserMessage() {
    }
}
