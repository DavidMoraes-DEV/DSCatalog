import { ReactComponent as SearchIcon } from 'assets/images/search-icon.svg';
import './styles.css';
import Select from 'react-select';

const CategoryFilter = () => {

  const options = [
    {value: 'Antigos', label: 'Mais Antigos'},
    {value: 'Recentes', label: 'Mais Recentes'},
    {value: 'Ordem Alfabética', label: 'Ordem Alfabética'}
  ]

  return (
    <div className="base-card category-filter-container">
      <form onSubmit={() => {}} className="category-filter-form">
        <div className="category-filter-name-container">
          <input
            type="text"
            className="form-control"
            placeholder="Pesquisar Categoria"
            name="name"
          />
          <button className="category-filter-search-icon">
            <SearchIcon />
          </button>
        </div>
        <div className="category-filter-botton-container">
          <div className="category-filter-category-container">
                <Select
                  options={options}
                  isClearable
                  classNamePrefix="category-filter-select"
                  placeholder="Filtrar por"
                  onChange={() => {}}
                />
          </div>
          <button
            onClick={() => {}}
            className="btn btn-outline-secondary btn-category-filter-clear"
          >
            LIMPAR <span className="btn-category-filter-word">FILTRO</span>
          </button>
        </div>
      </form>
    </div>
  );
};

export default CategoryFilter;
