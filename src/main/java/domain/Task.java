package domain;

import java.util.GregorianCalendar;

public class Task {
    
    private int id;
    private String name;
    private boolean isFinished;
    private GregorianCalendar date;
    
    public Task() {
    	
    }
    
    public Task(String name) {
        this.name = name;

    }
    
    public Task(String name, boolean isFinished, GregorianCalendar date) {
        this.name = name;
        this.isFinished = isFinished;
        this.date = date;
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
    
    public boolean isFinished() {
        return isFinished;
    }
	
    public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}
    public GregorianCalendar getDate() {
        return date;
    }
    
    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + id;
        result = prime * result + (isFinished ? 1231 : 1237);
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Task other = (Task) obj;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (id != other.id)
            return false;
        if (isFinished != other.isFinished)
            return false;
        if (name == null) {
            if (other.name != null)
            return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Task [id=" + id + ", name=" + name + ", isFinished=" + isFinished + ", date=" + date + "]";
    }
    
}
