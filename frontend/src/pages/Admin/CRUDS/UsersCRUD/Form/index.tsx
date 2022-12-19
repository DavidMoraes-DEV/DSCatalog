import { AxiosRequestConfig } from 'axios';
import { useEffect, useState } from 'react';
import { Controller, useForm } from 'react-hook-form';
import { useHistory, useParams } from 'react-router-dom';
import { User } from 'types/user';
import { requestBackend } from 'util/requests';
import { toast } from 'react-toastify';
import Select from 'react-select';
import { RoleData } from 'types/role';
import { getValue } from '@testing-library/user-event/dist/utils';
import { ReactComponent as RevealPass } from '../../../../../assets/images/reveal-password-icon.svg';
import { ReactComponent as HiddenPass } from '../../../../../assets/images/hidden-password-icon.svg';
import './styles.css';

type UrlParams = {
  userId: string;
};

const Form = () => {
  const { userId } = useParams<UrlParams>();
  const isEditing = userId !== 'create';
  const history = useHistory();
  const [selectRoles, setSelectRoles] = useState<RoleData[]>([]);
  const [inputTypePass, setInputTypePass] = useState(false);

  const {
    register,
    handleSubmit,
    formState: { errors },
    setValue,
    setError,
    clearErrors,
    control,
  } = useForm<User>();

  useEffect(() => {
    requestBackend({ url: '/roles', withCredentials: true }).then(
      (response) => {
        setSelectRoles(response.data);
      }
    );
  }, []);

  useEffect(() => {
    if (isEditing) {
      requestBackend({ url: `/users/${userId}`, withCredentials: true }).then(
        (response) => {
          const user = response.data as User;

          setValue('firstName', user.firstName);
          setValue('lastName', user.lastName);
          setValue('email', user.email);
          setValue('password', user.password);
          setValue('roles', user.roles);
        }
      );
    }
  }, [isEditing, userId, setValue]);

  const onSubmit = (formData: User) => {
    const data = {
      ...formData,
    };

    const config: AxiosRequestConfig = {
      method: isEditing ? 'PUT' : 'POST',
      url: isEditing ? `/users/${userId}` : '/users',
      data,
      withCredentials: true,
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
    <div className="user-crud-container">
      <div className="base-card user-crud-form-card">
        <h1 className="user-crud-form-title">
          {isEditing ? 'ATUALIZAR CADASTRO' : 'CADASTRAR USUÁRIO'}
        </h1>
        <form onSubmit={handleSubmit(onSubmit)}>
          <div className="user-crud-inputs-container">
            <div className="user-crud-inputs-content">
              <div className="user-crud-input">
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

              <div className="user-crud-input">
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
            </div>

            <div className="user-crud-input-email">
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

            <div className="user-crud-inputs-content">
              <div className="user-crud-input">
                <div className="user-crud-input-password-container">
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
                    className={`form-control user-crud-input-password ${
                      errors.password ? 'is-invalid' : ''
                    }`}
                    placeholder={
                      isEditing ? 'Alterar a Senha' : 'Defina uma Senha'
                    }
                    name="password"
                    onChange={confirmPassword}
                  />
                  <span className="user-crud-reveal-password-icon">
                    <button type="button" onClick={revealPassword}>
                      {inputTypePass ? <HiddenPass /> : <RevealPass />}
                    </button>
                  </span>
                </div>
                <div className="invalid-feedback d-block">
                  {errors.password?.message}
                </div>
              </div>

              <div className="user-crud-input">
                <div className="user-crud-input-password-container">
                  <input
                    id="cPassword"
                    type={inputTypePass ? 'text' : 'password'}
                    className={`form-control user-crud-input-password ${
                      errors.password ? 'is-invalid' : ''
                    }`}
                    placeholder={
                      isEditing ? 'Confirme a Nova Senha' : 'Confirme a Senha'
                    }
                    name="confirmPassword"
                    onChange={confirmPassword}
                  />
                  <span className="user-crud-reveal-password-icon">
                    <button type="button" onClick={revealPassword}>
                      {inputTypePass ? <HiddenPass /> : <RevealPass />}
                    </button>
                  </span>
                </div>
                <div className="invalid-feedback d-block">
                  {errors.password?.message}
                </div>
              </div>
            </div>

            <div className="user-crud-input-button">
              <Controller
                name="roles"
                rules={{ required: true }}
                control={control}
                render={({ field }) => (
                  <Select
                    {...field}
                    options={selectRoles}
                    classNamePrefix="product-crud-select"
                    isMulti
                    placeholder="Perfil do Usuário..."
                    getOptionLabel={(role: RoleData) =>
                      role.authority
                        .split('_', 2)[1]
                        .replace('OPERATOR', 'OPERADOR')
                        .replace('ADMIN', 'ADMINISTRADOR')
                    }
                    getOptionValue={(role: RoleData) => String(role.id)}
                  />
                )}
              />
              {errors.roles && (
                <div className="invalid-feedback d-block">
                  Campo Obrigatório!
                </div>
              )}
            </div>
            <div className="user-crud-buttons-container">
              <button className="btn btn-primary user-crud-button text-white">
                SALVAR
              </button>
              <button
                className="btn btn-outline-danger user-crud-button"
                onClick={handleCancel}
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
