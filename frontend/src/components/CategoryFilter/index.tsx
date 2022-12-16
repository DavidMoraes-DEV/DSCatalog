import { ReactComponent as SearchIcon } from 'assets/images/search-icon.svg';
import './styles.css';
import Select from 'react-select';
import { Controller, useForm } from 'react-hook-form';
import { Category } from 'types/category';
import { useEffect, useState } from 'react';
import { requestBackend } from 'util/requests';
import { FilterOptions } from 'types/filterOptions';

export type CategoryFilterData = {
  textInput?: string;
  category: Category | null;
  option: FilterOptions | null;
};

type Props = {
  onSubmitFilter: (data: CategoryFilterData) => void;
};

const CategoryFilter = ({ onSubmitFilter }: Props) => {
  const [selectCategories, setSelectCategories] = useState<Category[]>([]);

  const { register, handleSubmit, setValue, getValues, control } =
    useForm<CategoryFilterData>();

  useEffect(() => {
    requestBackend({ url: '/categories' }).then((response) => {
      setSelectCategories(response.data.content);
    });
  }, []);

  const onSubmit = (formData: CategoryFilterData) => {
    const selectedCategory = selectCategories.find(
      (catg) => catg.name === formData.textInput
    );
    if (selectedCategory !== undefined) {
      formData.category = selectedCategory;
    } else {
      formData.category = null;
    }

    onSubmitFilter(formData);
  };

  const handleFormClear = () => {
    setValue('textInput', '');
    setValue('category', null);
    setValue('option', null);
  };

  const handleChangeOption = (value: FilterOptions) => {
    setValue('option', value);

    const obj: CategoryFilterData = {
      textInput: getValues('textInput'),
      category: getValues('category'),
      option: getValues('option'),
    };

    onSubmitFilter(obj);
  };

  return (
    <div className="base-card category-filter-container">
      <form onSubmit={handleSubmit(onSubmit)} className="category-filter-form">
        <div className="category-filter-name-container">
          <input
            {...register('textInput')}
            type="text"
            className="form-control"
            placeholder="Pesquisar Categoria"
            name="textInput"
          />
          <button className="category-filter-search-icon">
            <SearchIcon />
          </button>
        </div>
        <div className="category-filter-botton-container">
          <div className="category-filter-category-container">
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
                  classNamePrefix="category-filter-select"
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
            className="btn btn-outline-secondary btn-category-filter-clear"
          >
            LIMPAR <span className="btn-category-filter-word">FILTRO</span>
          </button>
        </div>
      </form>
    </div>
  );
};

export default CategoryFilter;
