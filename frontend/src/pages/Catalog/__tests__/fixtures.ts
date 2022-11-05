import { rest } from 'msw';
import { setupServer } from 'msw/node';
import { BASE_URL } from 'util/requests';

const findAllResponse = {
  content: [
    {
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
    },
    {
      id: 2,
      name: 'Smart TV',
      description:
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
      price: 2190.0,
      imgUrl:
        'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/2-big.jpg',
      date: '2020-07-14T10:00:00Z',
      categories: [
        {
          id: 2,
          name: 'Eletrônicos',
        },
        {
          id: 3,
          name: 'Computadores',
        },
      ],
    },
    {
      id: 5,
      name: 'Rails for Dummies',
      description:
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
      price: 100.99,
      imgUrl:
        'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/5-big.jpg',
      date: '2020-07-14T10:00:00Z',
      categories: [
        {
          id: 1,
          name: 'Livros',
        },
      ],
    },
    {
      id: 10,
      name: 'PC Gamer Y',
      description:
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
      price: 1700.0,
      imgUrl:
        'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/10-big.jpg',
      date: '2020-07-14T10:00:00Z',
      categories: [
        {
          id: 3,
          name: 'Computadores',
        },
      ],
    },
    {
      id: 7,
      name: 'PC Gamer X',
      description:
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
      price: 1350.0,
      imgUrl:
        'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/7-big.jpg',
      date: '2020-07-14T10:00:00Z',
      categories: [
        {
          id: 3,
          name: 'Computadores',
        },
      ],
    },
    {
      id: 15,
      name: 'PC Gamer Weed',
      description:
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
      price: 2200.0,
      imgUrl:
        'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/15-big.jpg',
      date: '2020-07-14T10:00:00Z',
      categories: [
        {
          id: 3,
          name: 'Computadores',
        },
      ],
    },
    {
      id: 21,
      name: 'PC Gamer Tx',
      description:
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
      price: 1680.0,
      imgUrl:
        'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/21-big.jpg',
      date: '2020-07-14T10:00:00Z',
      categories: [
        {
          id: 3,
          name: 'Computadores',
        },
      ],
    },
    {
      id: 17,
      name: 'PC Gamer Turbo',
      description:
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
      price: 1280.0,
      imgUrl:
        'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/17-big.jpg',
      date: '2020-07-14T10:00:00Z',
      categories: [
        {
          id: 3,
          name: 'Computadores',
        },
      ],
    },
    {
      id: 20,
      name: 'PC Gamer Tr',
      description:
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
      price: 1650.0,
      imgUrl:
        'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/20-big.jpg',
      date: '2020-07-14T10:00:00Z',
      categories: [
        {
          id: 3,
          name: 'Computadores',
        },
      ],
    },
    {
      id: 9,
      name: 'PC Gamer Tera',
      description:
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
      price: 1950.0,
      imgUrl:
        'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/9-big.jpg',
      date: '2020-07-14T10:00:00Z',
      categories: [
        {
          id: 3,
          name: 'Computadores',
        },
      ],
    },
    {
      id: 13,
      name: 'PC Gamer Plus',
      description:
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
      price: 1350.0,
      imgUrl:
        'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/13-big.jpg',
      date: '2020-07-14T10:00:00Z',
      categories: [
        {
          id: 3,
          name: 'Computadores',
        },
      ],
    },
    {
      id: 11,
      name: 'PC Gamer Nitro',
      description:
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
      price: 1450.0,
      imgUrl:
        'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/11-big.jpg',
      date: '2020-07-14T10:00:00Z',
      categories: [
        {
          id: 3,
          name: 'Computadores',
        },
      ],
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
  totalPages: 3,
  totalElements: 25,
  last: false,
  number: 0,
  sort: {
    sorted: true,
    unsorted: false,
    empty: false,
  },
  size: 12,
  numberOfElements: 12,
  first: true,
  empty: false,
};

export const server = setupServer(
  rest.get(`${BASE_URL}/products`, (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(findAllResponse));
  })
);
