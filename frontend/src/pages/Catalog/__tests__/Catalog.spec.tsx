import { render, screen, waitFor } from "@testing-library/react"
import { BrowserRouter, Router } from "react-router-dom";
import history from "util/history";
import Catalog from "..";
import { server } from './fixtures';

beforeAll(() => server.listen());
afterEach(() => server.resetHandlers());
afterAll(() => server.close());

test('should render Catalog with products', async () => {

    render(
        <BrowserRouter basename={process.env.PUBLIC_URL}>
            <Router history={history}>
                <Catalog />
            </Router>
        </BrowserRouter>
    );

    expect(screen.getByText('Catálogo de produtos')).toBeInTheDocument();

    await waitFor(() => {
        expect(screen.getByText('Smart TV')).toBeInTheDocument();
    });

})