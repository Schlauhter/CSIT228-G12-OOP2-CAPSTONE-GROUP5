package com.example.budgettracker.util;

import com.example.budgettracker.model.User;

public class Session {
    private static User currentUser = null;

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static int getCurrentUserId() {
        return (currentUser != null) ? currentUser.getUserId() : -1;
    }

    public static void logout() {
        currentUser = null;
    }
}
