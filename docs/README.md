# Pompompurin User Guide

![Pompompurin Screenshot](Ui.png)

Pompompurin is a friendly task management chatbot that helps you keep track of your todos, deadlines, and events. It features a simple graphical interface with error highlighting and supports natural task management through text commands.

## Quick Start

1. Ensure you have Java 17 or above installed
2. Download the latest `Pompompurin.jar` from the releases page
3. Double-click the jar file to start the application
4. Type commands in the input box and press Enter or click Send
5. Refer to the Features section below for available commands

---

## Features

### Viewing help: `help`

Shows all available commands and their usage.

**Format:** `help`

**Example:**
```
help
```

---

### Adding a todo task: `todo`

Adds a simple task without any date.

**Format:** `todo DESCRIPTION`

**Example:**
```
todo read book
todo buy groceries
```

**Expected output:**
```
Gotcha. I've added this task:
  [T][ ] read book
Now you have 1 tasks in the list.
```

---

### Adding a deadline: `deadline`

Adds a task with a due date.

**Format:** `deadline DESCRIPTION /by DATE`
- `DATE` must be in `dd-mm-yyyy` format (e.g., 25-12-2026)

**Example:**
```
deadline submit report /by 15-03-2026
deadline return book /by 28-02-2026
```

**Expected output:**
```
Gotcha. I've added this task:
  [D][ ] submit report (by: 15 Mar 2026)
Now you have 2 tasks in the list.
```

---

### Adding an event: `event`

Adds an event with a start and end date.

**Format:** `event DESCRIPTION /from START_DATE /to END_DATE`
- Both dates must be in `dd-mm-yyyy` format
- Start date must be before end date

**Example:**
```
event conference /from 01-06-2026 /to 03-06-2026
event vacation /from 10-07-2026 /to 20-07-2026
```

**Expected output:**
```
Gotcha. I've added this task:
  [E][ ] conference (from: 1 Jun 2026 to: 3 Jun 2026)
Now you have 3 tasks in the list.
```

---

### Listing all tasks: `list`

Shows all tasks in your list.

**Format:** `list`

**Example:**
```
list
```

**Expected output:**
```
Here are the tasks in your list:
1. [T][ ] read book
2. [D][ ] submit report (by: 15 Mar 2026)
3. [E][ ] conference (from: 1 Jun 2026 to: 3 Jun 2026)
```

---

### Marking a task as done: `mark`

Marks a task as completed.

**Format:** `mark TASK_NUMBER`
- `TASK_NUMBER` refers to the index shown in the `list` command

**Example:**
```
mark 1
```

**Expected output:**
```
Nice! I've marked this task as done:
  [T][X] read book
```

---

### Unmarking a task: `unmark`

Marks a completed task as not done.

**Format:** `unmark TASK_NUMBER`

**Example:**
```
unmark 1
```

**Expected output:**
```
OK, I've marked this task as not done yet:
  [T][ ] read book
```

---

### Deleting a task: `delete`

Removes a task from your list.

**Format:** `delete TASK_NUMBER`

**Example:**
```
delete 2
```

**Expected output:**
```
Noted. I've removed this task:
  [D][ ] submit report (by: 15 Mar 2026)
Now you have 2 tasks in the list.
```

---

### Finding tasks: `find`

Searches for tasks containing a keyword.

**Format:** `find KEYWORD`
- Search is case-insensitive

**Example:**
```
find book
find conference
```

**Expected output:**
```
Here are the matching tasks in your list:
1. [T][ ] read book
```

---

### Exiting the application: `bye`

Closes the application.

**Format:** `bye`

**Example:**
```
bye
```

---

## Command Summary

| Command | Format | Example |
|---------|--------|---------|
| Help | `help` | `help` |
| Add Todo | `todo DESCRIPTION` | `todo read book` |
| Add Deadline | `deadline DESCRIPTION /by DATE` | `deadline submit report /by 15-03-2026` |
| Add Event | `event DESCRIPTION /from DATE /to DATE` | `event meeting /from 01-06-2026 /to 03-06-2026` |
| List | `list` | `list` |
| Mark | `mark TASK_NUMBER` | `mark 1` |
| Unmark | `unmark TASK_NUMBER` | `unmark 1` |
| Delete | `delete TASK_NUMBER` | `delete 2` |
| Find | `find KEYWORD` | `find book` |
| Exit | `bye` | `bye` |

---

## Error Messages

Pompompurin will display error messages in **red** when:
- Command format is incorrect
- Required parameters are missing
- Date format is invalid (must be `dd-mm-yyyy`)
- Task number is invalid or out of range
- Start date is not before end date for events

**Example error:**
```
Beeboo =( Date format: dd-mm-yyyy
```

---

## Data Storage

- Tasks are automatically saved to `./data/pompompurin.txt`
- Data is saved after every change
- The file is created automatically if it doesn't exist
- You can manually edit the file, but ensure the format is correct to avoid data corruption