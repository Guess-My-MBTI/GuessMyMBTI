import "./App.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import OwnerLogin from "./pages/OwnerLogin";
import OwnerMain from "./pages/OwnerMain";
import OwnerResult from "./pages/OwnerResult";
import Question from "./pages/Question";
import GuestLogin from "./pages/GuestLogin";
import GusetResult from "./pages/GusetResult";

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <Routes>
          {/* ---------수빈------------ */}
          <Route path="/" element={<OwnerLogin />} />
          <Route path="/owner-main" element={<OwnerMain />} />
          <Route path="/owner-result" element={<OwnerResult />} />
          {/* ---------아름------------ */}
          <Route path="/question" element={<Question />} />
          <Route path="/guest-login" element={<GuestLogin />} />
          <Route path="/guset-result" element={<GusetResult />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
