package com.gmail.genadyms.web.view;


public enum ConstantsValue {
    FIRST_NAME("First name"),
    LAST_NAME("Last name"),
    DIAGNOSIS("Diagnosis"),
    ADDRESS("Home address"),
    COMING_DATE("Coming"),
    LEAVING_DATE("Leaving"),
    WARD("Ward"),
    FORMAT_TEMPLATE("MM/dd/yyyy"),
    BUTTON_ADD("Add patient"),
    BUTTON_SAVE("Save patient "),
    BUTTON_CANCEL("Cancel"),
    BUTTON_UPDATE("Update patient"),
    TABLE_TITLE("table of patients"),
    MSG_VALIDATE(" has incorrect value!"),
    NEW_LINE("\r\n");

    private final String value;

    private ConstantsValue(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}