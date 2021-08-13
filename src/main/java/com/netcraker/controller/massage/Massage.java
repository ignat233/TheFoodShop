/*
 * Copyright
 */

package com.netcraker.controller.massage;

/**
 * Enum for controller messages.
 *
 * @since 0.0.1
 */
public enum Massage {

    /**
     * Massage for ProductController.
     */
    PRODUCT_NOT_ADD("Такой продукт уже существует"),

    /**
     * Massage for ProductController.
     */
    INCORRECT_PRODUCT("Назавние продукта введено некоректно"),

    /**
     * Massage for UserController.
     */
    LOGIN_ERROR("Пользователь с таким логином уже существует"),

    /**
     * Massage for UserController.
     */
    PASSWORD_MASSAGE("Пароль изменен");

    /**
     * Massage field.
     */
    private final String massage;

    /**
     * Private constructor for prohibit to create an instance.
     *
     * @param massage Massage
     */
    Massage(final String massage) {
        this.massage = massage;
    }

    /**
     * Getter.
     *
     * @return Massage
     */
    public String getMassage() {
        return this.massage;
    }
}
