import "./App.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import OwnerLogin from "./pages/OwnerLogin";

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <Routes>
          <Route path="/" element={<OwnerLogin />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
