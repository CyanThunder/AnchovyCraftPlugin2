package me.timothy.ats2.playerinfo.lib;

/**
 * Created by CyanThunderMC on 6/8/2016.
 */
public enum PlayerConfig {
    //FAKEs have to be handled separately.
    //[ENUM](PATH, TYPE, DEFAULT (OR) IS_FAKE)
    //[ENUM](PATH, TYPE, DEFAULT, IS_FAKE) (required for Boolean defaults)
    TAG("tag", Type.STRING, ""),
    HARDMODE("hardmode", Type.BOOLEAN, false),
    KILLS("playerkills", Type.INTEGER, 0),
    DEATHS("deaths", Type.INTEGER, 0),
    KDR(null, Type.DOUBLE, true),
    CHAT_COLOR("chatcolor", Type.STRING, "white"),
    FAKE("", Type.STRING, true);


    private String path;
    private Type type;
    private Object defualt = null;
    private boolean isFake = false;

    PlayerConfig(String path, Type type, Object defualt) {
        this.path = path;
        this.type = type;
        this.defualt = defualt;
    }

    PlayerConfig(String path, Type type, boolean isFAKE) {
        this.path = path;
        this.type = type;
        this.isFake = isFAKE;
    }

    PlayerConfig(String path, Type type, Object defualt, boolean isFAKE) {
        this.path = path;
        this.type = type;
        this.defualt = defualt;
        this.isFake = isFAKE;
    }

    public Type getType() {
        return type;
    }

    public String getPath() {
        return path;
    }

    public boolean isFake() {
        return isFake;
    }

    public Object getDefualt() {
        return defualt;
    }

    public PlayerConfig setPath(String path) {
        this.path = path;
        return this;
    }

    public PlayerConfig setType(Type type) {
        this.type = type;
        return this;
    }

    public PlayerConfig setFake(boolean fake) {
        this.isFake = fake;
        return this;
    }

    public PlayerConfig setDefualt(Object defualt) {
        this.defualt = defualt;
        return this;
    }

    public enum Type {
        STRING,
        BOOLEAN,
        INTEGER,
        DOUBLE,
        FLOAT,
    }
}
