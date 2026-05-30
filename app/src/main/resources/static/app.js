const API_BASE = "/api/tree";

function getNumberInput(id, label) {
  const value = document.getElementById(id).value;

  if (value === "" || Number(value) < 0) {
    throw new Error(`${label} debe ser un número válido.`);
  }

  return Number(value);
}

function getTextInput(id, label) {
  const value = document.getElementById(id).value.trim();

  if (!value) {
    throw new Error(`${label} no puede estar vacío.`);
  }

  return value;
}

function showResult(data, isError = false) {
  const resultBox = document.getElementById("resultBox");
  resultBox.className = isError ? "error" : "success";

  if (
    typeof data === "string" ||
    typeof data === "number" ||
    typeof data === "boolean"
  ) {
    resultBox.textContent = String(data);
    return;
  }

  resultBox.textContent = JSON.stringify(data, null, 2);
}

function showError(error) {
  console.error(error);
  showResult(error.message || "Error inesperado.", true);
}

async function requestJson(url, options = {}) {
  const response = await fetch(url, options);

  if (!response.ok) {
    throw new Error(`Error HTTP ${response.status}: ${response.statusText}`);
  }

  const text = await response.text();

  if (!text) {
    return null;
  }

  try {
    return JSON.parse(text);
  } catch {
    return text;
  }
}

async function createRoot() {
  try {
    const id = getNumberInput("rootId", "ID raíz");
    const value = getTextInput("rootValue", "Nombre raíz");

    const data = await requestJson(`${API_BASE}/root`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({ id, value })
    });

    document.getElementById("queryRootId").value = id;
    showResult(data);
    renderTree(data);
  } catch (error) {
    showError(error);
  }
}

async function addChild() {
  try {
    const parentId = getNumberInput("parentId", "ID padre");
    const id = getNumberInput("childId", "ID hijo");
    const value = getTextInput("childValue", "Nombre hijo");

    const data = await requestJson(`${API_BASE}/${parentId}/child`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({ id, value })
    });

    showResult(data);

    const rootId = document.getElementById("queryRootId").value || parentId;
    document.getElementById("queryRootId").value = rootId;
    await getTree();
  } catch (error) {
    showError(error);
  }
}

async function updateNode() {
  try {
    const id = getNumberInput("updateNodeId", "ID nodo");
    const value = getTextInput("updateNodeValue", "Nuevo nombre");

    const data = await requestJson(`${API_BASE}/${id}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({ id, value })
    });

    showResult(data);

    const rootId = document.getElementById("queryRootId").value;
    if (rootId) {
      await getTree();
    }
  } catch (error) {
    showError(error);
  }
}

async function deleteNode() {
  try {

    const id = getNumberInput("deleteNodeId", "ID nodo");

    const confirmed = confirm(
      `¿Seguro que deseas eliminar el nodo con ID ${id}?`
    );

    if (!confirmed) {
      showResult("Eliminación cancelada.");
      return;
    }

    await requestJson(`${API_BASE}/${id}`, {
      method: "DELETE"
    });

    showResult(`Nodo con ID ${id} eliminado correctamente.`);

    const rootId = document.getElementById("queryRootId").value;

    if (rootId && Number(rootId) !== id) {
      await getTree();
    } else {
      document.getElementById("treeView").textContent =
        "Árbol eliminado o sin datos cargados.";
    }

  } catch (error) {
    showError(error);
  }
}

async function getChildren() {
  try {
    const id = getNumberInput("nodeActionId", "ID nodo");

    const data = await requestJson(`${API_BASE}/${id}/children`);

    if (Array.isArray(data) && data.length === 0) {
      showResult(`El nodo con ID ${id} no tiene hijos directos.`);
      return;
    }

    showResult(data);

  } catch (error) {
    showError(error);
  }
}

async function checkExists() {
  try {
    const id = getNumberInput("nodeActionId", "ID nodo");

    const data = await requestJson(`${API_BASE}/${id}/exists`);

    showResult(`El nodo con ID ${id} existe: ${data}`);

  } catch (error) {
    showError(error);
  }
}
async function getTree() {
  try {
    const rootId = getNumberInput("queryRootId", "ID raíz a consultar");
    const data = await requestJson(`${API_BASE}/${rootId}`);

    showResult(data);
    renderTree(data);
  } catch (error) {
    showError(error);
  }
}

async function runDfs() {
  try {
    const rootId = getNumberInput("queryRootId", "ID raíz a consultar");
    const data = await requestJson(`${API_BASE}/${rootId}/dfs`);

    showResult(data);
  } catch (error) {
    showError(error);
  }
}

async function runBfs() {
  try {
    const rootId = getNumberInput("queryRootId", "ID raíz a consultar");
    const data = await requestJson(`${API_BASE}/${rootId}/bfs`);

    showResult(data);
  } catch (error) {
    showError(error);
  }
}

async function getHeight() {
  try {
    const rootId = getNumberInput("queryRootId", "ID raíz a consultar");
    const data = await requestJson(`${API_BASE}/${rootId}/height`);

    showResult(`Altura del árbol: ${data}`);
  } catch (error) {
    showError(error);
  }
}

async function validateTree() {
  try {
    const rootId = getNumberInput("queryRootId", "ID raíz a consultar");
    const data = await requestJson(`${API_BASE}/${rootId}/validate`);

    showResult(`Árbol válido: ${data}`);
  } catch (error) {
    showError(error);
  }
}

function renderTree(node) {
  const treeView = document.getElementById("treeView");

  if (!node || typeof node !== "object") {
    treeView.textContent = "No hay árbol para mostrar.";
    return;
  }

  treeView.innerHTML = "";
  treeView.appendChild(createNodeElement(node));
}

function createNodeElement(node) {
  const ul = document.createElement("ul");
  const li = document.createElement("li");

  li.textContent = `${node.value || "Sin valor"} (ID: ${node.id})`;

  if (Array.isArray(node.children) && node.children.length > 0) {
    node.children.forEach(child => {
      li.appendChild(createNodeElement(child));
    });
  }

  ul.appendChild(li);
  return ul;
}

async function getDepth() {

    try {

        const id = getNumberInput(
            "advancedNodeId",
            "ID nodo"
        );

        const result = await requestJson(
            `${API_BASE}/${id}/depth`
        );

        showResult(
            `Profundidad del nodo ${id}: ${result}`
        );

    } catch (error) {

        showError(error);
    }
}

async function getAncestors() {

    try {

        const id = getNumberInput(
            "advancedNodeId",
            "ID nodo"
        );

        const result = await requestJson(
            `${API_BASE}/${id}/ancestors`
        );

        showResult(result);

    } catch (error) {

        showError(error);
    }
}

async function getPath() {

    try {

        const id = getNumberInput(
            "advancedNodeId",
            "ID nodo"
        );

        const result = await requestJson(
            `${API_BASE}/${id}/path`
        );

        showResult(result);

    } catch (error) {

        showError(error);
    }
}