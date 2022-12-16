import { AxiosRequestConfig } from 'axios';
import { useEffect } from 'react';
import { useForm } from 'react-hook-form';
import { useHistory, useParams } from 'react-router-dom';
import { Category } from 'types/category';
import { requestBackend } from 'util/requests';
import { toast } from 'react-toastify';
import './styles.css';

type UrlParams = {
  categoryId: string;
};

const Form = () => {
  const { categoryId } = useParams<UrlParams>();
  const isEditing = categoryId !== 'create';
  const history = useHistory();

  const {
    register,
    handleSubmit,
    formState: { errors },
    setValue,
  } = useForm<Category>();

  useEffect(() => {
    if (isEditing) {
      requestBackend({ url: `/categories/${categoryId}` }).then((response) => {
        const category = response.data as Category;

        setValue('name', category.name);
      });
    }
  }, [isEditing, categoryId, setValue]);

  const onSubmit = (formData: Category) => {
    const data = {
      ...formData,
    };

    const config: AxiosRequestConfig = {
      method: isEditing ? 'PUT' : 'POST',
      url: isEditing ? `/categories/${categoryId}` : '/categories',
      data,
      withCredentials: true,
    };

    requestBackend(config)
      .then(() => {
        toast.info('Categoria Cadastrada com Sucesso');
        history.push('/admin/categories');
      })
      .catch(() => {
        toast.error('Erro ao Cadastrar Categoria');
      });
  };

  const handleCancel = () => {
    history.push('/admin/categories');
  };

  return (
    <div className="category-crud-container">
      <div className="base-card category-crud-form-card">
        <h1 className="category-crud-form-title">DADOS DA CATEGORIA</h1>
        <form
          className="category-crud-form-container"
          onSubmit={handleSubmit(onSubmit)}
        >
          <div className="category-crud-input-container">
            <input
              {...register('name', {
                required: 'Campo Obrigatório',
              })}
              type="text"
              className={`form-control base-input ${
                errors.name ? 'is-invalid' : ''
              }`}
              placeholder="Nome da Categoria"
              name="name"
            />
            <div className="invalid-feedback d-block">
              {errors.name?.message}
            </div>
          </div>
          <div className="category-crud-buttons-container">
            <button className="btn btn-primary category-crud-button text-white">
              SALVAR
            </button>
            <button
              className="btn btn-outline-danger category-crud-button"
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

export default Form;
