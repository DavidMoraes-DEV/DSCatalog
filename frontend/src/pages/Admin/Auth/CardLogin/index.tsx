import { Link, useHistory, useLocation } from 'react-router-dom';
import ButtonIcon from 'components/ButtonIcon';
import { useForm } from 'react-hook-form';
import { useContext, useState } from 'react';
import { AuthContext } from 'AuthContext';

import './styles.css';
import { requestBackendLogin } from 'util/requests';
import { saveAuthData } from 'util/storage';
import { getTokenData } from 'util/token';

type CredentialsDTO = {
  username: string;
  password: string;
};

type LocationState = {
  from: string;
};

const CardLogin = () => {
  const location = useLocation<LocationState>();
  const { from } = location.state || { from: { pathname: '/admin' } };
  const { setAuthContextData } = useContext(AuthContext);
  const [hasError, setHasError] = useState(false);
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<CredentialsDTO>();

  const history = useHistory();

  const onSubmit = (credentialsDTO: CredentialsDTO) => {
    requestBackendLogin(credentialsDTO)
      .then((response) => {
        saveAuthData(response.data);
        setHasError(false);
        setAuthContextData({
          authenticated: true,
          tokenData: getTokenData(),
        });
        history.replace(from);
      })
      .catch((error) => {
        setHasError(true);
      });
  };

  return (
    <div className="base-card login-card-container">
      <h1>LOGIN</h1>
      <form onSubmit={handleSubmit(onSubmit)}>
        {hasError && (
          <div className="alert alert-danger" role="alert">
            Erro ao tentar efetuar o login!!!
          </div>
        )}
        <div className="input-email">
          <input
            {...register('username', {
              required: 'Campo Obrigatório',
              pattern: {
                value: /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i,
                message: 'Email inválido',
              },
            })}
            type="text"
            className={`form-control base-input ${
              errors.username ? 'is-invalid' : ''
            }`}
            placeholder="Email"
            name="username"
          />
          <div className="invalid-feedback d-block">
            {errors.username?.message}
          </div>
        </div>
        <div className="input-password">
          <input
            {...register('password', {
              required: 'Campo Obrigatório',
            })}
            type="password"
            className={`form-control base-input ${
              errors.password ? 'is-invalid' : ''
            }`}
            placeholder="Password"
            name="password"
          />
          <div className="invalid-feedback d-block">
            {errors.password?.message}
          </div>
        </div>
        <Link to="/admin/auth/recover" className="login-link-recover">
          Esqueci a senha
        </Link>
        <div className="login-submit">
          <ButtonIcon text="Fazer login" />
        </div>
        <div className="signup-container">
          <span className="not-registered">Não tem Cadastro?</span>
          <Link to="/admin/auth/signup" className="login-link-register">
            CADASTRAR
          </Link>
        </div>
      </form>
    </div>
  );
};

export default CardLogin;
