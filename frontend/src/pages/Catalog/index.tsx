import Navbar from "components/Navbar";
import ProductCard from "components/ProductCard";

const Catalog = () => {
    return (
        <>
        <Navbar />
        <div className="container my-4">
            <ProductCard />
        </div>
        </>
    );
}

export default Catalog;