class Task{
    private int id;
    private String title;
    private String priority;
    private boolean completed;

    public Task(int id, String title, String priority){
        this.id = id;
        this.title = title;
        this.priority = priority;
        this.completed = false;
    }

    public int getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public String getPriority(){
        return priority;
    }
    public boolean isCompleted(){
        return completed;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setCompleted(boolean completed){
        this.completed = completed;
    }
    public String toString(){
        return "Task ID: " +id+
                ", Title: " +title+
                ", Priority: " +priority+
                ", Status: " +(completed ? "Completed" : "Pending");
    }
}