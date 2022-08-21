import { ReactComponent as ArrowIcon } from 'assets/images/arrow.svg';
import axios from 'axios';
import ProductPrice from 'components/ProductPrice';
import { Link } from 'react-router-dom';
import { Product } from 'types/products';
import { BASE_URL } from 'util/requests';

import './styles.css';

const ProductDetails = () => {

    //Formas incorretas de declarar a variável (Apenas para questões didático)
    let product : Product;

    axios.get(BASE_URL + "/products/2")
    .then(response => {
        console.log(response.data)
    });

    return (
        <div className="product-details-container">
            <div className="base-card product-details-card">
                <div className="goback-container">
                    <Link to={'/products'} className="goback-container-link">
                        <ArrowIcon />
                        <h2>VOLTAR</h2>
                    </Link> 
                </div>
                <div className="row">
                    <div className="col-xl-6">
                        <div className="img-container">
                            <img src="https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/2-big.jpg" alt="Nome do produto" />
                        </div>
                        <div className="name-price-container">
                            <h1>Nome do Produto</h1>
                            < ProductPrice price={2345.67} />
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