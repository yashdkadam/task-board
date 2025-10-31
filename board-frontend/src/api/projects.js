// src/api/projects.js
const API_URL = "http://localhost:8080/api/projects"; // update to match backend

export const getProjects = async () => {
  const response = await fetch(API_URL);
  return response.json();
};

export const createProject = async (data) => {
  const response = await fetch(API_URL, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  });
  return response.json();
};
