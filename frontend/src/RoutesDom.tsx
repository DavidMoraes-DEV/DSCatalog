import Navbar from "components/Navbar";
import Admin from "pages/Admin";
import Catalog from "pages/Catalog";
import Home from "pages/Home";
import { BrowserRouter, Routes, Route } from "react-router-dom";


const RoutesDom = () => (

    <BrowserRouter>
        <Navbar />
        <Routes>
            <Route path='/' element={<Home />} />
            <Route path='/products' element={<Catalog />} />
            <Route path='/admin' element={<Admin />} />
        </Routes>
    </BrowserRouter>

);

export default RoutesDom;