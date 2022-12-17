import { render, screen } from "@testing-library/react";
import ButtonIcon from "..";


test('ButtonIcon should render button with given text', () => {

    const text = "Fazer login";
    
    render(
        <ButtonIcon text={text} />
    );

    expect(screen.getByText(text)).toBeInTheDocument();
    expect(screen.getByTestId("arrow")).toBeInTheDocument();
});

/*
Se estiver usando React <= 17 utilizar o comando:
yarn add @testing-library/react@release-12.x para não dar erro de incompatibilidade ao rodar os tests
React 18 utilizar yarn add @testing-library/react
*/