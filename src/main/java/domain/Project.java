package domain;

import java.util.ArrayList;
import java.util.List;

public class Project {
    
    private int id;
    private String name;
    private List<Task> tasks = new ArrayList<Task>();
    
    public Project() {
    
    }

    public Project(String name) {
        this.name = name;
    }

    public Project(String name, List<Task> tasks) {
        this.name = name;
        this.tasks = tasks;
    }

    public void addTask(Task taskAdded) {
        if (taskAdded == null) {
            throw new IllegalArgumentException("The argument cannot be null");
        }
        tasks.add(taskAdded);
    }

    public void removeTask(Task taskRemoved) {
        tasks.remove(taskRemoved);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((tasks == null) ? 0 : tasks.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Project other = (Project) obj;
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (tasks == null) {
            if (other.tasks != null)
                return false;
        } else if (!tasks.equals(other.tasks))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Project [id=" + id + ", name=" + name + ", tasks=" + tasks + "]";
    }

}
