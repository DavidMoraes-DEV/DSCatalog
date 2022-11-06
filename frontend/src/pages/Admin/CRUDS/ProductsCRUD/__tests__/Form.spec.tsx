import matchers from '@testing-library/jest-dom/matchers';
import { render, screen, waitFor } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { BrowserRouter, Router, useParams } from 'react-router-dom';
import selectEvent from 'react-select-event';
import { ToastContainer } from 'react-toastify';
import history from 'util/history';
import Form from '../Form';
import { server } from './fixtures';

beforeAll(() => server.listen());
afterEach(() => server.resetHandlers());
afterAll(() => server.close());

jest.mock('react-router-dom', () => ({
  ...jest.requireActual('react-router-dom'),
  useParams: jest.fn(),
}));

describe('Product Form Create tests', () => {
  beforeEach(() => {
    (useParams as jest.Mock).mockReturnValue({
      productId: 'create',
    });
  });

  test('should show toast and redirect when submit form correctly', async () => {
    
    /*Renderiza o formulário e da acesso ao ToastContainer*/
    render(
      <BrowserRouter basename={process.env.PUBLIC_URL}>
        <Router history={history}>
          <ToastContainer />
          <Form />
        </Router>
      </BrowserRouter>
    );

    /*Acessa os Inputs do formulário*/
    const nameInput = screen.getByTestId('name');
    const categoriesInput = screen.getByLabelText('Categorias');
    const priceInput = screen.getByTestId('price');
    const imgUrlInput = screen.getByTestId('imgUrl');
    const descriptionInput = screen.getByTestId('description');

    /*Simula o preenchimento do formulário*/
    const submitButton = screen.getByRole('button', { name: /salvar/i });

    userEvent.type(nameInput, 'Computador');
    await selectEvent.select(categoriesInput, ['Eletrônicos', 'Computadores']);
    userEvent.type(priceInput, '5000.12');
    userEvent.type(imgUrlInput, 'https://raw.githubusercontent.com/DavidMoraes-DEV/imagem-computador-test.png');
    userEvent.type(descriptionInput, 'Teste de Descrição do Produto');

    /*Simula o click do Botão SALVAR*/
    userEvent.click(submitButton);

    /*Verifica se o Toast está presente*/
    await waitFor(() => {
      const toastElement = screen.getByText('Produto Cadastrado com Sucesso');
      expect(toastElement).toBeInTheDocument();
    });

    /*Verifica se foi realmente redirecionado para a tela de listagem de produtos*/
    expect(history.location.pathname).toEqual('/admin/products');
  });

  test('should show 5 validation messages when just clicking submit', async () => {
    
    render(
      <BrowserRouter basename={process.env.PUBLIC_URL}>
        <Router history={history}>
          <Form />
        </Router>
      </BrowserRouter>
    );
    
    const submitButton = screen.getByRole('button', { name: /salvar/i });

    userEvent.click(submitButton);

    await waitFor(() => {
        
        const messages = screen.getAllByText('Campo Obrigatório');
        expect(messages).toHaveLength(5);

    })

  });

});
