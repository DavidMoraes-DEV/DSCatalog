import { render, screen } from "@testing-library/react";
import ProductPrice from "..";

test('should render ProductPrice', () => {

    const price = 200.00;

    render(
        <ProductPrice price={price} />
    );

    expect(screen.getByText("R$")).toBeInTheDocument();
    expect(screen.getByText("200,00")).toBeInTheDocument();
    
})