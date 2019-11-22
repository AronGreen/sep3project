package models.Arons;

import java.io.Serializable;

public class SampleFormModel implements Serializable {
    private String name;
    private String message;

    public String getName() {
        return name;
    }

    public SampleFormModel(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
