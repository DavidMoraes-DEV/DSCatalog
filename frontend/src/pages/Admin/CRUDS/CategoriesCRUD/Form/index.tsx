import './styles.css';

const Form = () => {
  return (
    <div className="category-crud-container">
      <div className="base-card category-crud-form-card">
        <h1 className="category-crud-form-title">DADOS DA CATEGORIA</h1>
        <form className="category-crud-form-container" onSubmit={() => {}}>
          <div className="category-crud-input-container">
                <input
                  type="text"
                  className={ 'form-control base-input' }
                  placeholder="Nome da Categoria"
                  name="name"
                />
          </div>
          <div className="category-crud-buttons-container">
            <button className="btn btn-primary category-crud-button text-white">
              SALVAR
            </button>
            <button
              className="btn btn-outline-danger category-crud-button"
              onClick={() => {}}
            >
              CANCELAR
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default Form;
