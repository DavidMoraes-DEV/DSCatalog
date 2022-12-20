import './styles.css';
import { ReactComponent as RevealPass } from '../../../../assets/images/reveal-password-icon.svg';

const CardResetPassword = () => {
  
  return (
    <div className="base-card reset-password-card-container">
      <div className="reset-password-card-form-container">
        <h1>CRIE UMA NOVA SENHA</h1>

        <form onSubmit={() => {}}>
          <div className="reset-password-card-input-content">
            <div className="reset-password-card-input-container">
              <input
                id="nPassword"
                type='password'
                className='form-control reset-password-card-input'
                placeholder="Digite uma nova senha"
                name="password"
              />
              <span className="reset-password-card-reveal-icon">
                <button type="button" onClick={() => {}}>
                  <RevealPass />
                </button>
              </span>
            </div>
          </div>

          <div className="reset-password-card-input-content">
            <div className="reset-password-card-input-container">
              <input
                id="cPassword"
                type='password'
                className='form-control reset-password-card-input'
                placeholder="Confirme sua nova senha"
                name="confPassword"
                onChange={() => {}}
              />
              <span className="reset-password-card-reveal-icon">
                <button type="button" onClick={() => {}}>
                  <RevealPass />
                </button>
              </span>
            </div>
          </div>

          <div className="reset-password-card-buttons-container">
            <button className="btn btn-primary reset-password-card-button text-white">
              ENVIAR
            </button>
            <button
              className="btn btn-outline-danger reset-password-card-button"
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

export default CardResetPassword;
