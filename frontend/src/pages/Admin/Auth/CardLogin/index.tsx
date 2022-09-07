import { Link, useHistory } from 'react-router-dom';
import ButtonIcon from 'components/ButtonIcon';

import './styles.css';
import { useForm } from 'react-hook-form';
import { getTokenData, requestBackendLogin, saveAuthData } from 'util/requests';
import { useContext, useState } from 'react';
import { AuthContext } from 'AuthContext';

type FormData = {
  username: string;
  password: string;
};

const CardLogin = () => {

  const { setAuthContextData } = useContext(AuthContext);
  const [hasError, setHasError] = useState(false);
  const { register, handleSubmit, formState: { errors } } = useForm<FormData>();
  
  const history = useHistory();

  const onSubmit = (formData: FormData) => {
      requestBackendLogin(formData)
      .then((response) => {
        saveAuthData(response.data);
        setHasError(false);
        setAuthContextData({
          authenticated: true,
          tokenData: getTokenData(),
        })
        history.push('/admin');
      })
      .catch((error) => {
        setHasError(true);
        console.log('ERRO', error);
      });
    console.log(formData);
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
                message: 'Email inválido'
              }
            })}
            type="text"
            className={`form-control base-input ${errors.username ? 'is-invalid' : ''}`}
            placeholder="Email"
            name="username"
          />
          <div className='invalid-feedback d-block'>{errors.username?.message}</div>
        </div>
        <div className="input-password">
          <input
            {...register('password', {
              required: 'Campo Obrigatório'
            })}
            type="password"
            className={`form-control base-input ${errors.password ? 'is-invalid' : ''}`}
            placeholder="Password"
            name="password"
          />
          <div className='invalid-feedback d-block'>{errors.password?.message}</div>
        </div>
        <Link to="/admin/auth/recover" className="login-link-recover">
          Esqueci a senha
        </Link>
        <div className="login-submit" >
          <ButtonIcon text="Fazer login" />
        </div>
        <div className="signup-container">
          <span className="not-registered">Não tem Cadastro?</span>
          <Link to="/admin/auth/register" className="login-link-register">
            CADASTRAR
          </Link>
        </div>
      </form>
    </div>
  );
};

export default CardLogin;
