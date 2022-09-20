import { ReactComponent as ArrowIcon } from 'assets/images/arrow.svg';
import ReactPaginate from 'react-paginate';

import './styles.css';

const Pagination = () => {
  return (
    <>
      <ReactPaginate
        pageCount={10}
        pageRangeDisplayed={3}
        marginPagesDisplayed={1}
      />

      <div className="pagination-container">
        <ArrowIcon className="arrow-previous arrow-inactive" />
        <div className="pagination-item active">1</div>
        <div className="pagination-item">2</div>
        <div className="pagination-item">3</div>
        <div className="pagination-item">...</div>
        <div className="pagination-item">10</div>
        <ArrowIcon className="arrow-next arrow-active" />
      </div>
    </>
  );
};

export default Pagination;
