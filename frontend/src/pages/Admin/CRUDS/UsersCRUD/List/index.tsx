import './styles.css';
import UserCrudCard from 'pages/Admin/CRUDS/UsersCRUD/UserCrudCard';
import { Link } from 'react-router-dom';
import Pagination from 'components/Pagination';
import UserFilter from 'components/UserFilter';

const List = () => {
  return (
    <div className='user-crud-container'>
      <div className="user-crud-content">
        <div className="user-crud-bar-container">
          <Link to="/admin/Users/create">
            <button className="btn btn-primary text-white btn-crud-add">
              ADICIONAR
            </button>
          </Link>
          <UserFilter />
        </div>
        <div className="row">
            <div className="col-sm-6 col-md-12">
              <UserCrudCard />
              <UserCrudCard />
              <UserCrudCard />
              <UserCrudCard />
            </div>
        </div>
      </div>
      <Pagination
        forcePage={0}
        pageCount={10}
        range={3}
        onChange={() => {}}
      />
    </div>
  );
};

export default List;
