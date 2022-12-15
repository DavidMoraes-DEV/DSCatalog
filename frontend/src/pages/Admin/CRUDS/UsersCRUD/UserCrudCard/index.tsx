import './styles.css';
import { User } from 'types/user';
import { Link } from 'react-router-dom';
import { AxiosRequestConfig } from 'axios';
import { requestBackend } from 'util/requests';

type Props = {
  user: User;
  onDelete: Function;
};

const userCrudCard = ({ user, onDelete }: Props) => {
  const handleDelete = (userId: number) => {
    if (!window.confirm('Tem certeza que deseja Excluir esse usuário?')) {
      return;
    }

    const config: AxiosRequestConfig = {
      method: 'DELETE',
      url: `/users/${userId}`,
      withCredentials: true,
    };

    requestBackend(config).then(() => {
      onDelete();
    });
  };

  return (
    <div className="base-card user-crud-card">
      <div className="user-crud-card-content">
        <h6>{`${user.firstName} ${user.lastName}`}</h6>
        <p>{user.email}</p>
      </div>
      <div className="user-crud-card-buttons-container">
        <Link to={`/admin/users/${user.id}`}>
          <button className="btn btn-outline-secondary user-crud-card-button">
            EDITAR
          </button>
        </Link>
        <button
          onClick={() => handleDelete(user.id)}
          className="btn btn-outline-danger user-crud-card-button"
        >
          EXCLUIR
        </button>
      </div>
    </div>
  );
};

export default userCrudCard;
