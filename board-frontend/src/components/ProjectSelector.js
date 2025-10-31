import React, { useState, useEffect } from "react";
import { getProjects, createProject } from "../api/projects";

function ProjectSelector({ onSelect }) {
  const [projects, setProjects] = useState([]);
  const [newProject, setNewProject] = useState("");

  useEffect(() => {
    setProjects(getProjects());
  }, []);

  const handleAdd = () => {
    if (!newProject.trim()) return;
    const project = createProject(newProject);
    setProjects([...projects, project]);
    setNewProject("");
  };

  return (
    <div className="project-selector">
      <select onChange={(e) => onSelect(e.target.value)}>
        <option value="">Select Project</option>
        {projects.map((p) => (
          <option key={p.id} value={p.id}>
            {p.name}
          </option>
        ))}
      </select>
      <input
        type="text"
        placeholder="New Project"
        value={newProject}
        onChange={(e) => setNewProject(e.target.value)}
      />
      <button onClick={handleAdd}>Add</button>
    </div>
  );
}

export default ProjectSelector;
