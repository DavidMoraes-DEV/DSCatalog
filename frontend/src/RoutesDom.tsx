import Navbar from "components/Navbar";
import Admin from "pages/Admin";
import Catalog from "pages/Catalog";
import Home from "pages/Home";
import ProductDetails from "pages/ProductDetails";
import { BrowserRouter, Routes, Route } from "react-router-dom";


const RoutesDom = () => (

    <BrowserRouter>
        <Navbar />
        <Routes>
            <>
                <Route path='/' element={<Home />} />
                <Route path='/admin' element={<Admin />} />
                <Route path='/products' element={<Catalog />} />
                <Route path='/products/:productId' element={<ProductDetails />} />
            </>
        </Routes>
    </BrowserRouter>

);

export default RoutesDom;