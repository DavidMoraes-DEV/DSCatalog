import { ReactComponent as ArrowIcon } from 'assets/images/arrow.svg';
import ReactPaginate from 'react-paginate';

import './styles.css';

type Props = {
  forcePage?: number;
  pageCount: number;
  range: number;
  onChange?: (pageNumber: number) => void;
};

const Pagination = ({ forcePage, pageCount, range, onChange }: Props) => {
  return (
    <ReactPaginate
      forcePage={forcePage}
      pageCount={pageCount}
      pageRangeDisplayed={range}
      marginPagesDisplayed={1}
      containerClassName="pagination-container"
      pageLinkClassName="pagination-item"
      breakClassName="pagination-item"
      previousClassName="arrow-previous"
      nextClassName="arrow-next"
<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
>>>>>>> e3b24920c27b9a027a81f2bf02c72e29a08f8b7b
      previousLabel={
        <div className="pagination-arrow-container">
          <ArrowIcon />
        </div>
      }
      nextLabel={
        <div className="pagination-arrow-container">
          <ArrowIcon />
        </div>
      }
<<<<<<< HEAD
>>>>>>> e3b24920c27b9a027a81f2bf02c72e29a08f8b7b
=======
>>>>>>> e3b24920c27b9a027a81f2bf02c72e29a08f8b7b
      activeLinkClassName="pagination-link-active"
      disabledClassName="arrow-inactive"
      
      onPageChange={(items) => (onChange ? onChange(items.selected) : {})}

      previousLabel={<div className="pagination-arrow-container" data-testid="arrow-previous"><ArrowIcon /></div>}
      nextLabel={<div className="pagination-arrow-container" data-testid="arrow-next"><ArrowIcon /></div>}
    />
  );
};

export default Pagination;
