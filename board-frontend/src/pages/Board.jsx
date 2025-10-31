import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../hooks/useAuth.js';
import { projectService } from '../services/projectService.js';
import { taskService } from '../services/taskService.js';
import Header from '../components/Header.jsx';
import TaskBoardUI from '../components/TaskBoardUI.jsx';

export default function Board() {
  const navigate = useNavigate();
  const { user, logout } = useAuth();

  const [projects, setProjects] = useState([]);
  const [selectedProject, setSelectedProject] = useState(null);
  const [tasks, setTasks] = useState([]);
  const [assignedUser, setAssignedUser] = useState('');

  useEffect(() => {
    if (!user) {
      navigate('/login');
      return;
    }
    (async () => {
      const projs = await projectService.fetchAll();
      setProjects(projs);
      if (projs.length > 0) setSelectedProject(projs[0]);
      const t = await taskService.fetchAll();
      setTasks(t);
    })();
  }, [user, navigate]);

  function handleProjectSelect(e) {
    const id = e.target.value;
    const proj = projects.find((p) => p.projectId.toString() === id);
    setSelectedProject(proj);
  }

  async function handleAddTask(title) {
    if (!selectedProject) return;
    const newTask = {
      taskId: Date.now(),
      projectId: selectedProject.projectId,
      title,
      status: 'TO_DO',
      assignedTo: assignedUser || user.username,
    };
    await taskService.create(newTask);
    setTasks((t) => [...t, newTask]);
  }

  async function handleUpdateTask(updated) {
    setTasks((prev) => prev.map((t) => (t.taskId === updated.taskId ? updated : t)));
  }

  async function handleDeleteTask(taskId) {
    setTasks((prev) => prev.filter((t) => t.taskId !== taskId));
  }

  const filteredTasks = selectedProject
    ? tasks.filter((t) => t.projectId === selectedProject.projectId)
    : [];

  return (
    <div className="min-h-screen bg-gray-100 flex flex-col">
      <Header user={user} onLogout={logout} />

      <main className="flex-1 p-6 space-y-6">
        <div className="flex gap-4 items-center">
          <select
            onChange={handleProjectSelect}
            value={selectedProject?.projectId || ''}
            className="p-2 border rounded"
          >
            {projects.map((p) => (
              <option key={p.projectId} value={p.projectId}>
                {p.name}
              </option>
            ))}
          </select>

          <input
            type="text"
            placeholder="Assign to user..."
            value={assignedUser}
            onChange={(e) => setAssignedUser(e.target.value)}
            className="p-2 border rounded"
          />
        </div>

        <TaskBoardUI
          tasks={filteredTasks}
          onCreate={handleAddTask}
          onUpdate={handleUpdateTask}
          onDelete={handleDeleteTask}
        />
      </main>
    </div>
  );
}