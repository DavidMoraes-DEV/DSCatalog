import './styles.css';
import ProductPrice from 'components/ProductPrice';
import { Product } from 'types/products';
import CategoryBadge from '../CategoryBadge';

type Props = {
  product: Product;
};

const ProductCrudCard = ({ product }: Props) => {
  return (
    <div className="base-card product-crud-card">
      <div className="product-crud-card-top-container">
        <img src={product.imgUrl} alt={product.name} />
      </div>
      <div className="product-crud-card-description">
        <div className="product-crud-card-button-container">
          <h6>{product.name}</h6>
          <ProductPrice price={product.price} />
        </div>
        <div className="product-crud-categories-container">
          {product.categories.map((category) => (
            <CategoryBadge name={category.name} key={category.id} />
          ))}
        </div>
      </div>
      <div className='product-crud-card-buttons-container'>
            <button className='btn btn-outline-danger product-crud-card-button product-crud-card-button-first'>
                EXCLUIR
            </button>
            <button className='btn btn-outline-secondary product-crud-card-button'>
                EDITAR
            </button>
      </div>
    </div>
  );
};

export default ProductCrudCard;
