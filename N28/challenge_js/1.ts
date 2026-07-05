// Challenge input: OOP Architecture
class Task {
  id: number;
  title: string;
  priority: "low" | "medium" | "high";
  completed: boolean;
  tags: string[];

  constructor(
    id: number,
    title: string,
    priority: "low" | "medium" | "high" = "medium"
  ) {
    this.id = id;
    this.title = title;
    this.priority = priority;
    this.completed = false;
    this.tags = [];
  }

  complete(): void {
    this.completed = true;
  }

  addTag(tag: string): void {
    this.tags.push(tag);
  }
}

class TaskManager {
  tasks: Task[];
  nextId: number;

  constructor() {
    this.tasks = [];
    this.nextId = 1;
  }

  addTask(title: string, priority: "low" | "medium" | "high" = "medium"): Task {
    const task = new Task(this.nextId, title, priority);
    this.tasks.push(task);
    this.nextId += 1;
    return task;
  }

  completeTask(taskId: number): boolean {
    const task = this.tasks.find((task) => task.id === taskId);

    if (!task) {
      return false;
    }

    task.complete();
    return true;
  }

  tagTask(taskId: number, tag: string): boolean {
    const task = this.tasks.find((task) => task.id === taskId);

    if (!task) {
      return false;
    }

    task.addTag(tag);
    return true;
  }

  getPendingByPriority(priority: "low" | "medium" | "high"): Task[] {
    return this.tasks.filter(
      (task) => !task.completed && task.priority === priority
    );
  }

  completionRate(): number {
    if (this.tasks.length === 0) {
      return 0;
    }

    const completedCount = this.tasks.filter((task) => task.completed).length;
    return completedCount / this.tasks.length;
  }
}

const manager = new TaskManager();

manager.addTask("Write report", "high");
manager.addTask("Clean desk", "low");
manager.addTask("Review PR", "high");

manager.completeTask(1);
manager.tagTask(3, "urgent");

console.log(manager.getPendingByPriority("high"));
console.log(manager.completionRate());
