import { AxiosRequestConfig } from 'axios';
import { useState } from 'react';
import { useForm } from 'react-hook-form';
import { useHistory } from 'react-router-dom';
import { User } from 'types/user';
import { requestBackend } from 'util/requests';
import { toast } from 'react-toastify';
import { getValue } from '@testing-library/user-event/dist/utils';
import { ReactComponent as RevealPass } from '../../../../assets/images/reveal-password-icon.svg';
import { ReactComponent as HiddenPass } from '../../../../assets/images/hidden-password-icon.svg';
import './styles.css';

const CardSignup = () => {
  const history = useHistory();
  const [inputTypePass, setInputTypePass] = useState(false);

  const {
    register,
    handleSubmit,
    formState: { errors },
    setError,
    clearErrors,
  } = useForm<User>();

  const onSubmit = (formData: User) => {
    const data = {
      ...formData,
      roles: [{ id: 1, role: 'ROLE_OPERATOR' }],
    };

    const config: AxiosRequestConfig = {
      method: 'POST',
      url: '/users',
      data,
    };

    requestBackend(config)
      .then(() => {
        toast.info('Usuário Cadastrado com Sucesso');
        history.push('/admin/users');
      })
      .catch(() => {
        toast.error('Erro ao Cadastrar Usuário');
      });
  };

  const handleCancel = () => {
    history.push('/admin/users');
  };

  const revealPassword = (event: React.InputHTMLAttributes<InputEvent>) => {
    inputTypePass === false ? setInputTypePass(true) : setInputTypePass(false);
  };

  const confirmPassword = () => {
    const newPassword = document.getElementById('nPassword');
    const confPassword = document.getElementById('cPassword');

    if (getValue(confPassword) !== '') {
      !(
        (getValue(confPassword) as String) === (getValue(newPassword) as String)
      )
        ? setError('password', {
            type: 'validate',
            message: 'As senhas não correspondem',
          })
        : clearErrors('password');
    }
  };

  return (
    <div className="base-card signup-card-container">
      <h1>CADASTRO</h1>
      <form onSubmit={handleSubmit(onSubmit)}>
        <div className="signup-card-input-content">
          <input
            {...register('firstName', {
              required: 'Campo Obrigatório!',
            })}
            type="text"
            className={`form-control base-input ${
              errors.firstName ? 'is-invalid' : ''
            }`}
            placeholder="Nome"
            name="firstName"
          />
          <div className="invalid-feedback d-block">
            {errors.firstName?.message}
          </div>
        </div>

        <div className="signup-card-input-content">
          <input
            {...register('lastName', {
              required: 'Campo Obrigatório!',
            })}
            type="text"
            className={`form-control base-input ${
              errors.lastName ? 'is-invalid' : ''
            }`}
            placeholder="Sobrenome"
            name="lastName"
          />
          <div className="invalid-feedback d-block">
            {errors.lastName?.message}
          </div>
        </div>

        <div className="signup-card-input-content">
          <input
            {...register('email', {
              required: 'Campo Obrigatório!',
            })}
            type="text"
            className={`form-control base-input ${
              errors.email ? 'is-invalid' : ''
            }`}
            placeholder="Email"
            name="email"
          />
          <div className="invalid-feedback d-block">
            {errors.email?.message}
          </div>
        </div>

        <div className="signup-card-input-password">
          <div className="signup-card-input-password-container">
            <input
              {...register('password', {
                required: 'Campo Obrigatório!',
                pattern: {
                  value: /^(?=.*\d).{8,50}$/,
                  message:
                    'Senha deve ter entre 7 e 50 caracteres e conter ao menos 1 número',
                },
              })}
              id="nPassword"
              type={inputTypePass ? 'text' : 'password'}
              className={`form-control signup-card-input ${
                errors.password ? 'is-invalid' : ''
              }`}
              placeholder={'Defina uma Senha'}
              name="password"
              onChange={confirmPassword}
            />
            <span className="signup-card-reveal-password-icon">
              <button type="button" onClick={revealPassword}>
                {inputTypePass ? <HiddenPass /> : <RevealPass />}
              </button>
            </span>
          </div>
          <div className="invalid-feedback d-block">
            {errors.password?.message}
          </div>
        </div>

        <div className="signup-card-input-password">
          <div className="signup-card-input-password-container">
            <input
              id="cPassword"
              type={inputTypePass ? 'text' : 'password'}
              className={`form-control signup-card-input ${
                errors.password ? 'is-invalid' : ''
              }`}
              placeholder={'Confirme a Senha'}
              name="confirmPassword"
              onChange={confirmPassword}
            />
            <span className="signup-card-reveal-password-icon">
              <button type="button" onClick={revealPassword}>
                {inputTypePass ? <HiddenPass /> : <RevealPass />}
              </button>
            </span>
          </div>
          <div className="invalid-feedback d-block">
            {errors.password?.message}
          </div>
        </div>

        <div className="signup-card-buttons-container">
          <button className="btn btn-primary signup-card-button text-white">
            CADASTRAR
          </button>
          <button
            className="btn btn-outline-danger signup-card-button"
            onClick={handleCancel}
          >
            CANCELAR
          </button>
        </div>
      </form>
    </div>
  );
};

export default CardSignup;
