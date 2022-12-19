import { ReactComponent as SearchIcon } from 'assets/images/search-icon.svg';
import './styles.css';
import Select from 'react-select';
import { Controller, useForm } from 'react-hook-form';
import { User } from 'types/user';
import { useEffect, useState } from 'react';
import { requestBackend } from 'util/requests';
import { FilterOptions } from 'types/filterOptions';

export type UserFilterData = {
  textInput?: string;
  user: User | null;
  option: FilterOptions | null;
};

type Props = {
  onSubmitFilter: (data: UserFilterData) => void;
};

const UserFilter = ({ onSubmitFilter }: Props) => {
  const [selectedUsers, setSelectedUsers] = useState<User[]>([]);

  const { register, handleSubmit, setValue, getValues, control } =
    useForm<UserFilterData>();

  useEffect(() => {
    requestBackend({ url: '/users', withCredentials: true }).then(
      (response) => {
        setSelectedUsers(response.data.content);
      }
    );
  }, []);

  const onSubmit = (formData: UserFilterData) => {
    const selectedUser = selectedUsers.find(
      (user) => user.firstName === formData.textInput
    );
    if (selectedUser !== undefined) {
      formData.user = selectedUser;
    } else {
      formData.user = null;
    }

    onSubmitFilter(formData);
  };

  const handleFormClear = () => {
    setValue('textInput', '');
    setValue('user', null);
    setValue('option', null);
  };

  const handleChangeOption = (value: FilterOptions) => {
    setValue('option', value);

    const obj: UserFilterData = {
      textInput: getValues('textInput'),
      user: getValues('user'),
      option: getValues('option'),
    };

    onSubmitFilter(obj);
  };

  return (
    <div className="base-card user-filter-container">
      <form onSubmit={handleSubmit(onSubmit)} className="user-filter-form">
        <div className="user-filter-name-container">
          <input
            {...register('textInput')}
            type="text"
            className="form-control"
            placeholder="Pesquisar por Nome"
            name="textInput"
          />
          <button className="user-filter-search-icon">
            <SearchIcon />
          </button>
        </div>
        <div className="user-filter-botton-container">
          <div className="user-filter-user-container">
            <Controller
              name="option"
              control={control}
              render={({ field }) => (
                <Select
                  {...field}
                  options={[
                    { id: 1, name: 'Mais Antigos' },
                    { id: 2, name: 'Mais Recentes' },
                    { id: 3, name: 'Ordem Alfabética' },
                  ]}
                  isClearable
                  classNamePrefix="user-filter-select"
                  placeholder="Filtrar por"
                  onChange={(value) =>
                    handleChangeOption(value as FilterOptions)
                  }
                  getOptionLabel={(option: FilterOptions) => option.name}
                  getOptionValue={(option: FilterOptions) => String(option.id)}
                />
              )}
            />
          </div>
          <button
            onClick={handleFormClear}
            className="btn btn-outline-secondary btn-user-filter-clear"
          >
            LIMPAR <span className="btn-user-filter-word">FILTRO</span>
          </button>
        </div>
      </form>
    </div>
  );
};

export default UserFilter;
