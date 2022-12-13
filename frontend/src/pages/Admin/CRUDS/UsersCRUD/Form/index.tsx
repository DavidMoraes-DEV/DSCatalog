import './styles.css';
import Select from 'react-select';

const Form = () => {
  return (
    <div className="user-crud-container">
      <div className="base-card user-crud-form-card">
        <h1 className="user-crud-form-title">CADASTRAR USUÁRIO</h1>
        <form onSubmit={() => {}}>
          <div className="user-crud-inputs-container">
            <div className="user-crud-inputs-content">
              <div className="user-crud-input">
                <input
                  type="text"
                  className="form-control base-input"
                  placeholder="Nome"
                  name="firstName"
                />
                <div className="invalid-feedback d-block"></div>
              </div>

              <div className="user-crud-input">
                <input
                  type="text"
                  className="form-control base-input"
                  placeholder="Sobrenome"
                  name="lastName"
                />
                <div className="invalid-feedback d-block"></div>
              </div>
            </div>

            <div className="user-crud-input-email">
              <input
                type="text"
                className="form-control base-input"
                placeholder="Email"
                name="email"
              />
              <div className="invalid-feedback d-block"></div>
            </div>

            <div className="user-crud-inputs-content">
              <div className="user-crud-input">
                <input
                  type="text"
                  className="form-control base-input"
                  placeholder={'Defina uma Senha'}
                  name="password"
                />
                <div className="invalid-feedback d-block"></div>
              </div>

              <div className="user-crud-input">
                <input
                  type="text"
                  className="form-control base-input"
                  placeholder={'Confirme a Senha'}
                  name="password"
                />
                <div className="invalid-feedback d-block"></div>
              </div>
            </div>

            <div className="user-crud-input-button">
              <Select
                options={[
                  { value: 1, label: 'OPERADOR' },
                  { value: 2, label: 'ADMINISTRADOR' },
                ]}
                classNamePrefix="product-crud-select"
                isMulti
                placeholder="Perfil do Usuário..."
              />
              <div className="invalid-feedback d-block"></div>
            </div>
            <div className="user-crud-buttons-container">
              <button className="btn btn-primary user-crud-button text-white">
                SALVAR
              </button>
              <button
                className="btn btn-outline-danger user-crud-button"
                onClick={() => {}}
              >
                CANCELAR
              </button>
            </div>
          </div>
        </form>
      </div>
    </div>
  );
};

export default Form;
