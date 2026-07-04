# Challenge: Refactor a Task Manager from OOP to FP

**Difficulty:** Medium
**Time box:** 20–25 minutes (core) + 10 minutes (bonus)

## Goal

Refactor the OOP `TaskManager` below into a functional style: immutable data, pure functions, and explicit state — no hidden mutation anywhere.

## Starter Code (OOP)

```python
class Task:
    def __init__(self, id, title, priority="medium"):
        self.id = id
        self.title = title
        self.priority = priority
        self.completed = False
        self.tags = []

    def complete(self):
        self.completed = True

    def add_tag(self, tag):
        self.tags.append(tag)


class TaskManager:
    def __init__(self):
        self.tasks = []
        self.next_id = 1

    def add_task(self, title, priority="medium"):
        task = Task(self.next_id, title, priority)
        self.tasks.append(task)
        self.next_id += 1
        return task

    def complete_task(self, task_id):
        for task in self.tasks:
            if task.id == task_id:
                task.complete()
                return True
        return False

    def tag_task(self, task_id, tag):
        for task in self.tasks:
            if task.id == task_id:
                task.add_tag(tag)
                return True
        return False

    def get_pending_by_priority(self, priority):
        return [t for t in self.tasks if not t.completed and t.priority == priority]

    def completion_rate(self):
        if not self.tasks:
            return 0
        done = sum(1 for t in self.tasks if t.completed)
        return done / len(self.tasks)


# --- Usage ---
manager = TaskManager()
manager.add_task("Write report", "high")
manager.add_task("Clean desk", "low")
manager.add_task("Review PR", "high")

manager.complete_task(1)
manager.tag_task(3, "urgent")

print(manager.get_pending_by_priority("high"))
print(manager.completion_rate())
```

## Requirements

1. **Represent a task as immutable data.** No classes with mutable attributes that get changed in place.
2. **`add_task`, `complete_task`, and `tag_task` must all return a *new* collection of tasks** rather than mutating anything in place.
3. **`get_pending_by_priority` and `completion_rate` should become pure functions** that take the task list (or state) as an argument, not methods on a stateful object.
4. **The "current state" (list of tasks + the next id counter) must live outside all these functions** — e.g. in the script's `__main__` scope — and be threaded manually from one call to the next.
5. Watch out for **every place mutation currently happens** — there are three: `complete()`, `add_tag()`, and the `.append()` calls inside `TaskManager`. All three need to go.

## Bonus (if time allows)

Implement a reducer:

```python
def apply_action(state, action):
    ...
```

where `action` is a dict like `{"type": "complete", "task_id": 3}`. Then rewrite the usage section as a `reduce()` over a list of actions instead of calling functions one by one. This is the same pattern behind Redux, Elm, and React's `useReducer`.

## Discussion Questions (for group review)

- Where does the "current state" live now, compared to the OOP version?
- What would happen if two different parts of the program held onto the *same* task list at different points in time — could they get out of sync? Why or why not?
- Did you use a `list` or a `tuple` for `tasks`? What about `tags` inside each `Task`? Does it matter?
- Is the `next_id` counter easy to forget when writing `add_task`? Why does it need to be bundled into the returned state?
- How does the bonus reducer pattern compare to the individual pure functions? What did you gain, if anything?

## Submission

Bring your refactored file to the group review. We'll compare a few different approaches, then walk through a reference solution together.