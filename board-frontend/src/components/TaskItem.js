import React from "react";

function TaskItem({ task, onUpdateStatus, onDelete }) {
  return (
    <div className="task-item">
      <div>
        <h4>{task.name}</h4>
        <p><strong>Assigned to:</strong> {task.assignedTo || "Unassigned"}</p>
        <p>Status: {task.status}</p>
      </div>
      <div>
        <button onClick={() => onUpdateStatus(task.id, "Completed")} className="btn-success">
          Mark Done
        </button>
        <button onClick={() => onDelete(task.id)} className="btn-danger">
          Delete
        </button>
      </div>
    </div>
  );
}

export default TaskItem;
