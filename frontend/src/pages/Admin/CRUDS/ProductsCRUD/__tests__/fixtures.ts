import { rest } from 'msw';
import { setupServer } from 'msw/node';
import { BASE_URL } from 'util/requests';

const findCategoriesResponse = {
    content: [
        {
            id: 1,
            name: 'Livros',
        },
        {
            id: 2,
            name: 'Eletrônicos',
        },
        {
            id: 3,
            name: 'Computadores',
        },
    ],
    pageable: {
        sort: {
            sorted: true,
            unsorted: false,
            empty: false,
        },
        offset: 0,
        pageNumber: 0,
        pageSize: 12,
        paged: true,
        unpaged: false,
    },
    totalPages: 1,
    totalElements: 3,
    last: true,
    number: 0,
    sort: {
        sorted: true,
        unsorted: false,
        empty: false,
    },
    size: 12,
    numberOfElements: 3,
    first: true,
    empty: false,
};

const saveProductResponse = {
    id: 1,
    name: 'The Lord of the Rings',
    description:
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
    price: 90.5,
    imgUrl:
        'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/1-big.jpg',
    date: '2020-07-13T20:50:07.123450Z',
    categories: [
        {
            id: 1,
            name: 'Livros',
        },
    ],
}

export const server = setupServer(
    rest.get(`${BASE_URL}/categories`, (req, res, ctx) => {
        return res(
            ctx.status(200),
            ctx.json(findCategoriesResponse));
    }),

    rest.post(`${BASE_URL}/products`, (req, res, ctx) => {
        return res(
            ctx.status(201),
            ctx.json(saveProductResponse));
    }),
);