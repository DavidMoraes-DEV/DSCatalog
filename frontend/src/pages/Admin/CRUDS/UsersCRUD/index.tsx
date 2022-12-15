import { Route, Switch } from 'react-router-dom';
import Form from './Form';
import List from './List';

const UsersCRUD = () => {
  return (
    <Switch>
      <Route path="/admin/users" exact>
        <List />
      </Route>
      <Route path="/admin/users/:userId">
        <Form />
      </Route>
    </Switch>
  );
};

export default UsersCRUD;
