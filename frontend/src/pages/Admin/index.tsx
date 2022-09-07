import PrivateRoute from 'components/PrivateRoute';
import { Switch } from 'react-router-dom';
import Navbar from './Navbar';
import './styles.css';
import Users from './User';

const Admin = () => {
  return (
    <div className="admin-container">
      <Navbar />
      <div className="admin-content-container">
        <Switch>
          <PrivateRoute path="/admin/products">
            <h1>Product CRUD</h1>
          </PrivateRoute>
          <PrivateRoute path="/admin/categories">
            <h1>Category CRUD</h1>
          </PrivateRoute>
          <PrivateRoute path="/admin/users">
            <Users />
          </PrivateRoute>
        </Switch>
      </div>
    </div>
  );
};

export default Admin;
