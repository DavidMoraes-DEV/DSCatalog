import CategoriesCRUD from "components/CRUDS/CategoriesCRUD";
import ProductsCRUD from "components/CRUDS/ProductsCRUD";
import UsersCRUD from "components/CRUDS/UsersCRUD";
import Navbar from "components/Navbar";
import Admin from "pages/Admin";
import Catalog from "pages/Catalog";
import Home from "pages/Home";
import ProductDetails from "pages/ProductDetails";
import { BrowserRouter, Routes, Route, } from "react-router-dom";


const RoutesDom = () => (

    <BrowserRouter>
        <Navbar />
        <Routes>
            <>
                <Route path='/' element={<Home />} />
                <Route path='/admin' element={<Admin />}>
                    <Route index element={<ProductsCRUD /> }/>
                    <Route path="products" element={<ProductsCRUD /> } />
                    <Route path="categories"element={<CategoriesCRUD /> } />
                    <Route path="users" element={<UsersCRUD /> } />
                </Route>
                <Route path='/products' element={<Catalog />} />
                <Route path='/products/:productId' element={<ProductDetails />} />
            </>
        </Routes>
    </BrowserRouter>

);

export default RoutesDom;