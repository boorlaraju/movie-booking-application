import React from "react";
export default function Notice({ type = 'danger', children, onClose }) { if (!children) return null; return <div className={`alert alert-${type} d-flex justify-content-between align-items-center`} role="alert"><span>{children}</span>{onClose && <button className="btn-close" onClick={onClose} aria-label="Close"/>}</div>; }
