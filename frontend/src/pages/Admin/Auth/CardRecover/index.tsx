import './styles.css';

const CardRecover = () => {
  return (
    <div className="base-card recover-card-container">
      <div className="recover-card-form-container">
        <h1>RECUPERAÇÃO DE CONTA</h1>
        <form onSubmit={() => {}}>
          <div className="recover-card-input-content">
            <input
              id="nEmail"
              type="text"
              className="form-control base-input"
              placeholder="Digite o Email Cadastrado"
              name="email"
            />
          </div>

          <div className="recover-card-input-content">
            <input
              id="cEmail"
              type="text"
              className="form-control base-input"
              placeholder="Repita o Email Cadastrado"
              name="confEmail"
            />
          </div>
        </form>
      </div>
      <div className="recover-card-buttons-container">
        <button className="btn btn-primary recover-card-button text-white">
          ENVIAR
        </button>
        <button
          className="btn btn-outline-danger recover-card-button"
          onClick={() => {}}
        >
          CANCELAR
        </button>
      </div>
    </div>
  );
};

export default CardRecover;
