package models;

public abstract class Entity {
    private int id;
    private String createDate;
    private String updateDate;
    private String deleted;

    public Entity(int id, String createDate, String updateDate, String deleted) {
        this.id = id;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.deleted = deleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }
}
