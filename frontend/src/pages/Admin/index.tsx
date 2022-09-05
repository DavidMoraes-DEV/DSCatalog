import { Route, Switch } from 'react-router-dom';
import Navbar from './Navbar';
import './styles.css';
import Users from './User';

const Admin = () => {
  return (
    <div className="admin-container">
      <Navbar />
      <div className="admin-content-container">
        <Switch>
          <Route path="/admin/products">
            <h1>Product CRUD</h1>
          </Route>
          <Route path="/admin/categories">
            <h1>Category CRUD</h1>
          </Route>
          <Route path="/admin/users">
            <Users />
          </Route>
        </Switch>
      </div>
    </div>
  );
};

export default Admin;
