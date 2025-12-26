import "./App.css";
import Header from "./components/Header";
import Footer from "./components/Footer";
import React from "react";
import { Outlet } from "react-router-dom";
import { useNavigation } from "react-router-dom";

//React will not load the entire pages from the server
//Instead, it only renders the corresponding components that represent each page
// -> that's why it can be called SPA
function App() {
  //useNavigation provides feedback during loader execution
  //loading|idle(component renders)|submitting
  const navigation = useNavigation();

  return (
    <>
      <Header />
      {/* dynaic child route components */}
      {/* <Outlet /> */}
      {navigation.state === "loading" ? (
        <div className="flex items-center justify-center min-h-[852px]">
          <span className="text-4xl font-semibold text-primary dark:text-light">
            Loading...
          </span>
        </div>
      ) : (
        <Outlet />
      )}
      <Footer />
    </>
  );
}

export default App;
