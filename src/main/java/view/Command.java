package view;

import model.value.StringValue;

public abstract class Command {
    private String key, desc;

    public String getKey() {
        return key;
    }

    public void setId(String id) {
        this.key = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Command(String id, String descr) {
        this.key = id;
        this.desc = descr;
    }

    public abstract void execute() throws Exception;


}
