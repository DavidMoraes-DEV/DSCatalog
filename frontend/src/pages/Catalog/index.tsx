import { AxiosRequestConfig } from 'axios';
import Pagination from 'components/Pagination';
import ProductCard from 'components/ProductCard';
import ProductFilter, { ProductFilterData } from 'components/ProductFilter';
import { useCallback, useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { Product } from 'types/products';
import { SpringPage } from 'types/vendor/spring';
import { requestBackend } from 'util/requests';
import CardLoader from './CardLoader';

import './styles.css';

type ControlComponentsData = {
  activePage: number;
  filterData: ProductFilterData;
};

const Catalog = () => {
  const [page, setPage] = useState<SpringPage<Product>>();
  const [isLoading, setIsLoading] = useState(false);

  const [controlComponentsData, setControlComponentsData] =
    useState<ControlComponentsData>({
      activePage: 0,
      filterData: { name: '', category: null },
    });

  const handlePageChange = (pageNumber: number) => {
    setControlComponentsData({
      activePage: pageNumber,
      filterData: controlComponentsData.filterData,
    });
  };

  const handleSubmitFilter = (data: ProductFilterData) => {
    setControlComponentsData({ activePage: 0, filterData: data });
  };

  const getProducts = useCallback(() => {
    const config: AxiosRequestConfig = {
      method: 'GET',
      url: '/products',
      params: {
        page: controlComponentsData.activePage,
        size: 8,
        name: controlComponentsData.filterData.name,
        categoryId: controlComponentsData.filterData.category?.id,
      },
    };

    setIsLoading(true);
    requestBackend(config)
      .then((response) => {
        setPage(response.data);
      })
      .finally(() => {
        setIsLoading(false);
      });
  }, [controlComponentsData]);

  useEffect(() => {
    getProducts();
  }, [getProducts]);

  return (
    <div className="container my-4 catalog-container">
      <div className="catalog-bar-container">
        <h1>Catálogo de produtos</h1>
        <ProductFilter onSubmitFilter={handleSubmitFilter} />
      </div>

      <div className="row catalog-grid-container">
        {isLoading ? (
          <CardLoader />
        ) : (
          page?.content.map((product) => {
            return (
              <div
                className="col-sm-6 col-md-4 col-xl-3 catalog-item-container"
                key={product.id}
              >
                <Link to={`/products/${product.id}`}>
                  <ProductCard product={product} />
                </Link>
              </div>
            );
          })
        )}
      </div>

      <div>
        <Pagination
          forcePage={page?.number}
          pageCount={page ? page.totalPages : 0}
          range={10}
          onChange={handlePageChange}
        />
      </div>
    </div>
  );
};

export default Catalog;
