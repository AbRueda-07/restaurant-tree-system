function TreeView({ node }) {
  if (!node) {
    return null;
  }

  const label = node.value || node.name || node.label || node.id;

  return (
    <div className="tree-node">
      <div className="node-box">{label}</div>

      {node.children && node.children.length > 0 && (
        <div className="children">
          {node.children.map((child) => (
            <TreeView key={child.id} node={child} />
          ))}
        </div>
      )}
    </div>
  );
}

export default TreeView;