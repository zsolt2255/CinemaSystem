package com.topin.services;

import com.topin.models.Costumer;
import com.topin.models.User;

public class StorageService {
    private static StorageService ourInstance = new StorageService();
    private User currentUser;
    private Costumer currentCostumer;

    public Costumer costumer() { return currentCostumer; }
    public void setCurrentCostumer(Costumer currentCostumer) { this.currentCostumer = currentCostumer; }

    /**
     * @return StorageService
     */
    public static StorageService getInstance() {
        return ourInstance;
    }

    /**
     * StorageService Constructor
     */
    private StorageService() { }

    /**
     * @param currentUser
     */
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * @return User
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * @return User
     */
    public User user() {
        return currentUser;
    }

    /**
     * @return boolean
     */
    public boolean isLogined() {
        return currentUser != null;
    }
}
