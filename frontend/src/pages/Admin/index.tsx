import PrivateRoute from 'components/PrivateRoute';
import { Switch } from 'react-router-dom';
import ProductsCRUD from './CRUDS/ProductsCRUD';
import Navbar from './Navbar';
import './styles.css';
import Users from './Users';

const Admin = () => {
  return (
    <div className="admin-container">
      <Navbar />
      <div className="admin-content-container">
        <Switch>
          <PrivateRoute path="/admin/products">
            <ProductsCRUD />
          </PrivateRoute>
          <PrivateRoute path="/admin/categories">
            <h1>Category CRUD</h1>
          </PrivateRoute>
          <PrivateRoute path="/admin/users" roles={['ROLE_ADMIN']}>
            <Users />
          </PrivateRoute>
        </Switch>
      </div>
    </div>
  );
};

export default Admin;
