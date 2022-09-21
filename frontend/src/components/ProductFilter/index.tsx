import CategoryBadge from 'pages/Admin/CRUDS/ProductsCRUD/CategoryBadge';
import './styles.css';

const ProductFilter = () => {
  return (
    <div className="base-card product-filter-container">
      <form action="" className="product-filter-form">
        <div className="product-filter-name-container">
          <input className="form-control" type="text" />
        </div>
        <div className="product-filter-botton-container">
          <div className='product-filter-category-container'>
            <select name="" id="">
              <option value="">Livros</option>
            </select>
          </div>
          <button className="btn btn-outline-secondary">Limpar Filtros</button>
        </div>
      </form>
    </div>
  );
};

export default ProductFilter;
