import './styles.css';
import ProductCrudCard from 'pages/Admin/CRUDS/ProductsCRUD/ProductCrudCard';
import { Link } from 'react-router-dom';

const List = () => {
  const product = {
    id: 1,
    name: 'The Lord of the Rings',
    description:
      'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
    price: 90.5,
    imgUrl:
      'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/1-big.jpg',
    date: '2020-07-13T20:50:07.123450Z',
    categories: [
      {
        id: 2,
        name: 'Eletrônicos',
      },
      {
        id: 3,
        name: 'Computadores',
      },
      {
        id: 4,
        name: 'Celulares',
      },
    ],
  };

  return (
    <>
      <div className="product-crud-bar-container">
        <Link to="/admin/products/create">
          <button className="btn btn-primary text-white btn-crud-add">
            ADICIONAR
          </button>
        </Link>
        <div className="base-card product-filter-container">Search bar</div>
      </div>
      <div className='row'>
        <div className='col-sm-6 col-md-12'>
        <ProductCrudCard product={product} />
        </div>
        <div className='col-sm-6 col-md-12'>
        <ProductCrudCard product={product} />
        </div>
        <div className='col-sm-6 col-md-12'>
        <ProductCrudCard product={product} />
        </div>
        <div className='col-sm-6 col-md-12'>
        <ProductCrudCard product={product} />
        </div>
      </div>
    </>
  );
};

export default List;
