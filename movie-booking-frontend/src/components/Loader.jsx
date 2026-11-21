import React from "react";
export default function Loader({ label = 'Loading...' }) { 
    return <div className="loader-wrap">
             <div className="spinner-border text-warning" role="status">
              <span className="visually-hidden">Loading</span>
             </div>
             <p>{label}</p>
            </div>; }
