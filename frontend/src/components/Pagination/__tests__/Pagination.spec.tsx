import { render, screen } from "@testing-library/react";
import userEvent from "@testing-library/user-event"
import Pagination from "..";

describe('Pagination tests', () => {

    test('should render Pagination', () => {
        const pageCount = 3;
        const range = 3;

        render(
            <Pagination 
                pageCount={pageCount}
                range={range}
            />
        )

        const page1 = screen.getByText("1");
        const page2 = screen.getByText("2");
        const page3 = screen.getByText("3");

        expect(page1).toBeInTheDocument();
        expect (page1).toHaveClass("pagination-link-active");
        
        expect(page2).toBeInTheDocument();
        expect (page2).not.toHaveClass("pagination-link-active");

        expect(page3).toBeInTheDocument();
        expect (page3).not.toHaveClass("pagination-link-active");

        expect(screen.queryByText("4")).not.toBeInTheDocument();


    });

    test('next arrow should call onChange', () => {
        const pageCount = 3;
        const range = 3;
        const onChange = jest.fn();

        render(
            <Pagination 
                pageCount={pageCount}
                range={range}
                onChange={onChange}
            />
        );

        const arrowNext = screen.getByTestId("arrow-next");

        userEvent.click(arrowNext);
        expect(onChange).toHaveBeenCalledWith(1);


    });

    test('previous arrow should call onChange', () => {
        const pageCount = 3;
        const range = 3;
        const onChange = jest.fn();
        const forcePage = 1;

        render(
            <Pagination 
                pageCount={pageCount}
                range={range}
                onChange={onChange}
                forcePage={forcePage}
            />
        );

        const arrowPrevious = screen.getByTestId("arrow-previous");

        userEvent.click(arrowPrevious);
        expect(onChange).toHaveBeenCalledWith(0);


    });

    test('page link arrow should call onChange', () => {
        const pageCount = 3;
        const range = 3;
        const onChange = jest.fn();

        render(
            <Pagination 
                pageCount={pageCount}
                range={range}
                onChange={onChange}
            />
        );

        const page2 = screen.getByText("2");

        userEvent.click(page2);
        expect(onChange).toHaveBeenCalledWith(1);


    });

})

/*
Se estiver usando React <= 17 utilizar o comando:
yarn add @testing-library/user-event@13.x para não dar erro de incompatibilidade ao rodar os tests
React 18 utilizar yarn add @testing-library/user-event
*/