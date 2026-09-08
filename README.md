# todoCLI

`todoCLI` is a simple, command-line based task tracker written in Java, built as a solution for the [Task Tracker project on roadmap.sh](https://roadmap.sh/projects/task-tracker). It allows you to create, update, delete, and manage the status of your tasks directly from your terminal. All tasks are persisted locally in a `Todo.json` file created in the directory where the application is run.
## Features

*   **Add new tasks**: Create tasks with a description.
*   **Update tasks**: Change the description of an existing task.
*   **Delete tasks**: Remove tasks by their ID.
*   **Manage Status**: Change a task's status between `TODO`, `IN_PROGRESS`, and `DONE`.
*   **List and Filter**: List all tasks or filter them by their current status.
*   **Persistent Storage**: Tasks are saved in a local `Todo.json` file.

## Prerequisites

*   Java 25 or higher
*   Apache Maven

## Installation

1.  Clone the repository to your local machine:
    ```sh
    git clone https://github.com/octaviomf/todocli.git
    ```

2.  Navigate to the project directory:
    ```sh
    cd todocli
    ```

3.  Build the project using Maven. This command will compile the source code and package it into a single, executable JAR file with all dependencies.
    ```sh
    mvn clean package
    ```
    This will create a runnable JAR file named `todoCLI-1-jar-with-dependencies.jar` in the `target/` directory.

## Usage

All commands are run from the terminal using the generated JAR file. The basic structure of a command is:

```sh
java -jar target/todoCLI-1-jar-with-dependencies.jar [command] [arguments...]
```

### Add a Task

To add a new task, use the `add` command followed by the task description in quotes.

```sh
java -jar target/todoCLI-1-jar-with-dependencies.jar add "My new task"
```

### List Tasks

To list all tasks, use the `list` command.

```sh
java -jar target/todoCLI-1-jar-with-dependencies.jar list
```
**Output:**
```
1: My new task [TODO]
```

You can also filter tasks by their status (`todo`, `in-progress`, `done`).

```sh
# List tasks that are 'done'
java -jar target/todoCLI-1-jar-with-dependencies.jar list done
```

### Update a Task Description

To change the description of an existing task, use the `update` command followed by the task ID and the new description.

```sh
java -jar target/todoCLI-1-jar-with-dependencies.jar update 1 "My updated task description"
```

### Change a Task's Status

To change a task's status, use the `mark-in-progress` or `mark-done` commands followed by the task ID.

```sh
# Mark task with ID 1 as 'in progress'
java -jar target/todoCLI-1-jar-with-dependencies.jar mark-in-progress 1
```

```sh
# Mark task with ID 1 as 'done'
java -jar target/todoCLI-1-jar-with-dependencies.jar mark-done 1
```

### Delete a Task

To delete a task, use the `delete` command followed by the task ID.

```sh
java -jar target/todoCLI-1-jar-with-dependencies.jar delete 1