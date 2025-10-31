import { useState, useEffect } from 'react';

export default function TaskCardAPI({ task, onUpdate, onDelete }) {
  const [editing, setEditing] = useState(false);
  const [title, setTitle] = useState(task.title);
  useEffect(() => setTitle(task.title), [task.title]);

  async function save() {
    await onUpdate({ ...task, title });
    setEditing(false);
  }

  return (
    <div className="bg-white p-3 rounded shadow-sm flex flex-col">
      {editing ? (
        <div>
          <input value={title} onChange={(e) => setTitle(e.target.value)} className="w-full p-1 border rounded" />
          <div className="mt-2 flex gap-2">
            <button onClick={save} className="py-1 px-2 rounded bg-green-600 text-white">Save</button>
            <button onClick={() => { setEditing(false); setTitle(task.title); }} className="py-1 px-2 rounded border">Cancel</button>
          </div>
        </div>
      ) : (
        <>
          <div className="flex justify-between items-start gap-2">
            <div className="font-medium">{task.title}</div>
            <div className="text-sm text-gray-500">{task.dueDate || ''}</div>
          </div>
          <div className="mt-3 flex gap-2">
            <select value={task.status} onChange={(e) => onUpdate({ ...task, status: e.target.value })} className="p-1 border rounded">
              <option value="TO_DO">To do</option>
              <option value="IN_PROGRESS">In progress</option>
              <option value="DONE">Done</option>
            </select>
            <button onClick={() => setEditing(true)} className="py-1 px-2 rounded border">Edit</button>
            <button onClick={() => onDelete(task.taskId)} className="py-1 px-2 rounded text-red-600 border">Delete</button>
          </div>
        </>
      )}
    </div>
  );
}