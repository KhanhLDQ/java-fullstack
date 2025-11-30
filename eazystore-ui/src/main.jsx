import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import "./index.css";
import App from "./App.jsx";

createRoot(document.getElementById("root")).render(
  <StrictMode>
    {/* StrictMode helps catch bugs in development by:
        - Running components twice to detect side effects
        - Warning about deprecated APIs
        - Checking for unsafe lifecycles
        Note: Only affects development, not production builds */}
    <App />
  </StrictMode>
);
