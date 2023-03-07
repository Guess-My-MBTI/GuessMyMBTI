import "./App.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import OwnerLogin from "./pages/OwnerLogin";
import OwnerMain from "./pages/OwnerMain";
import OwnerResult from "./pages/OwnerResult";
import Question from "./pages/Question";
import GuestLogin from "./pages/GuestLogin";
import GusetResult from "./pages/GusetResult";
import Notice from "./components/Notice";
import LoginHandeler from "./components/LoginHandeler";
import OwnerQuestion from "./pages/OwnerQuestion";

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <Routes>
          {/* ---------수빈------------ */}
          <Route path="/" element={<OwnerLogin />} />
          <Route path="/owner-main" element={<OwnerMain />} />
          <Route path="/owner-result" element={<OwnerResult />} />
          <Route path="/owner-question" element={<OwnerQuestion />} />
          <Route path="/notice" element={<Notice />} />
          <Route
            path="/login/oauth2/callback/kakao"
            element={<LoginHandeler />}
          />
          {/* ---------아름------------ */}
          <Route path="/question" element={<Question />} />
          <Route path="/guest-login" element={<GuestLogin />} />
          <Route path="/guest-result" element={<GusetResult />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
