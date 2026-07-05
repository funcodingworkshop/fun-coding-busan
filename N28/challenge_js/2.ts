// Solution: Functional Programming Architecture in TypeScript
type TPriority = "low" | "medium" | "high";

type TTask = {
    title: string;
    priority: TPriority;
    completed: boolean;
    tags: string[];
}

type TState = Array<TTask>;

let state: TState = [];

function addTask(state: TState, title: string, priority: TPriority): TState {
    const stateShallowCopy: TState = [...state];
    stateShallowCopy.push({ title, priority, completed: false, tags: [] });
    return stateShallowCopy;
}

function completeTask(state: TState, taskId: number): TState {
    const currentTask = state[taskId];
    if (!currentTask) return state;

    const stateShallowCopy = [...state];
    const taskShallowCopy = {...currentTask};
    taskShallowCopy.completed = true;
    stateShallowCopy[taskId] = taskShallowCopy;
    return stateShallowCopy;
}

function tagTask(state: TState, taskId: number, tag: string): TState {
    const currentTask = state[taskId];
    if (!currentTask) return state;

    const stateShallowCopy: TState = [...state];
    const taskShallowCopy: TTask = {...currentTask};
    const tagsShallowCopy: string[] = [...currentTask.tags];

    tagsShallowCopy.push(tag);
    taskShallowCopy.tags = tagsShallowCopy;
    stateShallowCopy[taskId] = taskShallowCopy;
    return stateShallowCopy;
}

function getPendingByPriority(state: TState, priority: TPriority): TTask[] {
    return state.filter(
      (task) => !task.completed && task.priority === priority
    );
}

function completionRate(state: TState): number {
    if (state.length === 0) {
      return 0;
    }

    const completedCount = state.filter((task) => task.completed).length;
    return completedCount / state.length;
}

state = addTask(state, "Write report", "high");
state = addTask(state, "Clean desk", "low");
state = addTask(state, "Review PR", "high");

state = completeTask(state, 0);
state = tagTask(state, 2, "urgent");

console.log('--- Current state ---');
console.log(state);

console.log('--- Get Pending By Priority ---');
console.log(getPendingByPriority(state, "high"));

console.log('--- Get Pending By Priority ---');
console.log(completionRate(state));
