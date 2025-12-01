import "./App.css";
import Header from "./components/Header";
import Footer from "./components/Footer";
import React from "react";
import { Outlet } from "react-router-dom";

//React will not load the entire pages from the server
//Instead, it only renders the corresponding components that represent each page
// -> that's why it can be called SPA
function App() {
  return (
    <>
      <Header />
      {/* dynaic child route components */}
      <Outlet />
      <Footer />
    </>
  );
}

export default App;
