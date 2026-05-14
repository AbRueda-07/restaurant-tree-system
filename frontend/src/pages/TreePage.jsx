import { useState } from "react";
import { getTreeById, getDfs, getBfs } from "../api/treeApi";
import TreeView from "../components/TreeView";

function TreePage() {
  const [nodeId, setNodeId] = useState("");
  const [tree, setTree] = useState(null);
  const [dfs, setDfs] = useState([]);
  const [bfs, setBfs] = useState([]);
  const [error, setError] = useState("");

  const loadTree = async () => {
    try {
      setError("");
      const data = await getTreeById(nodeId);
      setTree(data);
    } catch (err) {
      setError(err.message);
    }
  };

  const loadDfs = async () => {
    try {
      setError("");
      const data = await getDfs(nodeId);
      setDfs(data);
    } catch (err) {
      setError(err.message);
    }
  };

  const loadBfs = async () => {
    try {
      setError("");
      const data = await getBfs(nodeId);
      setBfs(data);
    } catch (err) {
      setError(err.message);
    }
  };

  return (
    <main className="container">
      <h1>Restaurant Tree System</h1>

      <p className="subtitle">
        Frontend básico para visualizar el árbol del menú de restaurante.
      </p>

      <section className="panel">
        <h2>Buscar árbol o subárbol</h2>

        <div className="actions">
          <input
            type="text"
            placeholder="Ingrese ID del nodo"
            value={nodeId}
            onChange={(e) => setNodeId(e.target.value)}
          />

          <button onClick={loadTree}>Cargar árbol</button>
          <button onClick={loadDfs}>DFS</button>
          <button onClick={loadBfs}>BFS</button>
        </div>

        {error && <p className="error">{error}</p>}
      </section>

      <section className="panel">
        <h2>Visualización jerárquica</h2>
        {tree ? <TreeView node={tree} /> : <p>No hay árbol cargado.</p>}
      </section>

      <section className="panel">
        <h2>Recorrido DFS</h2>
        {dfs.length > 0 ? (
          <pre>{JSON.stringify(dfs, null, 2)}</pre>
        ) : (
          <p>No hay recorrido DFS cargado.</p>
        )}
      </section>

      <section className="panel">
        <h2>Recorrido BFS</h2>
        {bfs.length > 0 ? (
          <pre>{JSON.stringify(bfs, null, 2)}</pre>
        ) : (
          <p>No hay recorrido BFS cargado.</p>
        )}
      </section>
    </main>
  );
}

export default TreePage;