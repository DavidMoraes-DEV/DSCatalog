import './styles.css';
import UserCrudCard from 'pages/Admin/CRUDS/UsersCRUD/UserCrudCard';
import { Link } from 'react-router-dom';
import { useCallback, useEffect, useState } from 'react';
import { SpringPage } from 'types/vendor/spring';
import { User } from 'types/user';
import { AxiosRequestConfig } from 'axios';
import { requestBackend } from 'util/requests';
import Pagination from 'components/Pagination';
import UserFilter, { UserFilterData } from 'components/UserFilter';

type ControlComponentsData = {
  activePage: number;
  filterData: UserFilterData;
};

const List = () => {
  const [page, setPage] = useState<SpringPage<User>>();

  const [controlComponentsData, setControlComponentsData] =
    useState<ControlComponentsData>({
      activePage: 0,
      filterData: { user: null, option: null },
    });

  const handlePageChange = (pageNumber: number) => {
    setControlComponentsData({
      activePage: pageNumber,
      filterData: controlComponentsData.filterData,
    });
  };

  const handleSubmitFilter = (data: UserFilterData) => {
    setControlComponentsData({ activePage: 0, filterData: data });
  };

  const getuser = useCallback(() => {
    let sortUser;

    switch (controlComponentsData.filterData.option?.id) {
      case 1:
        sortUser = 'updateAt,createdAt';
        break;

      case 2:
        sortUser = 'updateAt,createdAt,desc';
        break;

      case 3:
        sortUser = 'firstName';
        break;

      default:
        sortUser = '';
        break;
    }

    const config: AxiosRequestConfig = {
      method: 'GET',
      url: '/users',
      withCredentials: true,
      params: {
        page: controlComponentsData.activePage,
        size: 4,
        firstName: controlComponentsData.filterData.textInput,
        sort: sortUser,
      },
    };

    requestBackend(config).then((response) => {
      setPage(response.data);
    });
  }, [controlComponentsData]);

  useEffect(() => {
    getuser();
  }, [getuser]);

  return (
    <div className="user-crud-container">
      <div className="user-crud-content">
        <div className="user-crud-bar-container">
          <Link to="/admin/Users/create">
            <button className="btn btn-primary text-white btn-crud-add">
              ADICIONAR
            </button>
          </Link>
          <UserFilter onSubmitFilter={handleSubmitFilter} />
        </div>
        <div className="row">
          {page?.content.map((user) => (
            <div className="col-sm-6 col-md-12" key={user.id}>
              <UserCrudCard user={user} onDelete={getuser} />
            </div>
          ))}
        </div>
      </div>
      <Pagination
        forcePage={page?.number}
        pageCount={page ? page.totalPages : 0}
        range={3}
        onChange={handlePageChange}
      />
    </div>
  );
};

export default List;
