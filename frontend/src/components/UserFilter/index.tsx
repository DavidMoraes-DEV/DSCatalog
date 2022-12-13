import { ReactComponent as SearchIcon } from 'assets/images/search-icon.svg';
import './styles.css';
import Select from 'react-select';

const UserFilter = () => {
  return (
    <div className="base-card user-filter-container">
      <form onSubmit={() => {}} className="user-filter-form">
        <div className="user-filter-name-container">
          <input
            type="text"
            className="form-control"
            placeholder="Pesquisar por Nome"
            name="textInput"
          />
          <button className="user-filter-search-icon">
            <SearchIcon />
          </button>
        </div>
        <div className="user-filter-botton-container">
          <div className="user-filter-user-container">
            <Select
              options={[
                { value: 1, label: 'Mais Antigos' },
                { value: 2, label: 'Mais Recentes' },
                { value: 3, label: 'Ordem Alfabética' },
              ]}
              isClearable
              classNamePrefix="user-filter-select"
              placeholder="Filtrar por"
            />
          </div>
          <button
            onClick={() => {}}
            className="btn btn-outline-secondary btn-user-filter-clear"
          >
            LIMPAR <span className="btn-user-filter-word">FILTRO</span>
          </button>
        </div>
      </form>
    </div>
  );
};

export default UserFilter;
