/*
 * Author:      Patrick Bartholomew
 * Date:        2/24/2016
 * Modified:    2/27/2016
 * Description: This bean is repsonsible for getters/setters on the Adminstrator's task list.
 *
 */

package secure.unite.beans;

import java.io.Serializable;
import java.util.List;
import secure.unite.entities.Task;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import secure.unite.beans.SessionState;

@ManagedBean(name = "taskBean")
@ViewScoped
public class adminTaskListBean implements Serializable {

    private List<Task> taskList;
    private Task selectedTask;
    private List<Task> selectedTasks;
    
    @ManagedProperty("#{taskServices}")
    private taskServices service;
    

    @PostConstruct
    public void init() {
        // generate the list for the xhtml
        taskList = service.gatherAdminTasks();
    }
    
    public List<Task> getTaskList() {
        return taskList;
    }

    public void setService(taskServices service) {
        this.service = service;
    }

    public Task getSelectedTask() {
        return selectedTask;
    }

    public void setSelectedTask(Task selectedTask) {
        this.selectedTask = selectedTask;
    }

    public List<Task> getSelectedTasks() {
        return selectedTasks;
    }

    public void setSelectedTasks(List<Task> selectedTasks) {
        this.selectedTasks = selectedTasks;
    }
}
