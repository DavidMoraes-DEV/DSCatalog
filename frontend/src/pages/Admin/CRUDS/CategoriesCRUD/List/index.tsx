import './styles.css';
import CategoryCrudCard from 'pages/Admin/CRUDS/CategoriesCRUD/CategoryCrudCard';
import { Link } from 'react-router-dom';
import { useCallback, useEffect, useState } from 'react';
import { SpringPage } from 'types/vendor/spring';
import { Category } from 'types/category';
import { AxiosRequestConfig } from 'axios';
import { requestBackend } from 'util/requests';
import Pagination from 'components/Pagination';
import CategoryFilter, { CategoryFilterData } from 'components/CategoryFilter';

type ControlComponentsData = {
  activePage: number;
  filterData: CategoryFilterData;
};

const List = () => {
  const [page, setPage] = useState<SpringPage<Category>>();

  const [controlComponentsData, setControlComponentsData] =
    useState<ControlComponentsData>({
      activePage: 0,
      filterData: { category: null, option: null },
    });

  const handlePageChange = (pageNumber: number) => {
    setControlComponentsData({
      activePage: pageNumber,
      filterData: controlComponentsData.filterData,
    });
  };

  const handleSubmitFilter = (data: CategoryFilterData) => {
    setControlComponentsData({ activePage: 0, filterData: data });
  };

  const getCategory = useCallback(() => {
    let sortCategory;

    switch (controlComponentsData.filterData.option?.id) {
      case 1:
        sortCategory = 'updateAt,createdAt';
        break;

      case 2:
        sortCategory = 'updateAt,createdAt,desc';
        break;

      case 3:
        sortCategory = 'name';
        break;

      default:
        sortCategory = '';
        break;
    }

    const config: AxiosRequestConfig = {
      method: 'GET',
      url: '/categories',
      params: {
        page: controlComponentsData.activePage,
        size: 4,
        name: controlComponentsData.filterData.textInput,
        sort: sortCategory,
      },
    };

    requestBackend(config).then((response) => {
      setPage(response.data);
    });
  }, [controlComponentsData]);

  useEffect(() => {
    getCategory();
  }, [getCategory]);

  return (
    <div className="category-crud-container">
      <div className="category-crud-content">
        <div className="category-crud-bar-container">
          <Link to="/admin/categories/create">
            <button className="btn btn-primary text-white btn-crud-add">
              ADICIONAR
            </button>
          </Link>
          <CategoryFilter onSubmitFilter={handleSubmitFilter} />
        </div>
        <div className="row">
          {page?.content.map((category) => (
            <div className="col-sm-6 col-md-12" key={category.id}>
              <CategoryCrudCard category={category} onDelete={getCategory} />
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
