import { AxiosRequestConfig } from 'axios';
import { useForm } from 'react-hook-form';
import { useHistory } from 'react-router-dom';
import { requestBackend } from 'util/requests';
import { toast } from 'react-toastify';
import { getValue } from '@testing-library/user-event/dist/utils';
import './styles.css';

type RecoverEmail = {
  email: string;
};

export type PasswordResetToken = {
  token: string;
};

type Props = {
  onResetPassword: (data: PasswordResetToken) => void;
};

const CardRecover = ({ onResetPassword }: Props) => {
  const history = useHistory();

  const {
    register,
    handleSubmit,
    formState: { errors },
    setError,
    clearErrors,
  } = useForm<RecoverEmail>();

  const onSubmit = (formData: RecoverEmail) => {
    const data = {
      ...formData,
    };

    const config: AxiosRequestConfig = {
      method: 'POST',
      url: '/users/forgot-password',
      data,
    };

    requestBackend(config)
      .then((response) => {
        onResetPassword(response.data);
        toast.info(
          'Email para a alteração da senha enviado com Sucesso. Favor verificar seu Email!'
        );
        history.push('/admin/auth/login');
      })
      .catch(() => {
        toast.error(
          'Erro ao enviar Email de alteração da senha. Email não encontrado ou token inválido!'
        );
      });
  };

  const handleCancel = () => {
    history.push('/admin/users');
  };

  const confirmEmail = () => {
    const newPassword = document.getElementById('nEmail');
    const confPassword = document.getElementById('cEmail');

    if (getValue(confPassword) !== '') {
      !(
        (getValue(confPassword) as String) === (getValue(newPassword) as String)
      )
        ? setError('email', {
            type: 'validate',
            message: 'Os emails não correspondem',
          })
        : clearErrors('email');
    }
  };

  return (
    <div className="base-card recover-card-container">
      <div className="recover-card-form-container">
        <h1>RECUPERAÇÃO DE CONTA</h1>

        <form onSubmit={handleSubmit(onSubmit)}>
          <div className="recover-card-input-content">
            <input
              {...register('email', {
                required: 'Campo Obrigatório!',
                pattern: {
                  value: /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i,
                  message: 'Email inválido',
                },
              })}
              id="nEmail"
              type="text"
              className={`form-control base-input ${
                errors.email ? 'is-invalid' : ''
              }`}
              placeholder="Digite seu Email"
              name="email"
            />
            <div className="invalid-feedback d-block">
              {errors.email?.message}
            </div>
          </div>

          <div className="recover-card-input-content">
            <input
              id="cEmail"
              type="text"
              className={`form-control base-input ${
                errors.email ? 'is-invalid' : ''
              }`}
              placeholder="Confirme seu Email"
              name="confEmail"
              onChange={confirmEmail}
            />
            <div className="invalid-feedback d-block">
              {errors.email?.message}
            </div>
          </div>

          <div className="recover-card-buttons-container">
            <button className="btn btn-primary recover-card-button text-white">
              ENVIAR
            </button>
            <button
              className="btn btn-outline-danger recover-card-button"
              onClick={handleCancel}
            >
              CANCELAR
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default CardRecover;
