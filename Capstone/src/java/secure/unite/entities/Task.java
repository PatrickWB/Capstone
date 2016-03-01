
package secure.unite.entities;

public class Task {
    
    private int adminTaskId;
    private int adminId;
    private int taskId;
    private String taskName;
    private String taskDesc;
    private String severityLevel;
    private String dateTimeCreated;
    private String dateTimeResoloved;
    private String resolution;
    private boolean resolved;

    public int getAdminTaskId() {
        return adminTaskId;
    }

    public void setAdminTaskId(int adminTaskId) {
        this.adminTaskId = adminTaskId;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public String getSeverityLevel() {
        return severityLevel;
    }

    public void setSeverityLevel(String severityLevel) {
        this.severityLevel = severityLevel;
    }

    public String getDateTimeCreated() {
        return dateTimeCreated;
    }

    public void setDateTimeCreated(String dateTimeCreated) {
        this.dateTimeCreated = dateTimeCreated;
    }

    public String getDateTimeResoloved() {
        return dateTimeResoloved;
    }

    public void setDateTimeResoloved(String dateTimeResoloved) {
        this.dateTimeResoloved = dateTimeResoloved;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public boolean isResolved() {
        return resolved;
    }

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }

}
