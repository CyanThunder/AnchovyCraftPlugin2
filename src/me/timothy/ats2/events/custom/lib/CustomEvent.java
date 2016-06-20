package me.timothy.ats2.events.custom.lib;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by CyanThunderMC on 6/20/2016.
 */
public class CustomEvent extends Event {

    private String eventName;

    public CustomEvent(String eventName) {
        this.eventName = eventName;
    }

    @Override
    public String getEventName() {
        return eventName;
    }

    @Override
    public HandlerList getHandlers() {
        return null;
    }
}
