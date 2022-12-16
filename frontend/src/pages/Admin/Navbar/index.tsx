import { NavLink } from 'react-router-dom';
import { hasAnyRoles } from 'util/auth';
import './styles.css';

const Navbar = () => {
  return (
    <nav className="admin-nav-container">
      <ul className="admin-nav-itens-container">
        <li>
          <NavLink to="/admin/products" className="admin-nav-item default">
            <p>Produtos</p>
          </NavLink>
        </li>
        <li>
          <NavLink to="/admin/categories" className="admin-nav-item">
            <p>Categorias</p>
          </NavLink>
        </li>
        {hasAnyRoles(['ROLE_ADMIN']) && (
          <li>
            <NavLink to="/admin/users" className="admin-nav-item">
              <p>Usuários</p>
            </NavLink>
          </li>
        )}
      </ul>
    </nav>
  );
};

export default Navbar;
