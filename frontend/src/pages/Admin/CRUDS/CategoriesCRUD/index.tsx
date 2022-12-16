import { Route, Switch } from 'react-router-dom';
import Form from './Form';
import List from './List';

const CategoryCRUD = () => {
  return (
    <Switch>
      <Route path="/admin/categories" exact>
        <List />
      </Route>
      <Route path="/admin/categories/:categoryId">
        <Form />
      </Route>
    </Switch>
  );
};

export default CategoryCRUD;
