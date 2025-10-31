import { useState } from 'react';
import TaskCardAPI from './TaskCardAPI.jsx';

export default function TaskBoardUI({ tasks, onCreate, onUpdate, onDelete }) {
  const [title, setTitle] = useState('');
  function add() {
    if (!title.trim()) return;
    onCreate(title.trim());
    setTitle('');
  }

  return (
    <div>
      <div className="flex gap-2 mb-4">
        <input value={title} onChange={(e) => setTitle(e.target.value)} placeholder="New task title" className="flex-1 p-2 border rounded" />
        <button onClick={add} className="py-2 px-4 rounded bg-indigo-600 text-white">Add</button>
      </div>
      <div className="grid grid-cols-3 gap-4">
        {['TO_DO', 'IN_PROGRESS', 'DONE'].map((col) => (
          <div key={col} className="bg-gray-50 p-3 rounded">
            <h4 className="font-semibold mb-2">{col.replace('_', ' ').toLowerCase()}</h4>
            <div className="space-y-2">
              {tasks.filter((t) => t.status === col).map((t) => (
                <TaskCardAPI key={t.taskId} task={t} onUpdate={onUpdate} onDelete={onDelete} />
              ))}
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}