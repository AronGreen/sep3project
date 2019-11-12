package models;

import java.util.Calendar;

public abstract class Entity {
    private int id;
    private Calendar createDate;
    private Calendar updateDate;
    private Calendar deleted;

    public Entity(int id, Calendar createDate, Calendar updateDate, Calendar deleted) {
        this.id = id;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.deleted = deleted;
    }

    public int getId() {
        return id;
    }

    public Calendar getCreateDate() {
        return createDate;
    }

    public Calendar getUpdateDate() {
        return updateDate;
    }

    public Calendar getDeleted() {
        return deleted;
    }
}
