package me.timothy.ats2.chatlib.antispam.lib;

/**
 * Created by CyanThunderMC on 6/23/2016.
 */
public enum SpamReason {
    REPEATED_MESSAGE("REPEATED MESSAGE!"),
    TOO_FAST_MESSAGE("TOO FAST!"),
    REPEAT_BYPASS("MESSAGE TO SIMILAR TO PREVIOUS MESSAGE!"),
    CHAT_TIMEOUT("TIMED OUT."),
    FALSE("NO REASON, REPORT THIS TO SERVER ADMINISTRATOR.");

    private String displayMessage;

    SpamReason(String displayName) {
        this.displayMessage = displayName;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }
}
