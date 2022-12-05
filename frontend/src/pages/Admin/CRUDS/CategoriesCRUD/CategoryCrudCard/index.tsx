import './styles.css';
import { Link } from 'react-router-dom';

const CategoryCrudCard = () => {
  return (
    <div className="base-card category-crud-card">
      <h6>Livros</h6>
      <div className="category-crud-card-buttons-container">
        <Link to={'/admin/categorys/1'}>
          <button className="btn btn-outline-secondary category-crud-card-button">
            EDITAR
          </button>
        </Link>
        <button
          onClick={() => {}}
          className="btn btn-outline-danger category-crud-card-button"
        >
          EXCLUIR
        </button>
      </div>
    </div>
  );
};

export default CategoryCrudCard;
