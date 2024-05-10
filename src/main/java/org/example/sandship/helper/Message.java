package org.example.sandship.helper;

public enum Message {
    ERROR_PLAYER_NOT_FOUND("No such player found"),

    ERROR_WAREHOUSE_NOT_FOUND("No such warehouse found"),

    ERROR_MATERIAL_TYPE_NOT_FOUND("No such material type found"),

    ERROR_MATERIAL_TYPE_EXISTS("Material type already exists"),

    ERROR_PLAYER_EXISTS("Player with nickname already exists"),

    ERROR_PLAYER_MAX_WAREHOUSES("Maximum capacity of warehouses is 10"),

    ERROR_MATERIAL_TYPE_MAX_LIMIT("You can't add more materials than your max capacity"),

    ERROR_MATERIAL_TYPE_MAX_LIMIT_MOVE("You can't move more materials than you have"),

    ERROR_WAREHOUSE_ID_REQUIRED("warehouseId must not be blank");

    private final String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
