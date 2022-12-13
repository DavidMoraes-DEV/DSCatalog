import PrivateRoute from 'components/PrivateRoute';
import { Switch } from 'react-router-dom';
import ProductsCRUD from './CRUDS/ProductsCRUD';
import CategoryCRUD from './CRUDS/CategoriesCRUD';
import Navbar from './Navbar';
import './styles.css';
import UsersCRUD from './CRUDS/UsersCRUD';

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
            <CategoryCRUD />
          </PrivateRoute>
          <PrivateRoute path="/admin/users" roles={['ROLE_ADMIN']}>
            <UsersCRUD />
          </PrivateRoute>
        </Switch>
      </div>
    </div>
  );
};

export default Admin;
