import './styles.css';
import CategoryCrudCard from 'pages/Admin/CRUDS/CategoriesCRUD/CategoryCrudCard';
import { Link } from 'react-router-dom';
import CategoryFilter from 'components/CategoryFilter';

const List = () => {
  return (
    <div className="category-crud-container">
      <div className="category-crud-bar-container">
        <Link to="/admin/category/create">
          <button className="btn btn-primary text-white btn-crud-add">
            ADICIONAR
          </button>
        </Link>
        <CategoryFilter />
      </div>
      <div className="row">
          <div className="col-sm-6 col-md-12">
            <CategoryCrudCard />
            <CategoryCrudCard />
            <CategoryCrudCard />
          </div>
      </div>
    </div>
  );
};

export default List;
