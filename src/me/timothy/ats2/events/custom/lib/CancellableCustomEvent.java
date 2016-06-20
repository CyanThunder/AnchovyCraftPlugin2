package me.timothy.ats2.events.custom.lib;


/**
 * Created by CyanThunderMC on 6/20/2016.
 */
public class CancellableCustomEvent extends CustomEvent {

    private boolean cancelled;

    public CancellableCustomEvent(String eventName) {
        super(eventName);
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public boolean isCancelled() {
        return cancelled;
    }
}
