import java.util.*;

public class TaskManager {
    static List<Task> taskList = new ArrayList<>();
    static LinkedList<Task> recentTasks = new LinkedList<>();
    static Stack<Task> deletedTasks = new Stack<>();
    static Vector<Task> backupTasks = new Vector<>();

    static Scanner s = new Scanner(System.in);
    public static void main(String[] args) {

        while(true){

            System.out.println("\n=== PERSONAL TASK MANAGEMENT SYSTEM ===");
            System.out.println("1. Add Task");
            System.out.println("2. View All Tasks");
            System.out.println("3. Update Tasks");
            System.out.println("4. Delete Tasks");
            System.out.println("5. Undo Last Delete");
            System.out.println("6. View Recent Tasks");
            System.out.println("7. Mark Task as completed");
            System.out.println("8. Delete Completed Tasks");
            System.out.println("9. Backup Tasks");
            System.out.println("10. Restore Backup");
            System.out.println("0. Exit");
            System.out.println("Enter Choice: ");

            int choice = s.nextInt();
            s.nextLine();

            switch(choice) {
                case 1 -> addTask();
                case 2 -> viewTask();
                case 3 -> updateTask();
                case 4 -> deleteTask();
                case 5 -> undoLastTask();
                case 6 -> viewRecentTask();
                case 7 -> markTaskAsComplete();
                case 8 -> deleteCompletedTask();
                case 9 -> backupTask();
                case 10 -> restoreTask();
                case 0 -> {
                    System.out.println("Exiting... Thank you for visiting");
                    return;
                }
                default -> System.out.println("Invalid choice!!!");
            }
        }
    }
    static void addTask(){
        System.out.println("Enter Task ID: ");
        int id = s.nextInt();
        s.nextLine();

        System.out.println("Enter Task Title: ");
        String title = s.nextLine();

        System.out.println("Enter Priority(High / Medium / Low): ");
        String priority = s.nextLine();

        Task task = new Task(id, title, priority);
        taskList.add(task);

        recentTasks.addFirst(task);
        if(recentTasks.size()>5){
            recentTasks.removeLast();
        }

        System.out.println("Task added successfully");
    }
    static void viewTask(){
        if(taskList.isEmpty()){
            System.out.println("No tasks available");
            return;
        }
        for(Task task : taskList){
            System.out.println(task);
        }
    }
    static void updateTask(){
        System.out.println("Enter Task ID to update: ");
        int id = s.nextInt();
        s.nextLine();
        for(Task task:taskList){
            if(task.getId()==id){
                System.out.println("Enter new title to update: ");
                task.setTitle(s.nextLine());
                System.out.println("Task Updated");
                return;
            }
            System.out.println("Task not found!!!");
        }
    }
    static void deleteTask(){
        System.out.println("Enter task ID to delete: ");
        int id = s.nextInt();
        Iterator<Task> it = taskList.iterator();
        while(it.hasNext()){
            Task task = it.next();
            if(task.getId()==id){
                deletedTasks.push(task);
                it.remove();
                System.out.println("Task deleted");
                return;
            }
        }
        System.out.println("Task not found!!!");
    }
    static void undoLastTask(){
      if(deletedTasks.isEmpty()){
          System.out.println("Nothin to undo!");
          return;
      }
      Task task = deletedTasks.pop();
      taskList.add(task);
      System.out.println("Undo successfully!!!");
    }
    static void viewRecentTask(){
      if(recentTasks.isEmpty()){
          System.out.println("No recent tasks");
          return;
      }
      for(Task task : recentTasks){
          System.out.println(task);
      }
    }
    static void markTaskAsComplete(){
        System.out.println("Enter task ID to mark completed: ");
        int id = s.nextInt();
        for(Task task : taskList){
            if(task.getId()==id){
                task.setCompleted(true);
                System.out.println("Task marked as completed!!!");
                return;
            }
            System.out.println("Task not found");
        }
    }
    static void deleteCompletedTask(){
      Iterator<Task> it = taskList.iterator();
      while(it.hasNext()){
          if(it.next().isCompleted()){
              it.remove();
          }
      }
        System.out.println("Completed tasks deleted");
    }
    static void backupTask(){
       backupTasks.clear();
       backupTasks.addAll(taskList);
        System.out.println("Backup created");
    }
    static void restoreTask(){
      taskList.clear();
      taskList.addAll(backupTasks);
        System.out.println("Backup restored!!!");
    }
}
