// src/api/tasks.js
const API_URL = "http://localhost:8080/api/tasks"; // update per backend

export const getTasks = async (projectId) => {
  const response = await fetch(`${API_URL}?projectId=${projectId}`);
  return response.json();
};

export const addTask = async (data) => {
  const response = await fetch(API_URL, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  });
  return response.json();
};

export const updateTask = async (id, data) => {
  const response = await fetch(`${API_URL}/${id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  });
  return response.json();
};

export const deleteTask = async (id) => {
  const response = await fetch(`${API_URL}/${id}`, { method: "DELETE" });
  return response.json();
};
