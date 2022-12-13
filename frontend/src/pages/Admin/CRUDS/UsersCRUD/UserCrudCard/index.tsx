import './styles.css';
import { Link } from 'react-router-dom';

const userCrudCard = () => {
  return (
    <div className="base-card user-crud-card">
      <div className='user-crud-card-content'>
        <h6>David Moraes de Oliveira</h6>
        <p>david@dmdeveloper.com</p>
      </div>
      <div className="user-crud-card-buttons-container">
        <Link to={'/admin/users/1'}>
          <button className="btn btn-outline-secondary user-crud-card-button">
            EDITAR
          </button>
        </Link>
        <button
          onClick={() => {}}
          className="btn btn-outline-danger user-crud-card-button"
        >
          EXCLUIR
        </button>
      </div>
    </div>
  );
};

export default userCrudCard;
