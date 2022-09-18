package com.skypro;

public class ValidatorService {

    private static final String VALIDATE_SYMBOLS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_0123456789";

    private ValidatorService() {

    }

    public static boolean validate(String login, String password, String confirmPassword) {
        try {
            validateParams(login, password, confirmPassword);
            return true;
        } catch (WrongLoginException | WrongPasswordException exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }

    private static void validateParams(String login, String password, String confirmPassword) throws WrongLoginException, WrongPasswordException {
        if (login == null || login.length() > 20) {
            throw new WrongLoginException("The length of login should be, at least 1 character and less, than 20 characters");
        }
        if (password == null || confirmPassword == null || password.length() >= 20 || confirmPassword.length() >= 20) {
            throw new WrongPasswordException("The length of password should be, at least 1 character and less, than 21 characters");
        }

        for (int i = 0; i < login.length(); i++) {
            if (!VALIDATE_SYMBOLS.contains(String.valueOf(login.charAt(i)))) {
                throw new WrongLoginException("Please, use only latin alphabet, _ and numbers");
            }
        }

        for (int i = 0; i < password.length(); i++) {
            if (!VALIDATE_SYMBOLS.contains(String.valueOf(password.charAt(i)))) {
                throw new WrongPasswordException("Please, use only latin alphabet, _ and numbers");
            }
        }

        if (!password.equals(confirmPassword)) {
            throw new WrongPasswordException("The confirm password should be the same");

        }



    }
}