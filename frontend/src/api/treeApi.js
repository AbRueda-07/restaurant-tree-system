const API_BASE_URL = "http://localhost:8080/api/tree";

export async function getTreeById(id) {
  const response = await fetch(`${API_BASE_URL}/${id}`);

  if (!response.ok) {
    throw new Error("No se pudo obtener el árbol");
  }

  return response.json();
}

export async function getDfs(id) {
  const response = await fetch(`${API_BASE_URL}/${id}/dfs`);

  if (!response.ok) {
    throw new Error("No se pudo obtener el recorrido DFS");
  }

  return response.json();
}

export async function getBfs(id) {
  const response = await fetch(`${API_BASE_URL}/${id}/bfs`);

  if (!response.ok) {
    throw new Error("No se pudo obtener el recorrido BFS");
  }

  return response.json();
}