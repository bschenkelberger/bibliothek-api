package de.praktikant.bibliothek.api.model.enums;

/**
 * @author Bjoern Schenkelberger, Postbank Systems AG
 */
public enum Fsk {
    FSK_0("0"),
    FSK_6("6"),
    FSK_12("12"),
    FSK_16("16"),
    FSK_18("18");

    private String id;

    Fsk(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public static Fsk getFromId(String id) {
        Fsk result = null;

        if (id != null) {
            for (Fsk fsk : values()) {
                if (fsk.getId().equals(id)) {
                    result = fsk;
                    break;
                }
            }
        }

        return result;
    }
}
