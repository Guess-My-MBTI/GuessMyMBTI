import "./App.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import OwnerLogin from "./pages/OwnerLogin";

function App() {
  return (
    <BrowserRouter>
      <div className="App"></div>
      <Routes>
        <Route path="/login" element={<OwnerLogin />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
