import { ReactComponent as ArrowIcon } from 'assets/images/arrow.svg';
import ProductPrice from 'components/ProductPrice';

const ProductDetails = () => {
    return (
        <div className="products-details-container">
            <div className="product-details-card">
                <div className="goback-container">
                    <ArrowIcon />
                    <h2>VOLTAR</h2>
                </div>
                <div className="row">
                    <div className="col-xl-6">
                        <div className="img-container">
                            <img src="https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/2-big.jpg" alt="Nome do produto" />
                        </div>
                        <div className="name-price-container">
                            <h1>Nome do Produto</h1>
                            <ProductPrice price={2345.67}/>
                        </div>
                    </div>
                    <div className="col-xl-6">
                        <div className="description-container">
                            <h2>Descrição do Produto</h2>
                            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Non, neque!</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default ProductDetails;