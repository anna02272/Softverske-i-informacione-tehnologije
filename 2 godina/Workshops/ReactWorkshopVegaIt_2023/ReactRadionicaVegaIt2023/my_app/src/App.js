import {BrowserRouter, Routes, Route} from "react-router-dom"
import HomePage from "./Pages/HomePage/HomePage"
import ContactsPage from "./Pages/ContactsPage/ContactsPage";
import PersonalInfoPage from "./Pages/PersonalInfoPage/PersonalInfoPage";

function App() {
  return (
    <BrowserRouter>
    <Routes>
      <Route path="/" element={<HomePage />}/>
      <Route path="/contacts" element={<ContactsPage />}/>
      <Route path="/personal" element={<PersonalInfoPage />}/>
      
    </Routes>
    </BrowserRouter>
  );
}

export default App;
