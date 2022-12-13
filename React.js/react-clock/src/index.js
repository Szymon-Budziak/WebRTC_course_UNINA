import React from 'react';
import ReactDOM from 'react-dom/client';
import ClassClock from "./ClassClock";
import FunctionClock from "./FunctionClock";

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(<React.StrictMode>
    <ClassClock/>
    <FunctionClock/>
</React.StrictMode>,);
