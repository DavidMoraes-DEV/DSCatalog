import CategoriesCRUD from "components/CRUDS/CategoriesCRUD";
import ProductsCRUD from "components/CRUDS/ProductsCRUD";
import UsersCRUD from "components/CRUDS/UsersCRUD";
import Navbar from "components/Navbar";
import Admin from "pages/Admin";
import Auth from "pages/Admin/Auth";
import CardLogin from "pages/Admin/Auth/CardLogin";
import CardRecover from "pages/Admin/Auth/CardRecover";
import CardSignup from "pages/Admin/Auth/CardSignup";
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
                <Route path="/admin/auth" element={<Auth />}>
                    <Route index element={<CardLogin />} />
                    <Route path="signup" element={<CardSignup />} />
                    <Route path="recover" element={<CardRecover />} />
                </Route>
                <Route path='/products' element={<Catalog />} />
                <Route path='/products/:productId' element={<ProductDetails />} />
            </>
        </Routes>
    </BrowserRouter>

);

export default RoutesDom;